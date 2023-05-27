package com.lzl.wj.vo;

import com.lzl.wj.pojo.Talk;

import java.util.List;

public class TalkVo implements Comparable<TalkVo>{
    Talk talk;
    String imageLocation;
    String userName;
    List<String> imgs;

    public Talk getTalk() {
        return talk;
    }

    public void setTalk(Talk talk) {
        this.talk = talk;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public TalkVo() {
    }

    public TalkVo(Talk talk, String imageLocation, String userName) {
        this.talk = talk;
        this.imageLocation = imageLocation;
        this.userName = userName;
    }

    public TalkVo(Talk talk, String imageLocation, String userName, List<String> imgs) {
        this.talk = talk;
        this.imageLocation = imageLocation;
        this.userName = userName;
        this.imgs = imgs;
    }

    @Override
    public int compareTo(TalkVo o) {
        return talk.compareTo(o.talk);
    }
}
