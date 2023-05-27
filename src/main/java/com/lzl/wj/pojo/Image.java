package com.lzl.wj.pojo;

public class Image {
    Integer id;
    String userName;
    String imageLocation;

    public Image() {
    }

    public Image(Integer id, String userName, String imageLocation) {
        this.id = id;
        this.userName = userName;
        this.imageLocation = imageLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }
}
