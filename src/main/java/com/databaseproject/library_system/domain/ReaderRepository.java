package com.databaseproject.library_system.domain;

import com.databaseproject.library_system.model.BorrowerWithCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long>{
    @Query(value = " SELECT reader_name, reader_id, " +
            "COUNT(*) AS Borrowed_book_count " +
            "FROM (reader NATURAL JOIN borrow_transaction) " +
            "WHERE BID=?1 GROUP BY reader_id ORDER BY COUNT(*) DESC LIMIT ?2",
            nativeQuery = true)
    List<Object[]> findTopNByBranch(long bid, int num);

    @Query(value = "SELECT reader_name, reader_id, " +
            "COUNT(*) AS Borrowed_book_count " +
            "FROM (reader NATURAL JOIN borrow_transaction) " +
            "GROUP BY reader_id ORDER BY COUNT(*) DESC  LIMIT ?1",
            nativeQuery = true)
    List<Object[]> findTopNFromAll(int num);

    @Query(value = "SELECT title, COUNT(*) AS Borrowed_times " +
            "FROM (document NATURAL JOIN borrow_transaction) " +
            "WHERE bid=?1 GROUP BY doc_id ORDER BY COUNT(*) DESC LIMIT ?2",
            nativeQuery = true)
    List<Object[]> findTopNDocFromBranch(long bid, int num);

    @Query(value = "SELECT title, COUNT(*) AS Borrowed_times " +
            "FROM (document NATURAL JOIN borrow_transaction) " +
            "GROUP BY doc_id ORDER BY COUNT(*) DESC LIMIT ?1",
            nativeQuery = true)
    List<Object[]> findTopNDocFromAll(int num);

    @Query(value = " SELECT title, COUNT(*) AS Borrowed_times " +
            "FROM (document NATURAL JOIN borrow_transaction) " +
            "WHERE ret_date_time LIKE ?1 AND doc_type=1 " +
            "GROUP BY doc_id ORDER BY COUNT(*) DESC LIMIT 10",
            nativeQuery = true)
    List<Object[]> findTopTenDocByYear(String year);

    @Query(value = "SELECT bid, name, bor_date_time,ret_date_time " +
            "    FROM (branch NATURAL JOIN  borrow_transaction) " +
            "    WHERE  (bor_date_time>=?1 AND bor_date_time<=?2 )",
            nativeQuery = true)
    List<Object[]> findTopTenDocByYear(String from, String to);



}
