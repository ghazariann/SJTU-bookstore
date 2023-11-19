package com.bookstore.bookstore_backend.daoImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bookstore.bookstore_backend.dao.BookDao;
import com.bookstore.bookstore_backend.entity.Book;
import com.bookstore.bookstore_backend.repository.BookRepository;
import com.bookstore.bookstore_backend.service.RedisStatusService;

import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;
    private final RedisStatusService redisStatusService;
    private final RedisTemplate<String, String> redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Book save(Book book) {
        Book savedBook = bookRepository.save(book);
        if (redisStatusService.isRedisAvailable()) {
            redisTemplate.opsForValue().set("book" + savedBook.getId(), JSON.toJSONString(savedBook));
            redisTemplate.delete("bookList"); // booklist is changed, delete cache
        }
        return savedBook;
    }

    @Override
    public Book findById(long id) {
        long startTime, endTime;

        if (redisStatusService.isRedisAvailable()) {
            startTime = System.currentTimeMillis();
            String cachedBook = redisTemplate.opsForValue().get("book" + id);
            endTime = System.currentTimeMillis();
            if (cachedBook != null) {
                logger.info("Time taken for findById operation with Redis: {} ms", (endTime - startTime));
                logger.info("Book: {} is in Redis", id);
                return JSON.parseObject(cachedBook, Book.class);
            }
        }

        startTime = System.currentTimeMillis();
        Book book = bookRepository.findById(id).orElse(null);
        endTime = System.currentTimeMillis();
        logger.info("Time taken for findById operation without Redis: {} ms", (endTime - startTime));

        if (redisStatusService.isRedisAvailable()) {
            redisTemplate.opsForValue().set("book" + id, JSON.toJSONString(book));
        }

        return book;
    }

    @Override
    public List<Book> findAll() {
        long startTime, endTime;

        if (redisStatusService.isRedisAvailable()) {
            startTime = System.currentTimeMillis();
            String cachedBookList = redisTemplate.opsForValue().get("bookList");
            endTime = System.currentTimeMillis();

            if (cachedBookList != null) {
                logger.info("Time taken for findAll operation with Redis: {} ms", (endTime - startTime));
                logger.info("Listed books from Redis");
                return JSON.parseObject(cachedBookList, new TypeReference<List<Book>>() {
                });
            }
        }

        startTime = System.currentTimeMillis();
        List<Book> bookList = bookRepository.findAll();
        endTime = System.currentTimeMillis();
        logger.info("Time taken for findAll operation without Redis: {} ms", (endTime - startTime));

        if (redisStatusService.isRedisAvailable()) {
            redisTemplate.opsForValue().set("bookList", JSON.toJSONString(bookList));
        }

        return bookList;
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
        if (redisStatusService.isRedisAvailable()) {
            redisTemplate.delete("book" + id);
            redisTemplate.delete("bookList");
        }
    }
}