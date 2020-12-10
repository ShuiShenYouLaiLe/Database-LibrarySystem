package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.domain.ReaderRepository;
import com.databaseproject.library_system.model.BorrowedBookWithCount;
import com.databaseproject.library_system.model.BorrowerWithCount;
import com.databaseproject.library_system.service.ReaderService;
import com.databaseproject.library_system.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReaderController {
    @Autowired
    ReaderService readerService;

    @Autowired
    ReaderRepository readerRepository;

    @PostMapping("/reader")
    public Reader post(Reader reader) {
        return readerService.addReader(reader);
    }

    @DeleteMapping("/reader/{reader_id}")
    public void delete(@PathVariable long reader_id) {
        readerService.deleteReader(reader_id);
    }

    @GetMapping("/topN/branch")
    public List<BorrowerWithCount> getTopNByBranch(@RequestParam Integer N, @RequestParam Long I, Model model) {
        List<Object[]> list = readerRepository.findTopNByBranch(I, N);
        List<BorrowerWithCount> res = EntityUtils.castEntity(list, BorrowerWithCount.class, new BorrowerWithCount());
        return res;
    }

    @GetMapping("/topN")
    public List<BorrowerWithCount> getTopNFromAll(@RequestParam int N) {
        List<Object[]> list = readerRepository.findTopNFromAll(N);
        List<BorrowerWithCount> res = EntityUtils.castEntity(list, BorrowerWithCount.class, new BorrowerWithCount());
        return res;
    }

    @GetMapping("/topN/branch/document")
    public List<BorrowedBookWithCount> getTopNDocByBranch(@RequestParam Integer N, @RequestParam Long I, Model model) {
        List<Object[]> list = readerRepository.findTopNDocFromBranch(I, N);
        List<BorrowedBookWithCount> res = EntityUtils.castEntity(list, BorrowedBookWithCount.class, new BorrowedBookWithCount());
        return res;
    }

    @GetMapping("/top10/year")
    public List<BorrowedBookWithCount> getTopNFromAll(@RequestParam String year) {
        year = year + "%";
        List<Object[]> list = readerRepository.findTopTenDocByYear(year);
        List<BorrowedBookWithCount> res = EntityUtils.castEntity(list, BorrowedBookWithCount.class, new BorrowedBookWithCount());
        return res;
    }
}
