package com.epam.entity;

import java.math.BigDecimal;

public class Album implements Identifiable {

    private final Long id;
    private final String title;
    private final Long artistId;
    private final BigDecimal price;

    public static final String TABLE = "albums";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ARTIST_ID = "artist_id";
    public static final String PRICE = "price";

    public Album(Long id, String title, Long artistId, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }
}
