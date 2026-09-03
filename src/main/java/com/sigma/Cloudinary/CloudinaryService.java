package com.sigma.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryService {

    private final Cloudinary cloudinary;
    private final String uploadPreset;

    public CloudinaryService() {

        System.out.println("[CLOUDINARY] Constructor started...");

        Dotenv dotenv = Dotenv.configure()
                .directory(".")
                .filename(".env")
                .ignoreIfMissing()
                .load();

        String cloudName =
                dotenv.get("CLOUDINARY_CLOUD_NAME");

        uploadPreset =
                dotenv.get("CLOUDINARY_UPLOAD_PRESET");

        System.out.println(
                "[CLOUDINARY] Cloud name = " + cloudName
        );

        System.out.println(
                "[CLOUDINARY] Upload preset = " + uploadPreset
        );

        cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName
                )
        );
    }

    public String uploadProfileImage(File imageFile)
            throws Exception {

        System.out.println(
                "[CLOUDINARY] Starting upload..."
        );

        System.out.println(
                "[CLOUDINARY] File = "
                + imageFile.getAbsolutePath()
        );

        Map uploadResult =
                cloudinary.uploader().upload(
                        imageFile,
                        ObjectUtils.asMap(
                                "upload_preset",
                                uploadPreset,
                                "folder",
                                "asha_profiles"
                        )
                );

        String imageUrl =
                uploadResult
                        .get("secure_url")
                        .toString();

        System.out.println(
                "[CLOUDINARY] Upload successful!"
        );

        System.out.println(
                "[CLOUDINARY] URL = " + imageUrl
        );

        return imageUrl;
    }
}
