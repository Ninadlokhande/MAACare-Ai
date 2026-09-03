package com.sigma.controller;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sigma.config.CloudinaryConfig;

public class ImageUploadController {

    public String imageUpload(File file) {

        // =====================================================
        // VALIDATE FILE
        // =====================================================

        if (file == null) {
            System.out.println(
                    "[CLOUDINARY] File is null.");
            return null;
        }

        if (!file.exists() || !file.isFile()) {
            System.out.println(
                    "[CLOUDINARY] File does not exist: "
                            + file.getAbsolutePath());
            return null;
        }

        try {

            // =================================================
            // GET CLOUDINARY INSTANCE
            // =================================================

            Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

            // =================================================
            // UPLOAD FILE
            // =================================================
            //
            // "auto" supports:
            // PDF
            // JPG
            // JPEG
            // PNG
            // etc.
            //
            // =================================================

            Map<String, Object> result = cloudinary.uploader().upload(
                    file,
                    ObjectUtils.asMap(
                            "resource_type",
                            "auto"));

            System.out.println(
                    "[CLOUDINARY] Upload result:");

            System.out.println(result);

            // =================================================
            // GET SECURE URL
            // =================================================

            Object secureUrlObject = result.get("secure_url");

            if (secureUrlObject == null) {

                System.out.println(
                        "[CLOUDINARY ERROR] secure_url is null.");

                return null;
            }

            String url = String.valueOf(
                    secureUrlObject);

            if (url.trim().isEmpty()
                    || url.equalsIgnoreCase("null")) {

                System.out.println(
                        "[CLOUDINARY ERROR] Uploaded URL is empty.");

                return null;
            }

            System.out.println(
                    "[CLOUDINARY] Uploaded File URL:");

            System.out.println(url);

            return url.trim();

        } catch (Exception e) {

            System.out.println(
                    "[CLOUDINARY ERROR] Upload failed.");

            e.printStackTrace();

            return null;
        }
    }
}