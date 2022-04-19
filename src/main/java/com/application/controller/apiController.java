package com.application.controller;

import com.application.entity.title;
import com.application.repo.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class apiController {

    @Autowired
    TitleRepository titleRepository;

    @RequestMapping("/findAll")
    public List<title> findAll() {
        return titleRepository.findAll();
    }
}
