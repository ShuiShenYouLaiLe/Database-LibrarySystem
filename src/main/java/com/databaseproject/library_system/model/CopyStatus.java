package com.databaseproject.library_system.model;

public enum CopyStatus {
    Missed(0), Reserved(1), Borrowed(3), Awaiting(4);
    private final int status;
    CopyStatus(int status) {
        this.status = status;
    }
    public int getStatusValue() {
        return status;
    }
}
