package com.databaseproject.library_system.model;

import com.databaseproject.library_system.domain.Publisher;
import sun.util.calendar.LocalGregorianCalendar;

import java.sql.Timestamp;

public interface DocumentInterface {
    public void setDocId(long docId);
    public void setTitle(String title);
    public void setPDate(Timestamp pDate);
    public void setDocType(int docType);
    public void setPublisher(Publisher publisher);
}
