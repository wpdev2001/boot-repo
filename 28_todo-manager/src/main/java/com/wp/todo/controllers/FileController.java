package com.wp.todo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;

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

    //Uploading multiple files
    @PostMapping("/multiple")
    public String uploadMultiple(@RequestParam("files") MultipartFile[] files){
        Arrays.stream(files).forEach(file -> {
            logger.info("File Name {}", file.getOriginalFilename());
            logger.info("File Type {}", file.getContentType());
            System.out.println("++++++++++++++++++++++++++++++++++++++++");
        });
        return "Handling multiple files";
    }

    //Serving the image files in response
    @GetMapping("/serve-img")
    public void serveImageHandler(HttpServletResponse response){
        //image
        try {

            InputStream fileInputStream = new FileInputStream("images/img1.png");
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(fileInputStream,response.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
