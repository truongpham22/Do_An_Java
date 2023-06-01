package com.example.DoAnJava.services;

import com.example.DoAnJava.DoAnJavaApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseService {
    public void initialAppFireBase() throws IOException, FileNotFoundException {
        ClassLoader classLoader = DoAnJavaApplication.class.getClassLoader();

        InputStream serviceAccount = classLoader.getResourceAsStream("serviceAccount.json");

        FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setStorageBucket("upload-img-81776.appspot.com").build();
        for (FirebaseApp app : FirebaseApp.getApps()) {
            app.delete();
        }
        FirebaseApp app = FirebaseApp.initializeApp(options);
    }

    public List<String> uploadImages(List<MultipartFile> files) {
        List<String> fileUrls = new ArrayList<>();

        // Tạo đối tượng lưu trữ (Storage)
        Storage storage = StorageClient.getInstance().bucket().getStorage();

        // Thực hiện upload từng file một
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            String storageFileName = UUID.randomUUID().toString() + "_" + fileName;

            // Tạo đối tượng BlobId để định danh cho file
            BlobId blobId = BlobId.of("upload-img-81776.appspot.com", storageFileName);

            // Tạo đối tượng BlobInfo để cung cấp thông tin về file cần upload
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();

            // Đọc dữ liệu từ file và thực hiện upload lên Google Cloud Storage
            try {
                byte[] bytes = file.getBytes();
                storage.create(blobInfo, bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Tạo đường dẫn tới file đã upload và thêm vào danh sách đường dẫn
            Blob blob = storage.get(blobId);
            URL signedUrl = blob.signUrl(2000, TimeUnit.DAYS);
            String fileUrl = signedUrl.toString();
            fileUrls.add(fileUrl);
        }

        return fileUrls;
    }

}