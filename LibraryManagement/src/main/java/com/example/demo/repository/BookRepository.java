package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book>  findByBookName(String bookName);
	
    public     List<Book> findByIsAvailable(Boolean isAvailable);
    
    

}
