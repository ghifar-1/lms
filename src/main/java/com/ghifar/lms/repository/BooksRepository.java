package com.ghifar.lms.repository;

import com.ghifar.lms.models.Books;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface BooksRepository extends JpaRepository<Books, Integer> {
}
