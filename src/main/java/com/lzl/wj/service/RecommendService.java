package com.lzl.wj.service;

import com.lzl.wj.dao.TalkImgDao;
import com.lzl.wj.dao.UuserDao;
import com.lzl.wj.pojo.Talk;
import com.lzl.wj.vo.TalkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RecommendService {

    @Autowired
    TalkService talkService;

    @Autowired
    ImageService imageService;

    @Autowired
    TalkImgDao talkImgDao;

    @Autowired
    UuserDao uuserDao;

    public List<TalkVo> getRecommend() {
        List<Talk> talks = talkService.getTalks();
        List<TalkVo> talkVos = new ArrayList<>();
        for (Talk talk : talks) {
            String image = imageService.getImage(talk.getUser_id());
            String byId = uuserDao.getById(talk.getUser_id());
            List<String> imgs = talkImgDao.getImgs(talk.getTalk_id());
            TalkVo talkVo = new TalkVo(talk, image, byId, imgs);
            talkVos.add(talkVo);
        }
        talkVos.sort(new Comparator<TalkVo>() {
            @Override
            public int compare(TalkVo o1, TalkVo o2) {
                if (o1.getTalk().getPublish_data().getTime() > o2.getTalk().getPublish_data().getTime()) {
                    return -1;
                }
                return 1;
            }
        });
//        talks.sort(new Comparator<Talk>() {
//            @Override
//            public int compare(Talk o1, Talk o2) {
//                if (o1.getPublish_data().getTime() > o2.getPublish_data().getTime()) {
//                    return -1;
//                }
//                return 1;
//            }
//        });
        return talkVos.subList(0, 10);
    }
}
