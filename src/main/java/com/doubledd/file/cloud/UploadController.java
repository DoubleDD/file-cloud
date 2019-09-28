package com.doubledd.file.cloud;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author kedong
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Value("${settings.upload-path}")
    private String uploadPath;

    @GetMapping
    public String hello(){
        return uploadPath;
    }


    /**
     * 分片上传文件
     *
     * @param request
     * @param uploadRequest
     * @return
     */
    @PostMapping
    public ResponseEntity upload(HttpServletRequest request, UploadRequest uploadRequest) throws IOException {
        //文件信息
        MultipartFile file = uploadRequest.getFile();
        if (file.getBytes().length == 0) {
            return new ResponseEntity("空文件", HttpStatus.BAD_REQUEST);
        }

        String fileName  = file.getOriginalFilename();
        String path      = uploadPath + File.separator + uploadRequest.getDir();
        String finalPath = path + File.separator + fileName;
        FileUtils.mkdirIfNotExist(path);

        if (uploadRequest.isFirst() && uploadRequest.isLast()) {
            //这个文件就只有一片
            file.transferTo(new File(finalPath));
        } else if (uploadRequest.isFirst()) {
            //第一片
        } else {
            //最后一片
        }


        return ResponseEntity.ok().build();
    }
}

@Data
@Setter
@Getter
class UploadRequest {

    /**
     * 是否第一个分片
     */
    private boolean first;

    /**
     * 是否最后一个分片
     */
    private boolean last;

    /**
     * 文件唯一标识
     */
    private String etag;

    /**
     * 分片序号
     */
    private int index;

    /**
     * 文件最后修改时间
     */
    private long lastModify;

    /**
     * 要存储到哪个目录下
     */
    private String dir;

    /**
     * 文件实体
     */
    private MultipartFile file;
}