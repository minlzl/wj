package com.lzl.wj.controller;

import com.lzl.wj.pojo.Image;
import com.lzl.wj.properties.ImgProperties;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.ImageService;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    ImageService imageService;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @Autowired
    ImgProperties imgProperties;

    @RequestMapping("/image/{id}")
    public Result getImage(@PathVariable Integer id) {
        String image = imageService.getImage(id);
        return ResultFactory.buildSuccessResult(image);
    }

    @RequestMapping("/upload")
    public Result talkImgUpload(@RequestParam("file") MultipartFile file) {
//        Cookie[] cookies = request.getCookies();
//        String userName = cookieUtils.getUserName(cookies);
//        if(userName == null) {
//            return ResultFactory.buildFailResult("fail");
//        }
//        int id = userService.getByName(userName).getId();
        System.out.println(1);
        String folder = imgProperties.getFolder();

//        String folder = "D:/image";
        System.out.println(folder);
        File imgFolder = new File(folder);
        File f = new File(imgFolder, StringUtils.getRandomString(8) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String baseUrl = imgProperties.getBaseUrl();
            String imgUrl = baseUrl + f.getName();
            return ResultFactory.buildSuccessResult(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultFactory.buildFailResult(null);
    }

}
