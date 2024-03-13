package com.example.news.Controller;

import com.example.news.ApiResponse.ApiResponse;
import com.example.news.Model.News;
import com.example.news.Services.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {


    private final NewsService newsService;

    @GetMapping("/get")
    public ResponseEntity getNews(){
        ArrayList<News> news = newsService.getNews();
        return ResponseEntity.ok(news);
    }

    @PostMapping("post")
    public ResponseEntity addnews(@RequestBody @Valid News news, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        newsService.addNews(news);
        return ResponseEntity.ok(new ApiResponse("news added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNews(@PathVariable int id, @RequestBody @Valid News news, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        boolean isUpdated = newsService.updateNews(id, news);

        if (isUpdated) {
            return ResponseEntity.ok(new ApiResponse("news updated"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("news not found"));
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteNews(@PathVariable int id){



        boolean isDeleted = newsService.deleteNews(id);
        if (isDeleted){
            return ResponseEntity.ok(new ApiResponse("news deleted"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("news not found"));
        }

    }

    @PutMapping("publish/{id}")
    public ResponseEntity publish(@PathVariable int id){



        boolean isPublished = newsService.publish(id);
        if (isPublished){
            return ResponseEntity.ok(new ApiResponse("The new published successfully"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("news not found or it is already published"));
        }

    }


    @GetMapping("/get/publish/all")
    public ResponseEntity getPublished(){
        ArrayList<News> newsPublishList = newsService.allPublished();
        if (newsPublishList.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No published news found"));
        }
        return ResponseEntity.ok(newsPublishList);
    }

    @GetMapping("/get/category/all/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        ArrayList<News> newsCategory = newsService.getAllByCategory(category);
        if (newsCategory.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiResponse("No news found"));
        }
        return ResponseEntity.ok(newsCategory);
    }
}
