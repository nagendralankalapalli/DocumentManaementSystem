package com.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    	
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

         if (authentication == null || !authentication.isAuthenticated()) {
             // Handle unauthorized access here
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
         }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         String username = userDetails.getUsername();
         
         
         
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
    
    
    
    
    
    
    
  

	    @PostMapping("/generate-pdf")
	    public String generatePdf(@RequestParam String fileName, @RequestParam String content) throws IOException {
	        try (PDDocument document = new PDDocument()) {
	            PDPage page = new PDPage(PDRectangle.A4);
	            document.addPage(page);

	            PDPageContentStream contentStream = new PDPageContentStream(document, page);

	            // Add content to the PDF document
	            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	            contentStream.beginText();
	            contentStream.newLineAtOffset(100, 700);
	            contentStream.showText(content);
	            contentStream.endText();

	            contentStream.close();

	            // Save the PDF content to a byte array
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            document.save(byteArrayOutputStream);
	            document.close();

	            // Save the PDF to a local file
	            String filePath = location + fileName + ".pdf";
	            File pdfFile = new File(filePath);
	            try (FileOutputStream fileOutputStream = new FileOutputStream(pdfFile)) {
	                fileOutputStream.write(byteArrayOutputStream.toByteArray());
	            }

	            return "PDF saved to: " + filePath;
	        }
	    }
	    
	    
	    @PostMapping("/update-pdf")
	    public String updatePdf(@RequestParam String fileName, @RequestParam String content) throws IOException {
	        String filePath = location + fileName + ".pdf";

	        try (PDDocument document = PDDocument.load(new File(filePath))) {
	            PDPage page = document.getPage(0); // Assuming you want to update the first page

	            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

	            // Add content to the PDF document
	            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
	            contentStream.beginText();
	            contentStream.newLineAtOffset(100, 700);
	            contentStream.showText(content);
	            contentStream.endText();

	            contentStream.close();

	            // Save the updated PDF back to the same file path
	            document.save(filePath);
	            document.close();

	            return "PDF updated successfully at: " + filePath;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Failed to update the PDF.";
	        }
	    }
}

