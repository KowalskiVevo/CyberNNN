package com.postulate7.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.postulate7.demo.models.News;
import com.postulate7.demo.repo.NewsRepos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@ComponentScan("com.postulate7.repo")
public class NewsController {
    @Autowired
    private NewsRepos newsRepos;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<News> news = newsRepos.findAll();
        model.addAttribute("newss", news);
        return "home";
    }
    
    
    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String newsAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text,Model model){
        News news = new News (title,anons,full_text);
        newsRepos.save(news);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String newsDetails (@PathVariable(value = "id") long id, Model model){
        Optional<News> news = newsRepos.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("news", res);
        return "/detalis";
    }
    
    @GetMapping("/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!newsRepos.existsById(id)){
            return "redirect:/";
        }
        Optional<News> news = newsRepos.findById(id);
        ArrayList<News> res = new ArrayList<>();
        news.ifPresent(res::add);
        model.addAttribute("news", res);
        return "/edit";
    }
    
    @PostMapping("/{id}/edit")
    public String newsEditUpdate(@PathVariable(value = "id") long id,@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        News news = newsRepos.findById(id).orElseThrow(IllegalStateException::new);
        news.setTitle(title);
        news.setAnons(anons);
        news.setFull_text(full_text);
        newsRepos.save(news);

        return "redirect:/";
    }
    
    @PostMapping("/{id}/remove")
    public String newsEditRemove(@PathVariable(value = "id") long id, Model model) {
        News news = newsRepos.findById(id).orElseThrow(IllegalStateException::new);
        newsRepos.delete(news);
        return "redirect:/";
    }
}
