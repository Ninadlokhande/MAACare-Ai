package com.sigma.controller;

import java.io.File;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sigma.config.CloudinaryConfig;

public class ImageUploadController {
    public String imageUpload(File file){

        try {

            Cloudinary cloudinary = CloudinaryConfig.getCloudinary();

            Map<String , Object> result = cloudinary.uploader().upload(file,ObjectUtils.asMap("resource_type","image"));

            System.out.println(result);

            String url = String.valueOf(result.get("secure_url"));
            System.out.println(" Uploaded Image Url : "+url);
            return url;
        } catch (Exception e) {
            System.out.println("Cloudinary Upload Failed" + e.getMessage());
            return null;
        }
    }


    
}
