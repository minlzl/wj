package com.lzl.wj.controller;

import com.lzl.wj.pojo.Talk;
import com.lzl.wj.result.Result;
import com.lzl.wj.result.ResultFactory;
import com.lzl.wj.service.RecommendService;
import com.lzl.wj.service.TalkService;
import com.lzl.wj.vo.TalkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @RequestMapping("/getRecommend")
    public Result getRecommend() {
        List<TalkVo> recommend = recommendService.getRecommend();
        return ResultFactory.buildSuccessResult(recommend);
    }
}
