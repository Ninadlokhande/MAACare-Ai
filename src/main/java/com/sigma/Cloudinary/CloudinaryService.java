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

        // Load .env file
        Dotenv dotenv = Dotenv.configure()
                .directory(".")
                .filename(".env")
                .ignoreIfMissing()
                .load();

        // Read Cloudinary configuration
        String cloudName =
                dotenv.get("CLOUDINARY_CLOUD_NAME");

        String apiKey =
                dotenv.get("CLOUDINARY_API_KEY");

        String apiSecret =
                dotenv.get("CLOUDINARY_API_SECRET");

        uploadPreset =
                dotenv.get("CLOUDINARY_UPLOAD_PRESET");

        // Debug information
        System.out.println(
                "[CLOUDINARY] Cloud name = " + cloudName
        );

        System.out.println(
                "[CLOUDINARY] Upload preset = " + uploadPreset
        );

        // Do NOT print API key or API secret
        if (cloudName == null || cloudName.isBlank()) {
            throw new IllegalStateException(
                    "CLOUDINARY_CLOUD_NAME is missing in .env"
            );
        }

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "CLOUDINARY_API_KEY is missing in .env"
            );
        }

        if (apiSecret == null || apiSecret.isBlank()) {
            throw new IllegalStateException(
                    "CLOUDINARY_API_SECRET is missing in .env"
            );
        }

        if (uploadPreset == null || uploadPreset.isBlank()) {
            throw new IllegalStateException(
                    "CLOUDINARY_UPLOAD_PRESET is missing in .env"
            );
        }

        // Initialize Cloudinary
        cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret
                )
        );

        System.out.println(
                "[CLOUDINARY] Cloudinary initialized successfully!"
        );
    }

    /**
     * Upload profile image to Cloudinary
     */
    public String uploadProfileImage(File imageFile)
            throws Exception {

        if (imageFile == null) {
            throw new IllegalArgumentException(
                    "Image file cannot be null."
            );
        }

        if (!imageFile.exists()) {
            throw new IllegalArgumentException(
                    "Image file does not exist: "
                            + imageFile.getAbsolutePath()
            );
        }

        System.out.println(
                "[CLOUDINARY] Starting upload..."
        );

        System.out.println(
                "[CLOUDINARY] File = "
                        + imageFile.getAbsolutePath()
        );

        // Upload image
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

        // Get secure URL
        Object secureUrl =
                uploadResult.get("secure_url");

        if (secureUrl == null) {
            throw new Exception(
                    "Cloudinary upload completed but secure_url was not returned."
            );
        }

        String imageUrl =
                secureUrl.toString();

        System.out.println(
                "[CLOUDINARY] Upload successful!"
        );

        System.out.println(
                "[CLOUDINARY] URL = " + imageUrl
        );

        return imageUrl;
    }
}