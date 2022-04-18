package com.shortLinks.DAO;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class MyDataSource {


    public static DataSource getDataSource(){
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setURL("jdbc:postgresql://localhost/Rusy");
        source.setDatabaseName("Rusy");
        source.setUser("Rusy");
        source.setPassword("123");

        return source;
    }
}
