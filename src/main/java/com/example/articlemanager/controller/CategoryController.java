package com.example.articlemanager.controller;


import com.example.articlemanager.pojo.Category;
import com.example.articlemanager.sevice.CategoryService;
import com.example.articlemanager.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> categoryList=categoryService.getList();
        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category=categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
