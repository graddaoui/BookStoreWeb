package com.vermeg.bookstore.controllers;

import com.vermeg.bookstore.models.Filename;
import com.vermeg.bookstore.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/files"})
public class FilesController {

    @Autowired
    FilesStorageService storageService;

    @PostMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return new Filename(storageService.save(file));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
