package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Reader;
import com.databaseproject.library_system.domain.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService{
    @Autowired
    private ReaderRepository readerRepository;
    @Override
    public Reader getReaderById(long id) {
        return readerRepository.findById(id).get();
    }

    @Override
    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public void deleteReader(long id) {
        readerRepository.deleteById(id);
    }

    @Override
    public List<Reader> getAll() {
        return readerRepository.findAll();
    }
}
