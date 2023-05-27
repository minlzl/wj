package com.lzl.wj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lzl.wj.dao.TalkImgDao;
import com.lzl.wj.dao.UserFollowsDao;
import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.pojo.Talk;
import com.lzl.wj.vo.TalkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TalkMainService {

    @Autowired
    UserTalksService userTalksService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserFollowsDao userFollowsDao;

    @Autowired
    TalkImgDao talkImgDao;

    @Autowired
    UuserDao uuserDao;

    public List<Talk> getShowTalks(Integer userId) throws JsonProcessingException {
        List<Integer> allFollows = userFollowsDao.getAllFollows(userId);
        List<Talk> talks = new ArrayList<>();
        if(userTalksService.getTalks(userId) != null) {
            talks.addAll(userTalksService.getTalks(userId));
        }
        for (int id : allFollows) {
            if (id != userId) {
                List<Talk> talks1 = userTalksService.getTalks(id);
                talks.addAll(talks1);
            }
        }
        Collections.sort(talks);
        return talks;
    }

    public List<TalkVo> getShowTalkVos(int userId, Integer currentPage, Integer pageSize) {
        List<TalkVo> talkVos = new ArrayList<>();
        List<Integer> allFollows = userFollowsDao.getAllFollows(userId);
        Set<Integer> set = new HashSet<>(allFollows);
        set.add(userId);
        for (int id : set) {
            List<Talk> talks1 = userTalksService.getTalks(id);
            for (Talk talk : talks1) {
                String name = uuserDao.getById(id);
                String image = imageService.getImage(talk.getUser_id());
                List<String> imgs = talkImgDao.getImgs(talk.getTalk_id());
                TalkVo talkVo = new TalkVo(talk, image, name, imgs);
                talkVos.add(talkVo);
            }
        }
        Collections.sort(talkVos);
        if (currentPage != null && pageSize != null) {
            List<TalkVo> list = new ArrayList<>();
            for(int i = (currentPage - 1) * pageSize; i < currentPage * pageSize && i < talkVos.size(); i++) {
                list.add(talkVos.get(i));
            }
            return list;
        }
        return talkVos;
    }
}
