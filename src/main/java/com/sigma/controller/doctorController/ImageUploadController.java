package com.sigma.controller.doctorController;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sigma.config.CloudinaryConfig;

public class ImageUploadController {

    public String imageUpload(File file) {

        Cloudinary cloudinary = CloudinaryConfig.getCloudinary();
        try {
            Map<String, Object> result = cloudinary.uploader()
                    .upload(file, ObjectUtils.asMap("resource_type", "image"));
            System.out.println(result);
            String url = String.valueOf(result.get("secure_url"));
            System.out.println(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
