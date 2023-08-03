package com.example.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PdfController {
	@Value("${spring.servlet.multipart.location}")
	private String location;

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





