package com.shortLinks.service;

import com.shortLinks.model.Link;

import java.util.List;

public interface LinkService {

    public void create (Link link);

    public Link read (String id);

    public List<Link> readAll ();

    public boolean update (Link link, String id);

    public boolean delete (String id);
}
