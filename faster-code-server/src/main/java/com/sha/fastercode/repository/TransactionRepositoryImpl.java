package com.sha.fastercode.repository;

import com.sha.fastercode.dto.ReportFilter;
import com.sha.fastercode.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class TransactionRepositoryImpl extends AbstractGenericDao<Transaction> implements TransactionRepository {

    @Override
    public List<Transaction> filter(final ReportFilter reportFilter){
        String hql = "Select t from Transaction t left join Library l on t.book.library.id = l.id Where 1=1 ";
        if(reportFilter.getLibraryList()!=null && !reportFilter.getLibraryList().isEmpty()){
            hql += "AND l.id in (:pLibraryList) ";
        }
        if(reportFilter.getFreeText()!=null && !"".equals(reportFilter.getFreeText().trim())){
            hql += "AND (lower(l.name) like lower(:pText) or lower(t.book.title) like lower(:pText) or lower" +
                    "(t.member.name) like lower(:pText)) ";
        }
        Query query = em.createQuery(hql);
        if(reportFilter.getLibraryList()!=null && !reportFilter.getLibraryList().isEmpty()){
            query.setParameter("pLibraryList",reportFilter.getLibraryList() );
        }
        if(reportFilter.getFreeText()!=null && !"".equals(reportFilter.getFreeText().trim())){
            query.setParameter("pText","%"+reportFilter.getFreeText()+"%");
        }
        return query.getResultList();
    }

    @Override
    public List<Long> filterIds(final ReportFilter reportFilter){
        String hql = "Select t.id from Transaction t left join Library l on t.book.library.id = l.id Where 1=1 ";
        if(reportFilter.getLibraryList()!=null && !reportFilter.getLibraryList().isEmpty()){
            hql += "AND l.id in (:pLibraryList) ";
        }
        if(reportFilter.getFreeText()!=null && !"".equals(reportFilter.getFreeText().trim())){
            hql += "AND (lower(l.name) like lower(:pText) or lower(t.book.title) like lower(:pText) or lower" +
                    "(t.member.name) like lower(:pText)) ";
        }
        Query query = em.createQuery(hql);
        if(reportFilter.getLibraryList()!=null && !reportFilter.getLibraryList().isEmpty()){
            query.setParameter("pLibraryList",reportFilter.getLibraryList() );
        }
        if(reportFilter.getFreeText()!=null && !"".equals(reportFilter.getFreeText().trim())){
            query.setParameter("pText","%"+reportFilter.getFreeText()+"%");
        }
        return query.getResultList();
    }
}
