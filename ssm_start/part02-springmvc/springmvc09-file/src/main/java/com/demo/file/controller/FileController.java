package com.demo.file.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private ServletContext servletContext;

    private String imageFolder = "/images";


    @PostMapping("/upload")
    public String upload(String nickName, MultipartFile headPicture, MultipartFile backgroundPicture) throws IOException {
        //1.获取项目运行真实路径
        String realPath = servletContext.getRealPath(imageFolder);
        System.out.println(realPath);
        //2.为文件生成唯一文件名
        String newFileName1 = UUID.randomUUID().toString().replaceAll("-", "") + "_" + headPicture.getOriginalFilename();
        String newFileName2 = UUID.randomUUID().toString().replaceAll("-", "") + "_" + backgroundPicture.getOriginalFilename();

        //3.文件转存：指定路径+分隔符+文件名.文件类型
        headPicture.transferTo(new File(realPath + File.separator + newFileName1));
        backgroundPicture.transferTo(new File(realPath + File.separator + newFileName2));
        return "upload success";
    }

    @GetMapping("download")
    public ResponseEntity<byte[]> downloadFile(String fileName) throws IOException {
        //1.获取存储图片的真实路径
        String realPath = servletContext.getRealPath(imageFolder);
        System.out.println(realPath);

        //2.拼接该文件的真实本地路径
        String imageRealPath = realPath + File.separator + fileName;
        System.out.println(imageRealPath);

        //3.将本地文件读取内存中
        FileInputStream fileInputStream = new FileInputStream(imageRealPath);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);

        //4.响应文件给客户端，并告知客户端下载
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        return responseEntity;
    }

}
