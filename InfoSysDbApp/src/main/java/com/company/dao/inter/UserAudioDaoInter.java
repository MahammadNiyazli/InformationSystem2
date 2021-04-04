package com.company.dao.inter;

import com.company.entity.UserAudio;

import java.sql.SQLException;
import java.util.List;

public interface UserAudioDaoInter {

    public List<UserAudio> getAll() throws SQLException, Exception;

    public boolean addUserAudio(UserAudio a);

    public boolean removeUserAudio(int id);


}
