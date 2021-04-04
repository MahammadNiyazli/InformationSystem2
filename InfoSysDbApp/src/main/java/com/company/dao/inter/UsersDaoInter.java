package com.company.dao.inter;

import com.company.entity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDaoInter {

    public List<Users> getAll() throws SQLException, Exception;

    public boolean updateUser(Users u);

    public boolean addUser(Users u);

    public boolean removeUser(int id);

    public Users getById(int id);

    public Users findByEmail(String email);

}
