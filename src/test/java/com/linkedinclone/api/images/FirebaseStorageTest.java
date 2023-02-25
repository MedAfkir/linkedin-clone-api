package com.linkedinclone.api.images;

import com.linkedinclone.api.services.images.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

@SpringBootTest
public class FirebaseStorageTest {

    @Autowired
    private ImageService imageService;


    @Test
    public void testUploadImage() throws Exception {



    }
}






