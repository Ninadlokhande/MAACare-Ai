package com.sigma.controller.doctorController;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sigma.config.CloudinaryConfig;

public class ImageUploadController {

    public String imageUpload(File file) {

        if (file == null) {
            System.out.println("[CLOUDINARY] File is null");
            return null;
        }

        try {

            Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

            String fileName = file.getName().toLowerCase();

            String resourceType;

            // =====================================================
            // PDF -> IMAGE
            // =====================================================
            if (fileName.endsWith(".pdf")) {

                resourceType = "image";

            }
            // =====================================================
            // IMAGE -> IMAGE
            // =====================================================
            else if (fileName.endsWith(".png")
                    || fileName.endsWith(".jpg")
                    || fileName.endsWith(".jpeg")) {

                resourceType = "image";

            }
            // =====================================================
            // OTHER FILES
            // =====================================================
            else {

                resourceType = "auto";
            }

            System.out.println("==========================================");
            System.out.println("[CLOUDINARY] Uploading file: "
                    + file.getName());

            System.out.println("[CLOUDINARY] File path: "
                    + file.getAbsolutePath());

            System.out.println("[CLOUDINARY] Resource type: "
                    + resourceType);

            // =====================================================
            // UPLOAD
            // =====================================================

            Map<String, Object> result = cloudinary.uploader().upload(
                    file,
                    ObjectUtils.asMap(
                            "resource_type",
                            resourceType));

            System.out.println("[CLOUDINARY] Upload Result:");
            System.out.println(result);

            // =====================================================
            // GET SECURE URL
            // =====================================================

            Object secureUrlObject = result.get("secure_url");

            if (secureUrlObject == null) {

                System.out.println(
                        "[CLOUDINARY] secure_url is null");

                return null;
            }

            String secureUrl = String.valueOf(secureUrlObject).trim();

            System.out.println("[CLOUDINARY] Secure URL:");
            System.out.println(secureUrl);

            // =====================================================
            // CHECK DELIVERY TYPE
            // =====================================================

            if (secureUrl.contains("/image/upload/")) {

                System.out.println(
                        "[CLOUDINARY] Image delivery URL generated.");

            } else if (secureUrl.contains("/raw/upload/")) {

                System.out.println(
                        "[CLOUDINARY] WARNING: Raw delivery URL generated.");

            } else {

                System.out.println(
                        "[CLOUDINARY] WARNING: Unknown delivery URL.");
            }

            System.out.println("==========================================");

            return secureUrl;

        } catch (Exception e) {

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