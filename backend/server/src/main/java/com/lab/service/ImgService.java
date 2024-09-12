package com.lab.service;

import cn.hutool.core.img.ImgUtil;
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
import java.util.Base64;
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

    public String handle(MultipartFile img, ImgDTO attrs, HttpServletResponse response) {
        // 将图片保存在本地
        File image = FileUtil.file(inputDir + "/" + img.getOriginalFilename());
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

        File result = FileUtil.file(resultDir + "/" + image.getName());
        if (!result.getParentFile().exists()) {
            result.getParentFile().mkdirs();
        }
        // 大于4MB进行压缩
        float size = (float) result.length() / 1024 / 1024;
        if (size > 4) {
            log.info("图片正在进行压缩...");
            ImgUtil.scale(result, result, (4/size));
        }
        // 将图片转化为base64返回给前端
        try (BufferedInputStream fis = FileUtil.getInputStream(result)) {
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            return "data:" + FileUtil.getMimeType(result.getAbsolutePath())
                    + ";base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new ImageHandleException(e.getMessage());
        } finally {
            result.delete();
            image.delete();
        }
    }
}
