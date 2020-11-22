package com.databaseproject.library_system.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByAuthorAndStatus(String author, int status);
    List<Book> findByDescriptionEndsWith(String des);
    //List<Book> findByDescriptionContains(String des);

    //JPQL style for this: @Query("SELECT b FROM Book b where b.id  > ?1")
    @Query(value = "SELECT * FROM book where id > ?1", nativeQuery = true)
    List<Book> findByJPQL(long len);

    //update book set author='烤大蛤' where id = 3
    @Modifying
    //JPQL style for this: @Query("update Book b set b.author = ?1 where b.id = ?2")
    @Query(value = "update book set author = ?1 where id = ?2", nativeQuery = true)
    int updateByJPQL(String author, long id);

    @Modifying
    //JPQL style for this: @Query("delete from Book b where b.id = ?1")
    @Query(value = "delete from book where id = ?1", nativeQuery = true)
    int deleteByJPQL(long id);
}
