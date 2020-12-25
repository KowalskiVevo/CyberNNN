package com.postulate7.demo.repo;

import com.postulate7.demo.models.News;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

public interface NewsRepos extends CrudRepository<News,Long>{
}