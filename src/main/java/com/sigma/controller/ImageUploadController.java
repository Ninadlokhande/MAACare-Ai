package com.sigma.controller;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sigma.config.CloudinaryConfig;

public class ImageUploadController {

        // ============================================================
        // UPLOAD FILE TO CLOUDINARY
        // ============================================================

        public String imageUpload(File file) {

                // ========================================================
                // CHECK FILE
                // ========================================================

                if (file == null) {

                        System.out.println(
                                        "[CLOUDINARY] File is null");

                        return null;
                }

                try {

                        // ====================================================
                        // GET CLOUDINARY INSTANCE
                        // ====================================================

                        Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

                        // ====================================================
                        // GET FILE NAME
                        // ====================================================

                        String fileName = file.getName().toLowerCase();

                        String resourceType;

                        // ====================================================
                        // PDF
                        // ====================================================
                        // IMPORTANT:
                        // PDF is uploaded as IMAGE resource type.
                        // This avoids the RAW PDF delivery issue.
                        // ====================================================

                        if (fileName.endsWith(".pdf")) {

                                resourceType = "image";
                        }

                        // ====================================================
                        // PNG / JPG / JPEG
                        // ====================================================

                        else if (fileName.endsWith(".png")
                                        || fileName.endsWith(".jpg")
                                        || fileName.endsWith(".jpeg")) {

                                resourceType = "image";
                        }

                        // ====================================================
                        // OTHER FILE TYPES
                        // ====================================================

                        else {

                                resourceType = "auto";
                        }

                        // ====================================================
                        // PRINT UPLOAD INFORMATION
                        // ====================================================

                        System.out.println(
                                        "==========================================");

                        System.out.println(
                                        "[CLOUDINARY] Uploading file: "
                                                        + file.getName());

                        System.out.println(
                                        "[CLOUDINARY] File path: "
                                                        + file.getAbsolutePath());

                        System.out.println(
                                        "[CLOUDINARY] Resource type: "
                                                        + resourceType);

                        // ====================================================
                        // UPLOAD FILE
                        // ====================================================

                        Map<String, Object> result = cloudinary.uploader().upload(
                                        file,
                                        ObjectUtils.asMap(
                                                        "resource_type",
                                                        resourceType));

                        // ====================================================
                        // PRINT UPLOAD RESULT
                        // ====================================================

                        System.out.println(
                                        "[CLOUDINARY] Upload Result:");

                        System.out.println(result);

                        // ====================================================
                        // GET SECURE URL
                        // ====================================================

                        Object secureUrlObject = result.get("secure_url");

                        if (secureUrlObject == null) {

                                System.out.println(
                                                "[CLOUDINARY] secure_url is null");

                                return null;
                        }

                        // ====================================================
                        // CONVERT URL TO STRING
                        // ====================================================

                        String secureUrl = String.valueOf(
                                        secureUrlObject).trim();

                        // ====================================================
                        // PRINT SECURE URL
                        // ====================================================

                        System.out.println(
                                        "[CLOUDINARY] Secure URL:");

                        System.out.println(
                                        secureUrl);

                        // ====================================================
                        // CHECK GENERATED URL TYPE
                        // ====================================================

                        if (secureUrl.contains("/image/upload/")) {

                                System.out.println(
                                                "[CLOUDINARY] Image delivery URL generated.");

                        } else if (secureUrl.contains("/raw/upload/")) {

                                System.out.println(
                                                "[CLOUDINARY] WARNING: Raw delivery URL generated.");

                        } else {

                                System.out.println(
                                                "[CLOUDINARY] Delivery URL type could not be determined.");
                        }

                        System.out.println(
                                        "==========================================");

                        // ====================================================
                        // RETURN URL
                        // ====================================================

                        return secureUrl;

                } catch (Exception e) {

                        // ====================================================
                        // UPLOAD ERROR
                        // ====================================================

                        System.out.println(
                                        "[CLOUDINARY] Upload failed");

                        System.out.println(
                                        "[CLOUDINARY] Error message: "
                                                        + e.getMessage());

                        e.printStackTrace();

                        return null;
                }
        }
}