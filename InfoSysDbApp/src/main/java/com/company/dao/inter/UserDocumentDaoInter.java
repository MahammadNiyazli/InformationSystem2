package com.company.dao.inter;

import com.company.entity.UserDocument;

import java.sql.SQLException;
import java.util.List;

public interface UserDocumentDaoInter {

    public List<UserDocument> getAll() throws SQLException, Exception;

    public boolean addUserDocument(UserDocument ud);

    public boolean removeUserDocument(int id);


}
