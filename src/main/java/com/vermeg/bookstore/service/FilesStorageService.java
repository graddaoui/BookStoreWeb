package com.vermeg.bookstore.service;

import com.vermeg.bookstore.controllers.BookController;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

@Service()
public class FilesStorageService {

    private final Path root = Paths.get(BookController.uploadDirectory);

    public String save(MultipartFile file) throws Exception {

            try {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                return file.getOriginalFilename();
            }

            catch (Exception e) {
                throw new Exception(e);
            }


    }

}
