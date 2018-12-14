package com.gbl.configure;

/**
 * Created by guobaolin on 2018/11/2.
 */
public enum DatabaseType {
    master("write"),
    slave("read");

    private String name;

    DatabaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DatabaseType{" +
                "name='" + name + '\'' +
                '}';
    }
}
