package com.databaseproject.library_system.model;

public enum ReservationStatus {
    DELETED(0), RESERVED(1), FINISHED(3);
    private final int status;
    ReservationStatus(int status) {
        this.status = status;
    }
    public int getStatusValue() {
        return status;
    }
}
