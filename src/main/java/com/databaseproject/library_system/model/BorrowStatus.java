package com.databaseproject.library_system.model;

public enum BorrowStatus {
    BORROWED(0), RETURNED(1);
    private final int status;
    BorrowStatus(int status) {
        this.status = status;
    }
    public int getStatusValue() {
        return status;
    }
}
