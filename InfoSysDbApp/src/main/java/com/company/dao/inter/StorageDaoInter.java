package com.company.dao.inter;

import com.company.entity.Storage;

import java.sql.SQLException;
import java.util.List;

public interface StorageDaoInter {

    public List<Storage> getAll() throws SQLException, Exception;

    public Storage getById(int id);

}
