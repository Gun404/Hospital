package com.bupt.Controller.Common;



import com.bupt.Pojo.Result;
import com.bupt.Utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/common/upload")
public class UploadController {

    @Autowired
    private AliOSSUtils utils;

    @PostMapping
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        log.info("文件上传，文件名:{}",file.getOriginalFilename());
        String url=utils.upload(file);
        log.info("图片的url地址：{}",url);
        return Result.success(url);
    }
}
