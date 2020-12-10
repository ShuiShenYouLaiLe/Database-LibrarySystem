package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Branch;
import com.databaseproject.library_system.domain.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{
    @Autowired
    BranchRepository branchRepository;

    @Override
    public Branch getBranchById(long bid) {
        return branchRepository.findById(bid).get();
    }

    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }
}
