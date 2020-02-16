package com.epam.spotify.rowMapper;

import com.epam.spotify.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table){
        switch (table){
            case User.TABLE:
                return new UserRowMapper();
            case Audio.TABLE:
                return new AudioRowMapper();
            case Artist.TABLE:
                return new ArtistRowMapper();
            case Album.TABLE:
                return new AlbumRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = " + table);
        }
    }
}
