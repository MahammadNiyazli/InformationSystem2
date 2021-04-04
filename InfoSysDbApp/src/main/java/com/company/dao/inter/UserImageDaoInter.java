package com.company.dao.inter;

import com.company.entity.UserImage;

import java.sql.SQLException;
import java.util.List;

public interface UserImageDaoInter {

    public List<UserImage> getAll() throws SQLException, Exception;

    public boolean addUserImage(UserImage ui);

    public boolean removeUserImage(int id);


}
