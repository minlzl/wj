package com.lzl.wj.service;

import com.lzl.wj.dao.TalkImgDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TalkImgService {

    @Autowired
    TalkImgDao talkImgDao;

    public void addImgs(int talk_id, String img) {
        talkImgDao.addImgs(talk_id, img);
    }
}
