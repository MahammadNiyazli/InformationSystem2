package com.company.dao.inter;

import com.company.entity.Image;

import java.sql.SQLException;
import java.util.List;

public interface ImageDaoInter {

    public List<Image> getAll() throws SQLException, Exception;

    public boolean addImage(Image a);

    public boolean removeImage(int id);

    public Image getById(int id);

    public Image findByName(String name);

}
