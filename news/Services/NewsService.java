package com.example.news.Services;

import com.example.news.Model.News;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsService {


    ArrayList<News> newsList = new ArrayList<>();

    public ArrayList<News> getNews(){
        return newsList;
    }

    public void addNews(News news){
        newsList.add(news);
    }

    public boolean updateNews(int id, News news){

        for(int i = 0; i < newsList.size(); i++){
            if(newsList.get(i).getId() == id){
                newsList.set(i, news);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNews(int id){

        for(int i = 0; i < newsList.size(); i++){
            if(newsList.get(i).getId() == id){
                newsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publish(int id) {
        for (int i = 0; i < newsList.size(); i++) {
            if (newsList.get(i).getId() == id) {

                if (!newsList.get(i).isPublished()) {

                    newsList.get(i).setPublished(true);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<News> allPublished(){
        ArrayList<News> publishedList = new ArrayList<>();
        for (News news : newsList){
            if (news.isPublished()){
                news.setDate(LocalDate.now());
                publishedList.add(news);

            }
        }
        return publishedList;
    }
    public ArrayList<News> getAllByCategory(String category){
        ArrayList<News> categoryList = new ArrayList<>();
        if(category.equals("politics") || category.equals("sports") || category.equals("technology")) {

            for (News news : newsList) {
                if (news.getCategory().equals(category)) {
                    categoryList.add(news);

                }
            }
        }
        return categoryList;
    }


}
