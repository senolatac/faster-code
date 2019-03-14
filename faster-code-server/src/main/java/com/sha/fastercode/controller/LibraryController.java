package com.sha.fastercode.controller;

import com.sha.fastercode.dto.ReportFilter;
import com.sha.fastercode.dto.StringResponse;
import com.sha.fastercode.model.Library;
import com.sha.fastercode.model.Transaction;
import com.sha.fastercode.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping(path ="/library")
    public ResponseEntity<List<Library>> getLibraries() {
        return ResponseEntity.ok(libraryService.getAll());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public ResponseEntity<List<Transaction>> filter(@RequestBody ReportFilter reportFilter){
        return ResponseEntity.ok(libraryService.filterTransactions(reportFilter));
    }

    @RequestMapping(value = "/filter-ids", method = RequestMethod.POST)
    public ResponseEntity<StringResponse> filterIds(@RequestBody ReportFilter reportFilter){
        List<Long> ids = libraryService.filterTransactionIds(reportFilter);
        String result = "";
        for(Long l:ids){
            result+=l+", ";
        }
        return ResponseEntity.ok(new StringResponse(result));
    }

}
