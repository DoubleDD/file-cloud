package com.doubledd.file.cloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kedong
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @GetMapping
    public String hello(){
        return "hello";
    }
}
