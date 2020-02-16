package com.epam.spotify.dto;

public class AlbumArtistInfoDto implements Dto {

    private final Long id;
    private final String title;
    private final String artistName;
    private final Long artistId;

    public static final String ARTIST_NAME = "Artist.name";

    public AlbumArtistInfoDto(Long id, String title, String artistName, Long artistId) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.artistId = artistId;
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

    public Long getArtistId() {
        return artistId;
    }
}
