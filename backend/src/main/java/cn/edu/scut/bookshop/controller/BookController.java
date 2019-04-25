package cn.edu.scut.bookshop.controller;

import cn.edu.scut.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController
{
    @Autowired
    private BookService bookService;
    
    
}
