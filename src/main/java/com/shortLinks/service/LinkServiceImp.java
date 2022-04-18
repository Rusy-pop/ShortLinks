package com.shortLinks.service;

import com.shortLinks.DAO.LinkDAO;
import com.shortLinks.DAO.MyDataSource;
import com.shortLinks.model.Link;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImp implements LinkService {

    @Override
    public void create(Link link) {
        LinkDAO linkDAO = new LinkDAO(MyDataSource.getDataSource());

        link.creatName();
        while (linkDAO.isNameExistInDB(link.getName())){
            link.creatName();
        }
        linkDAO.save(link);
    }

    @Override
    public Link read(int id) {
        return new LinkDAO(MyDataSource.getDataSource()).read(id);
    }

    @Override
    public List<Link> readAll() {
        return new LinkDAO(MyDataSource.getDataSource()).readAll();
    }

    @Override
    public boolean update(int id, String newName, String newUrl) {

        return new LinkDAO(MyDataSource.getDataSource()).update(id, newName, newUrl);
    }

    @Override
    public boolean delete(int id) {
        return new LinkDAO(MyDataSource.getDataSource()).delete(id);
    }

    @Override
    public String redirect(String name) {
        return new LinkDAO(MyDataSource.getDataSource()).getUrl(name);
    }

}
