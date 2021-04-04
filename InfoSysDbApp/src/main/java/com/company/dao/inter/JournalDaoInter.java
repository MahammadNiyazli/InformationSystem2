package com.company.dao.inter;

import com.company.entity.Journal;

import java.sql.SQLException;
import java.util.List;

public interface JournalDaoInter {

    public List<Journal> getAll() throws SQLException, Exception;

    public boolean addJournal(Journal j);

    public boolean removeJournal(int id);

    public Journal getById(int id);

    public Journal findByName(String name);

}
