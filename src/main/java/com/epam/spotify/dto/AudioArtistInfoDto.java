package com.epam.spotify.dto;

import com.epam.spotify.entity.enums.Genre;

import java.math.BigDecimal;

public class AudioArtistInfoDto implements Dto {

    private final Long id;
    private final String title;
    private final String artistName;
    private final BigDecimal price;
    private final Genre genre;

    public static final String ARTIST_NAME = "Artist.name";
    public static final String AUDIO_ID = "Audios.id";

    public AudioArtistInfoDto(Long id, String title, String artistName, BigDecimal price, Genre genre) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.price = price;
        this.genre = genre;
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

    public Genre getGenre() {
        return genre;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AudioArtistInfoDto that = (AudioArtistInfoDto) o;

        if (!id.equals(that.id)) {
            return false;
        }
        if (!title.equals(that.title)) {
            return false;
        }
        if (!artistName.equals(that.artistName)) {
            return false;
        }
        if (!price.equals(that.price)) {
            return false;
        }

        return genre == that.genre;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + artistName.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }
}
