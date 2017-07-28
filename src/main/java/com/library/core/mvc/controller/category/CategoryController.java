package com.library.core.mvc.controller.category;

import com.library.core.mvc.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 28.07.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("api/open/category")
public class CategoryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    protected CategoryService service;
}
