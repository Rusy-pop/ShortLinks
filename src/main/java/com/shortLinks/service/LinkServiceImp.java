package com.shortLinks.service;

import com.shortLinks.model.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LinkServiceImp implements LinkService{

    private static final Map<String, Link> LINK_MAP = new HashMap();

    @Override
    public void create(Link link) {
            LINK_MAP.put(link.getShortUrl(), link);
    }

    @Override
    public Link read(String shortLink) {
        if (LINK_MAP.containsKey(shortLink)){
            return LINK_MAP.get(shortLink);
        } else return null;
    }

    @Override
    public List<Link> readAll() {
        return new ArrayList<>(LINK_MAP.values());
    }

    @Override
    public boolean update(Link link, String id) {
        Link l = LINK_MAP.get(id);
        l.setOldUrl(link.getOldUrl());

        return true;
    }

    @Override
    public boolean delete(String shortLink) {
        return LINK_MAP.remove(shortLink)!=null;
    }
}
