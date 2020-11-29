package com.databaseproject.library_system.service;

import com.databaseproject.library_system.domain.BorrowTransaction;
import com.databaseproject.library_system.domain.Copy;

public interface BorrowService {
    BorrowTransaction update(BorrowTransaction borrowTransaction);
    BorrowTransaction getByCopy(Copy copy, int status);
    BorrowTransaction add(BorrowTransaction borrowTransaction);
}
