package com.sigma.config;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryDocumentUploader {

    public static String uploadDocument(File file) throws IOException {

        if (file == null || !file.exists()) {
            throw new IOException("Document file not found.");
        }

        // Existing Cloudinary configuration वापरत आहे
        Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file,
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "maacare/lab_reports"
                )
        );

        Object secureUrl = uploadResult.get("secure_url");

        if (secureUrl == null) {
            throw new IOException("Document upload failed. URL not received.");
        }

        return secureUrl.toString();
    }
}