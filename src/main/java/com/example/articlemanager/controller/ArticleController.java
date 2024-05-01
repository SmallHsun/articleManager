package com.example.articlemanager.controller;


import com.example.articlemanager.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> getArticleList() {

//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
        return Result.success("所有文章");
//
//        }catch (Exception e){
//            httpServletResponse.setStatus(401);
//            return Result.error("未登錄");
//        }
//
//    }
    }
}
