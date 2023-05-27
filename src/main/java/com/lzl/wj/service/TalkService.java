package com.lzl.wj.service;

import com.lzl.wj.dao.ChangeDao;
import com.lzl.wj.dao.TalkDao;
import com.lzl.wj.dao.UserTalksDao;
import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.pojo.Talk;
import com.lzl.wj.vo.TalkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkService {

    @Autowired
    TalkDao talkDao;

    @Autowired
    ChangeDao changeDao;

    @Autowired
    UuserDao uuserDao;

    @Autowired
    ImageService imageService;


    public Talk saveTalk(Talk talk) {
        return talkDao.save(talk);
    }
    public Talk findById(Integer id) {
        return talkDao.findById(id).orElse(null);
    }
    public TalkVo findVoById(Integer id) {
        Talk talk = talkDao.findById(id).orElse(null);
        if (talk == null) {
            return null;
        }
        String name = uuserDao.getById(talk.getUser_id());
        String image = imageService.getImage(talk.getUser_id());
        return new TalkVo(talk, image, name);
    }
    public boolean deleteById(Integer id) {
        talkDao.deleteById(id);
        return true;
    }
    public List<Talk> getAllTalks(List<Integer> list) {
        return talkDao.findAllById(list);
    }
    public List<Talk> getTalks() {
        return talkDao.findAll();
    }
    public void addTalkCommentNumber(Integer id) {
        changeDao.addTalkCommentNumber(id);
    }

}
