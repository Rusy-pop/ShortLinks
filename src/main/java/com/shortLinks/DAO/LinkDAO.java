package com.shortLinks.DAO;

import com.shortLinks.model.Link;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LinkDAO {
    private final DataSource source;

    public LinkDAO(DataSource source) {
        this.source = source;
    }

    public void save(Link link) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/Rusy","Rusy","123");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO links (url, name)" +
                    " VALUES (?, ?);"))
            {

            List <Link> links = this.readAll();
            long equalsNewLink  = links.stream().filter(existLink -> existLink.equals(link)).count();
            if (equalsNewLink==0){
                statement.setString(1, link.getUrl());
                statement.setString(2, link.getName());
                System.out.println(link.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Link> readAll(){
        ArrayList<Link> links = new ArrayList<>();
        try(Connection connection = source.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM links");
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String url = resultSet.getString("url");
                Link link = new Link(id, name, url);
                links.add(link);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return links;
    }

    public Link read(int id){
        Link link = null;
            try(Connection connection = source.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT name, url FROM links WHERE id = ?;")){

                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    String name = resultSet.getString("name");
                    String url = resultSet.getString("url");
                    link = new Link(id, name, url);
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        return link;
    }
    public boolean update(int id, String newName, String newUrl){
        boolean update = false;
        try(Connection connection = source.getConnection()){
            if (isIdExistInDB(id)){
                if (newName.equals("")){
                    newName = read(id).getName();
                }
                if (newUrl.equals("")){
                    newUrl = read(id).getUrl();
                }
                PreparedStatement statement = connection.prepareStatement("UPDATE links SET name = ?, url = ? " +
                        "WHERE id = ?;");
                statement.setString(1, newName);
                statement.setString(2, newUrl);
                statement.setInt(3, id);
                statement.executeUpdate();
                update = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return update;
    }

    public boolean delete(int id){
        boolean delete = false;
        try (Connection connection = source.getConnection()){
            if (isIdExistInDB(id)){
                PreparedStatement statement = connection.prepareStatement("DELETE FROM links WHERE id = ?;");
                statement.setInt(1, id);
                statement.executeUpdate();
                delete = true;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return delete;
    }

    public boolean isIdExistInDB(int id){
        boolean isExist = false;
        try(Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM links WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            String s = null;
            while (resultSet.next()){
                s = resultSet.getString("name");
            }
            if (s != null){
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isExist;
    }

    public boolean isNameExistInDB(String name){
        boolean isExist = false;
        try(Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM links WHERE name = ?;");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            String s = null;
            while (resultSet.next()){
                s = resultSet.getString("name");
            }
            if (s != null){
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isExist;
    }

    public boolean isUrlExistInDB(String url) {
        boolean isExist = false;
        try(Connection connection = source.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM links WHERE url = ?;");
            statement.setString(1, url);
            ResultSet resultSet = statement.executeQuery();

            String s = null;
            while (resultSet.next()){
                s = resultSet.getString("url");
            }
            if (s != null){
                isExist = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isExist;
    }

    public String getUrl(String name) {
        String url = null;
        if (this.isNameExistInDB(name)){
            try(Connection connection = source.getConnection()){
                PreparedStatement statement = connection.prepareStatement("SELECT url FROM links WHERE name = ?;");
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    url = resultSet.getString("url");
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return url;
    }
}
