package com.jobhunter.appjobfestservice.shit.aop;


import com.jobhunter.appjobfestservice.shit.enums.RoleEnum;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorize {
    RoleEnum[] permissions() default {};
}
