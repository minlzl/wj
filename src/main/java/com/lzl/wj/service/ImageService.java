package com.lzl.wj.service;

import com.lzl.wj.dao.ImageDao;
import com.lzl.wj.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    ImageDao imageDao;

//    private static final String DEFAULT = "http://121.36.23.219:8442/default.png";
    private static final String DEFAULT = "http://localhost:8443/api/file/default.png";
    public String getImage(Integer id) {
        if (id == null) {
            return DEFAULT;
        }
        String image = imageDao.getImage(id);
        if (image == null || "".equals(image)) {
            return DEFAULT;
        }
        return image;
    }

    public void addImage(Image image) {
        imageDao.addImage(image);
    }
}
