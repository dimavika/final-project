package com.epam.dto;

import java.math.BigDecimal;

public class AlbumArtistInfoDto implements Dto {

    private final Long id;
    private final String title;
    private final String artistName;
    private final BigDecimal price;

    public static final String ARTIST_NAME = "Artist.name";

    public AlbumArtistInfoDto(Long id, String title, String artistName, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
