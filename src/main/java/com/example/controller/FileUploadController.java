package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

   // private static final String UPLOAD_FOLDER = "/path/to/your/upload/folder/";
    @Value("${spring.servlet.multipart.location}")
	private String location;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            // Create the upload folder if it doesn't exist
            File folder = new File(location);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Save the file to the upload folder
            Path filePath = Paths.get(location + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    @PostMapping("/edit/{filename}")
    public ResponseEntity<String> editFile(@PathVariable("filename") String filename,
                                           @RequestBody String content) {
        try {
            Path filePath = Paths.get(location + filename);

            // Check if the file exists
            if (!Files.exists(filePath)) {
                return new ResponseEntity<>("File not found.", HttpStatus.NOT_FOUND);
            }

            // Write the updated content to the file
            Files.write(filePath, content.getBytes());

            return new ResponseEntity<>("File updated successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

