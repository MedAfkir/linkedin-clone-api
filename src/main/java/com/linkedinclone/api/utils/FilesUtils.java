package com.linkedinclone.api.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FilesUtils {

    private static final int  SIZE_LIMIT = 500 * 1024;

    /**
     * generate random file name contains 10 characters
     * @return
     */
    public static String generateName() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replaceAll("-", "");
        return uuidStr.substring(0, 10);
    }

    /**
     * convert multipart file to a file
     * @param multipartFile
     * @return File
     * @throws IOException
     */
    public static File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = null;
        if(multipartFile != null) {
            file = new File(multipartFile.getOriginalFilename());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(multipartFile.getBytes());
            outputStream.close();
        }
        return file;
    }

}