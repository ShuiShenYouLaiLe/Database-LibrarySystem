package com.databaseproject.library_system.model;

public enum DocType {
    Book(1), JournalVolume(2), Proceedings(3);
    private final int typeName;
    DocType(int typeName) {
        this.typeName = typeName;
    }

    public int getTypeName() {
        return typeName;
    }
}
