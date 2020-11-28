package com.databaseproject.library_system.web;

import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReaderController {
    @Autowired
    ReaderService readerService;

    @PostMapping("/reader")
    public Reader post(Reader reader) {
        return readerService.addReader(reader);
    }

    @DeleteMapping("/reader/{id}")
    public void delete(@PathVariable long reader_id) {
        readerService.deleteReader(reader_id);
    }
}
