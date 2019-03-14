package com.sha.fastercode.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReportFilter {

    private List<Long> libraryList;
    private String freeText;
    private LocalDateTime localDateTime;

    public List<Long> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<Long> libraryList) {
        this.libraryList = libraryList;
    }

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
