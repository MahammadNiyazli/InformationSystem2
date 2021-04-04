package com.company.dao.inter;

import com.company.entity.UserVideo;

import java.sql.SQLException;
import java.util.List;

public interface UserVideoDaoInter {

    public List<UserVideo> getAll() throws SQLException, Exception;

    public boolean addUserVideo(UserVideo uj);

    public boolean removeUserVideo(int id);


}
