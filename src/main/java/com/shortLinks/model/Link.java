package com.shortLinks.model;

import java.util.Objects;

public class Link {
    private int id;
    private String name;
    private String url;

    public Link(String url) {
        this.url = url;
    }

    public Link(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(name, link.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void creatName() {
        StringBuilder name = new StringBuilder();
        String v = this.getUrl();

        for (int i = 0; i < 5; i++) {

            v = v.replaceAll("\\p{Punct}", "");
            char [] arr =  v.toCharArray();

            double random = Math.random() * v.length();
            int index = (int) random;
            name.append(arr[index]);
        }
        this.setName(name.toString());
    }
}
