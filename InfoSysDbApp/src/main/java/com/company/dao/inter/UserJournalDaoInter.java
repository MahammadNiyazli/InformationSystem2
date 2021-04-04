package com.company.dao.inter;

import com.company.entity.UserJournal;

import java.sql.SQLException;
import java.util.List;

public interface UserJournalDaoInter {

    public List<UserJournal> getAll() throws SQLException, Exception;

    public boolean addUserJournal(UserJournal uj);

    public boolean removeUserJournal(int id);


}
