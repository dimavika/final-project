package com.epam.spotify.entity;

import java.io.Serializable;

public class Album implements Serializable, Identifiable {

    private final Long id;
    private final String title;
    private final Long artistId;

    public static final String TABLE = "albums";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ARTIST_ID = "artist_id";

    public Album(Long id, String title, Long artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public String getTitle() {
        return title;
    }

    public Long getArtistId() {
        return artistId;
    }

    @Override
    public Long getId() {
        return id;
    }
}
