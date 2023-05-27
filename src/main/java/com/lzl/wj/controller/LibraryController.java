package com.lzl.wj.controller;

import com.lzl.wj.pojo.Image;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.ImageService;
import com.lzl.wj.service.UserService;
import com.lzl.wj.utils.CookieUtils;
import com.lzl.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @RequestMapping("/covers")
    public Result coversUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        System.out.println(1);
        Cookie[] cookies = request.getCookies();
        String userName = cookieUtils.getUserName(cookies);
        if(userName == null) {
            return ResultFactory.buildFailResult("fail");
        }
//        System.out.println(41);
        int id = userService.getByName(userName).getId();
//        String folder = "/home/image";
        String folder = "D:/image";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(8) + file.getOriginalFilename()
        .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
//        System.out.println(50);
        try {
            file.transferTo(f);
//            String imgURL = "http://121.36.23.219:8442/" + f.getName();
            String imgURL = "http://liuzile.com:8443/api/file/" + f.getName();
            Image image = new Image(id, userName, imgURL);
            imageService.addImage(image);
//            System.out.println(56);
            return ResultFactory.buildSuccessResult(imgURL);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultFactory.buildFailResult("f");
        }
    }
}
