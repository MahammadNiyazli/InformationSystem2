package com.company.dao.inter;

import com.company.entity.Document;

import java.sql.SQLException;
import java.util.List;

public interface DocumentDaoInter {

    public List<Document> getAll() throws SQLException, Exception;

    public boolean addDocument(Document d);

    public boolean removeDocument(int id);

    public Document getById(int id);

    public Document findByName(String name);

}
