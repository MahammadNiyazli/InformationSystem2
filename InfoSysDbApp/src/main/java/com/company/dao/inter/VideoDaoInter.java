package com.company.dao.inter;

import com.company.entity.Video;

import java.sql.SQLException;
import java.util.List;

public interface VideoDaoInter {

    public List<Video> getAll() throws SQLException, Exception;

    public boolean addVideo(Video j);

    public boolean removeVideo(int id);

    public Video getById(int id);

    public Video findByName(String name);

}
