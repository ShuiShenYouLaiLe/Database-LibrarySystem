package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.Branch;

import java.util.List;

public interface BranchService {
    Branch getBranchById(long bid);
    List<Branch> getAll();
}
