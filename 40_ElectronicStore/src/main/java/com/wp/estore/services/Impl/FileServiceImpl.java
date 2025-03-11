package com.wp.estore.services.Impl;

import com.wp.estore.exceptions.BadApiRequest;
import com.wp.estore.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        String originalFilename = file.getOriginalFilename();
        logger.info("Filename: {}", originalFilename);
        String filename = UUID.randomUUID().toString(); //generating file name through UUID
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename + extension;
        String fullPathWithFileName = path + fileNameWithExtension;

        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")){
            //save image file

            //making folder
            File folder_file = new File(path);

            if(!folder_file.exists()){
                //create the folder
                folder_file.mkdirs(); // mkdir() method basically use for single folder level
            }

            // upload
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;
        }
        else {
            throw new BadApiRequest("File with this " + extension + " not allowed!!");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
