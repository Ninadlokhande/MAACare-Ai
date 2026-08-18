package com.sigma.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {

    public static Cloudinary cloudinary;

    public static Cloudinary getCloudinary(){

        if(cloudinary==null){

            Map<String , Object> config = new HashMap<>();

            config.put("cloud_name","mfs2z2nr");
            config.put("api_key","283539722735734");
            config.put("api_secret", "wmuGONc0oBxSRlxuQulDdFpL8Lc");
            config.put("secure",true);

            cloudinary = new Cloudinary(config);

        }

        return cloudinary;
    }








    
}
