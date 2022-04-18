package com.shortLinks.service;

import com.shortLinks.model.Link;

import java.util.List;

public interface LinkService {

    void create (Link link);

    Link read (int id);

    List<Link> readAll ();

    boolean update (int id, String newName, String newUrl);

    boolean delete (int id);

    String redirect(String name);
}
