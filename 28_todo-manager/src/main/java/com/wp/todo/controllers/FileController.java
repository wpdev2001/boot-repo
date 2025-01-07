package com.wp.todo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/single")
    public String uploadSingle(@RequestParam("image")MultipartFile file){

        logger.info("Name: {}", file.getName());
        logger.info("Content type: {}",file.getContentType());
        logger.info("Original File Name: {}",file.getOriginalFilename());
        logger.info("File Size: {}",file.getSize());
        return "File Test";

        /*
        To write the got data into some other file:
        -------------------------------------------
        InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("data.png");
        byte data[] = new byte[inputStream.available()];
        fileOutputStream.write(data);

        If we upload the file of max size the code will throw the error so to avoid
        that error we need to add one property inside application.properties
         */
    }
}
