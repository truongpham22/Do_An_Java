package com.example.DoAnJava.controller;

import com.example.DoAnJava.services.FirebaseService;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class FirebaseController {
    @Autowired
    private FirebaseService firebaseService;

    // TODO: Upload one file
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        String storageFileName = UUID.randomUUID().toString() + "_" + fileName;

        BlobId blobId = BlobId.of("upload-img-81776.appspot.com", storageFileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(contentType)
                .build();

        Storage storage = StorageClient.getInstance().bucket().getStorage();
        storage.create(blobInfo, file.getBytes());

        Blob blob = storage.get(blobId);
        URL signedUrl = blob.signUrl(2000, TimeUnit.DAYS);

        String fileUrl = signedUrl.toString();
        return new ResponseEntity<>(fileUrl, HttpStatus.OK);
    }


    @PostMapping("/upload-multi")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("file") List<MultipartFile> files) throws IOException {

        var fileUrls = firebaseService.uploadImages(files);
        return new ResponseEntity<>(fileUrls, HttpStatus.OK);
    }
}
