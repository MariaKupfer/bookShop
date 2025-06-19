package com.adesso.shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.shop.domain.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>{

    List<BookEntity> findByTitle(String title);
}
