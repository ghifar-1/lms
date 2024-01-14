package com.ghifar.lms.controllers;

import com.ghifar.lms.models.Books;
import com.ghifar.lms.models.Borrow;
import com.ghifar.lms.models.User;
import com.ghifar.lms.repository.BooksRepository;
import com.ghifar.lms.repository.BorrowRepository;
import com.ghifar.lms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @PostMapping("/borrow/{bookId}/patron/{patronID}")
    public String borrowBook(@PathVariable Integer bookId,@PathVariable Integer patronID) {
        User user = usersRepository.findById(patronID).get();
        Books book = booksRepository.findById(bookId).get();

        if (book.getNoOfCopies() < 1) {
            return "The book \"" + book.getBookName() + "\" is out of stock!";
        }

        Borrow borrow = new Borrow();
        borrow.setBookId(book.getBookId());
        borrow.setUserId(user.getId());
        book.borrowBook();
        booksRepository.save(book);

        Date currentDate = new Date();
        Date overdueDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(overdueDate);
        c.add(Calendar.DATE, 7);
        overdueDate = c.getTime();
        borrow.setIssueDate(currentDate);
        borrow.setDueDate(overdueDate);
        borrowRepository.save(borrow);
        return user.getUsername() + " has borrowed one copy of \"" + book.getBookName() + "\"!";
    }

    @GetMapping("/borrow")
    public List<Borrow> getAllBorrow() {
        return borrowRepository.findAll();
    }

    @PutMapping("/returnBook")
    public Borrow returnBook(@RequestBody Borrow borrow) {
        Borrow borrowBook = borrowRepository.findById(borrow.getBorrowId()).get();
        Books book = booksRepository.findById(borrowBook.getBookId()).get();
        book.returnBook();
        booksRepository.save(book);
        Date currentDate = new Date();
        borrowBook.setReturnDate(currentDate);
        return borrowRepository.save(borrowBook);
    }

    @GetMapping("user/{id}")
    public List<Borrow> booksBorrowedByUser(@PathVariable Integer id) {
        return borrowRepository.findByUserId(id);
    }

    @GetMapping("book/{id}")
    public List<Borrow> bookBorrowHistory(@PathVariable Integer id) {
        return borrowRepository.findByBookId(id);
    }

}
