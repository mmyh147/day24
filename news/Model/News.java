package com.example.news.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class News {

    @NotNull(message = "id can not be empty")
    private int id;
    @NotEmpty(message = "title can not be empty")
    @Size(max = 100, message = "title can not be more than 100 characters")
    private String title;
    @NotEmpty(message = "the author can not be empty")
    @Size(min = 5, max = 20, message = "author must be 5 to 20 characters")
    private String author;
    @NotEmpty(message = "content can not be empty")
    @Size(max = 201, message = "content must be more than 200 characters")
    private String content;
    @NotEmpty(message = "category can not be null")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Category must be either 'politics', 'sports' or 'technology'")
    private String category;
    @NotEmpty(message = "you should add image url")
    private String imageUrl;
    @AssertFalse(message = "must be false in initiate")
    private boolean isPublished;
    @Null
    private LocalDate date;
}
