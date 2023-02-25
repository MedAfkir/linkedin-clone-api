package com.linkedinclone.api.services.images;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.linkedinclone.api.models.images.Image;
import com.linkedinclone.api.utils.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@Service
public class ImageService {

    @Value("${firebase.bucket}")
    private String bucket;
    @Value("${firebase.serviceAccountKeyPath}")
    private String servicePath;
    private BlobInfo blobInfo;
    private BlobId blobId;
    private Storage storage;


    @Autowired
    public void initialize() throws IOException {
        Credentials credentials = GoogleCredentials.fromStream(
                new FileInputStream(servicePath));
        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }


    public String uploadImage(File file, String name) throws IOException {
        blobId = BlobId.of(bucket, name);
        blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/png").build();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        Blob blob = storage.get(blobId);
        return blob.signUrl(15, TimeUnit.MINUTES).toString();
    }

    public void updateImage(File file, String name) throws IOException {
        blobId = BlobId.of(bucket, name);
        blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/png").build();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
    }


    public boolean deleteImage(String name) {
        return storage.delete(bucket, name);
    }
}
