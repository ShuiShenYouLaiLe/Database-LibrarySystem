package com.databaseproject.library_system.model;

public enum CopyStatus {
    MISSED(0), RESERVED(1), BORROWED(3), AVAILABLE(4);
    private final int status;
    CopyStatus(int status) {
        this.status = status;
    }
    public int getStatusValue() {
        return status;
    }
}
