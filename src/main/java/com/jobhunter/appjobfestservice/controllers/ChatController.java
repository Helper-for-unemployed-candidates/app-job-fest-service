package com.jobhunter.appjobfestservice.controllers;

import com.jobhunter.appjobfestservice.dto.ChatDTO;
import com.jobhunter.appjobfestservice.dto.MessageDTO;
import com.jobhunter.appjobfestservice.dto.PaginationDTO;
import com.jobhunter.appjobfestservice.dto.SendMessageDTO;
import com.jobhunter.appjobfestservice.repositories.projection.ChatProjection;
import com.jobhunter.appjobfestservice.shit.payload.Response;
import com.jobhunter.appjobfestservice.shit.utils.ConstantFields;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ChatController.BASE_PATH)
public interface ChatController {
    String BASE_PATH = ConstantFields.BASE_PATH + "/chat";

    @PostMapping("/create")
    Response<String> createChatRoom(@RequestParam(required = false) String jobApplicationId,
                                    @RequestParam(required = false) String resumeId);

    @MessageMapping("/room")
    void processMessage(@Payload SendMessageDTO message);

    @GetMapping("/{chatRoomId}")
    Response<PaginationDTO<MessageDTO>> getMessages(@PathVariable String chatRoomId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size);

    @GetMapping("/search")
    Response<Page<ChatProjection>> getChats(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(required = false) String jobApplicationId);
}
