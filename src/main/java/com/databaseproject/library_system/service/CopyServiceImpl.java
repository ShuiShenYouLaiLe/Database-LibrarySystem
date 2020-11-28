package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.Copy;
import com.databaseproject.library_system.domain.CopyRepository;
import com.databaseproject.library_system.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService{
    @Autowired
    CopyRepository copyRepository;

    @Override
    public List<Copy> findCopiesByDocument(Document document) {
        return copyRepository.findCopiesByDocument(document);
    }

    @Override
    public Copy save(Copy copy) {
        return copyRepository.save(copy);
    }

    @Override
    public void remove(Copy copy) {
        copyRepository.delete(copy);
    }

    @Override
    public List<Copy> findCopiesByBranch(Branch branch) {
        return copyRepository.findCopiesByBranch(branch);
    }
}
