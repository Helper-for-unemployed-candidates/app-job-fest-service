package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.JobApplicationDTO;
import com.jobhunter.appjobfestservice.dto.JobCreateDTO;
import com.jobhunter.appjobfestservice.dto.JobUpdateDTO;
import com.jobhunter.appjobfestservice.entity.JobApplication;
import com.jobhunter.appjobfestservice.service.JobService;
import com.jobhunter.appjobfestservice.shit.aop.Authorize;
import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(JobApplicationController.BASE_PATH)
public class JobApplicationController {
    public static final String BASE_PATH = ConstantFields.BASE_PATH + "/job";

    private final JobService service;


    @GetMapping("/{id}")
    public Response<JobApplicationDTO> getJobByID(@PathVariable String id) {
        log.info("Getting job application by ID: {}", id);
        return Response.successResponse(service.findById(id));
    }

    @GetMapping("/all-jobs")
    public Response<Page<JobApplicationDTO>> getAllJobs
            (@RequestParam(value = "size", defaultValue = "10") int size,
             @RequestParam(value = "page", defaultValue = "1") int page,
             @PathVariable UUID id) {
        PageRequest pageRequest = PageRequest.of(size, page);
        log.info("Getting all job applications");
        return Response.successResponse(service.findAll(pageRequest, id));
    }

    @PostMapping
    @Authorize(permissions = {RoleEnum.COMPANY})
    public Response<JobCreateDTO> createJob(@RequestBody JobCreateDTO dto) {
        log.info("createJob: {}", dto);
        return Response.successResponse(service.create(dto));
    }

    @PutMapping("/{id}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<JobApplicationDTO> updateJob(@PathVariable String id, @RequestBody JobUpdateDTO dto) {
        log.info("updateJob: {}", dto);
        return Response.successResponse(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Authorize(permissions = RoleEnum.COMPANY)
    public Response<Void> deleteJob(@PathVariable String id) {
        log.info("deleteJob: {}", id);
        service.deleteById(id);
        return Response.successResponse();
    }

    @PostMapping("/write-all")
    @Authorize(permissions = {RoleEnum.COMPANY})
    public Response<List<JobCreateDTO>> createMultiple(@RequestBody List<JobCreateDTO> jobCreateDTO) {
        log.info("createMultiple: {}", jobCreateDTO);
        return Response.successResponse(service.createMultiple(jobCreateDTO));
    }

    @GetMapping("/search-by-title")
    public Response<Page<JobApplicationDTO>> findByTitle(@RequestParam String title,
                                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                                         @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageRequest = PageRequest.of(size, page);
        return Response.successResponse(service.findByTitle(title, pageRequest));
    }


}
