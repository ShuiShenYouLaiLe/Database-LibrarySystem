package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Reader;

import java.util.List;

public interface ReaderService {
    Reader getReaderById(long id);
    Reader addReader(Reader reader);
    void deleteReader(long id);
    List<Reader> getAll();
}
