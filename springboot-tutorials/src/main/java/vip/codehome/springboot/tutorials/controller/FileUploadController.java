package vip.codehome.springboot.tutorials.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dsys
 * @version v1.0
 * max-file-size设置能接受文件的最大带下
 * max-request-size 1次能接受文件的大小
 * 分片上传、断点续传、秒传、文件夹上传
 **/
@RestController
@RequestMapping("/file")
public class FileUploadController {
  @PostMapping("/upload")
  public ResponseEntity upload(MultipartFile file){
    return  ResponseEntity.ok().build();
  }
}
