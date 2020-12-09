package com.epam.spotify.entity;

import java.io.Serializable;

public class Artist implements Identifiable, Serializable {

    private final Long id;
    private final String name;

    public static final String TABLE = "artist";
    public static final String ID = "id";
    public static final String NAME = "name";

    public Artist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }
}
