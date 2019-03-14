package com.sha.fastercode.repository;

import com.sha.fastercode.dto.ReportFilter;
import com.sha.fastercode.model.Transaction;

import java.util.List;

public interface TransactionRepository  extends IGenericDao<Transaction> {
    List<Transaction> filter(ReportFilter reportFilter);

    List<Long> filterIds(ReportFilter reportFilter);
}
