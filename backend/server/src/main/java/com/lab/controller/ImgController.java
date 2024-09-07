package com.lab.controller;

import com.lab.dto.ImgDTO;
import com.lab.result.Result;
import com.lab.service.ImgService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgService imgService;

    @PostMapping
    public void handleImg(MultipartFile file, ImgDTO attrs, HttpServletResponse response) {
        log.info("处理图片：{} {}", file.getOriginalFilename(), attrs);
        imgService.handle(file, attrs, response);
    }

}
