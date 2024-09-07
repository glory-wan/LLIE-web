package com.lab.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.lab.constant.MessageConstant;
import com.lab.dto.ImgDTO;
import com.lab.exception.ImageHandleException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;


@Slf4j
@Service
public class ImgService {
    @Value("${web.python.env}")
    private String python;
    @Value("${web.python.commandTA}")
    private String commandTA;
    @Value("${web.python.input-dir}")
    private String inputDir;
    @Value("${web.python.result-dir}")
    private String resultDir;

    private String[] imageToArgs(File image, ImgDTO attrs) {
        // 准备使用commandTA.py处理图片使用的命令行指令及参数
        return new String[]{
                python, commandTA,
                "--img", image.getAbsolutePath(),
                "--method", attrs.getMethod(),
                "--cs", attrs.getCs(),
                "--name", FileUtil.mainName(image),
                "--format", FileUtil.extName(image),
                "--save", resultDir
        };
    }

    public void handle(MultipartFile img, ImgDTO attrs, HttpServletResponse response) {
        // 将图片保存在本地
        File image = new File(inputDir + "/" + img.getOriginalFilename());
        if (!image.getParentFile().exists()) {
            image.getParentFile().mkdirs();
        }
        try {
            img.transferTo(image);
        } catch (IOException e) {
            throw new ImageHandleException(e.getMessage());
        }

        // 执行Python脚本
        String[] args = imageToArgs(image, attrs);
        try {
            Process proc = Runtime.getRuntime().exec(args);
            proc.waitFor();
            // python程序错误处理
            byte[] bytes = proc.getErrorStream().readAllBytes();
            if (bytes.length > 0) {
                String error = new String(bytes);
                // 忽略warning
                if (!error.contains(MessageConstant.PYTHON_WARNING)) {
                    log.error(new String(bytes));
                    throw new ImageHandleException(MessageConstant.PYTHON_ERROR);
                }
                log.warn(new String(bytes));
                log.info(MessageConstant.IMG_HANDLE_SUCCESS);
            } else {
                log.info(MessageConstant.IMG_HANDLE_SUCCESS);
            }
        } catch (IOException | InterruptedException e) {
            throw new ImageHandleException(e.getMessage());
        }

        // 将结果写入响应流
        File result = new File(resultDir + "/" + image.getName());
        if (!result.getParentFile().exists()) {
            result.getParentFile().mkdirs();
        }
        response.setContentType(FileUtil.getMimeType(result.getAbsolutePath()));
        try(FileInputStream fis = new FileInputStream(result)) {
            IoUtil.copy(fis, response.getOutputStream(), 2048);
        } catch (IOException e) {
            throw new ImageHandleException(e.getMessage());
        } finally {
            result.delete();
            image.delete();
        }
    }
}
