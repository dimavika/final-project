package com.epam.spotify.entity;

import com.epam.spotify.entity.enums.Genre;

import java.io.Serializable;
import java.math.BigDecimal;

public class Audio implements Serializable, Identifiable {

    private final Long id;
    private final String title;
    private final Long artistId;
    private final Long albumId;
    private final BigDecimal price;
    private final Genre genre;

    public static final String TABLE = "audios";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ARTIST_ID = "artist_id";
    public static final String ALBUM_ID = "album_id";
    public static final String PRICE = "price";
    public static final String GENRE = "genre";

    public Audio(Long id, String title, Long artistId, Long albumId,
                 BigDecimal price, Genre genre) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genre = genre;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getArtistId() {
        return artistId;
    }

    public Genre getGenre() {
        return genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Audio audio = (Audio) o;

        if (!id.equals(audio.id)) {
            return false;
        }
        if (!title.equals(audio.title)) {
            return false;
        }
        if (!artistId.equals(audio.artistId)) {
            return false;
        }
        if (!albumId.equals(audio.albumId)) {
            return false;
        }
        if (!price.equals(audio.price)) {
            return false;
        }

        return genre == audio.genre;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artistId.hashCode();
        result = 31 * result + albumId.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

//    public String getArtistName() {
//        return artistName;
//    }

    public Long getAlbumId() {
        return albumId;
    }
}
