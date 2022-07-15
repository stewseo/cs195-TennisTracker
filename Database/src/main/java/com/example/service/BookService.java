package com.example.service;

import org.springframework.transaction.annotation.Transactional;


public interface BookService {

    @Transactional
    void create(int id, int authorId, String title);

}