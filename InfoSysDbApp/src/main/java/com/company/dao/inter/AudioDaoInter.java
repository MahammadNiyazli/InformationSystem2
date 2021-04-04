package com.company.dao.inter;

import com.company.entity.Audio;

import java.sql.SQLException;
import java.util.List;

public interface AudioDaoInter {

    public List<Audio> getAll() throws SQLException, Exception;

    public boolean addAudio(Audio a);

    public boolean removeAudio(int id);

    public Audio getById(int id);

    public Audio findByName(String name);

}
