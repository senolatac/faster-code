package com.sha.fastercode.service;

import com.sha.fastercode.dto.ReportFilter;
import com.sha.fastercode.model.Book;
import com.sha.fastercode.model.Library;
import com.sha.fastercode.model.Member;
import com.sha.fastercode.model.Transaction;

import java.util.List;

public interface LibraryService {
    List<Library> getAll();

    List<Book> getAllBooks();

    List<Member> getAllMembers();

    List<Transaction> filterTransactions(ReportFilter reportFilter);

    List<Long> filterTransactionIds(ReportFilter reportFilter);
}
