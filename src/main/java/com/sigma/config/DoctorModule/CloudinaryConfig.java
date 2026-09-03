package com.sigma.config.DoctorModule;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {
    public static Cloudinary cloudinary;

    public static Cloudinary getCloudinary() {
        if (cloudinary == null) {

            Map<String, Object> config = new HashMap<>();
            config.put("cloud_name", "tr9lck69");
            config.put("api_key", "797449856749481");
            config.put("api_secret", "TRJdgOQq5CwEsekHpL6yfv2iKrI");
            config.put("secure", true);

            cloudinary = new Cloudinary(config);

        }
        return cloudinary;

    }

}
