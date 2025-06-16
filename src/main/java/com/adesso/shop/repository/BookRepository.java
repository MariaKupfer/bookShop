package com.adesso.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adesso.shop.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
