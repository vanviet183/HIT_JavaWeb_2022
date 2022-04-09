package com.vitvn183.trainb5.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@RequestMapping("/api/v1/")
@RestController
public @interface RestApiV1 {

}
