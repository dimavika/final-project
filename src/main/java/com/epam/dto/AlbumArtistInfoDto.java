package com.epam.dto;

public class AlbumArtistInfoDto implements Dto {

    private final Long id;
    private final String title;
    private final String artistName;

    public static final String ARTIST_NAME = "Artist.name";

    public AlbumArtistInfoDto(Long id, String title, String artistName) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
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
}
