package com.sha.fastercode.service;

import com.sha.fastercode.dto.ReportFilter;
import com.sha.fastercode.model.Book;
import com.sha.fastercode.model.Library;
import com.sha.fastercode.model.Member;
import com.sha.fastercode.model.Transaction;
import com.sha.fastercode.repository.BookRepository;
import com.sha.fastercode.repository.LibraryRepository;
import com.sha.fastercode.repository.MemberRepository;
import com.sha.fastercode.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Library> getAll() {
        final List<Library> libraryList = new ArrayList<>();
        libraryRepository.findAll().forEach(libraryList::add);
        return libraryList;
    }

    @Override
    public List<Book> getAllBooks(){
        final List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(bookList::add);
        return bookList;
    }

    @Override
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    @Override
    public List<Transaction> filterTransactions(final ReportFilter reportFilter){
        return transactionRepository.filter(reportFilter);
    }

    @Override
    public List<Long> filterTransactionIds(final ReportFilter reportFilter){
        return transactionRepository.filterIds(reportFilter);
    }
}
