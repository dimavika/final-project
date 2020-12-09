package com.epam.spotify.dao.audio;

import com.epam.spotify.dao.AbstractDao;
import com.epam.spotify.dao.exception.DaoException;
import com.epam.spotify.dto.AudioArtistInfoDto;
import com.epam.spotify.entity.Audio;
import com.epam.spotify.entity.enums.Genre;
import com.epam.spotify.rowMapper.dto.AudioArtistInfoDtoRowMapper;
import com.epam.spotify.rowMapper.AudioRowMapper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class AudioDaoImpl extends AbstractDao<Audio> implements AudioDao {

    private static final Logger LOGGER = Logger.getLogger(AudioDaoImpl.class);

    private static final String FIND_BY_TITLE = "SELECT * FROM Audios WHERE title LIKE '%";
    private static final String FIND_JOIN_ARTIST_BY_TITLE = "SELECT * FROM Audios " +
            "inner join Artist on Audios.artist_id = Artist.id" +
            " WHERE title LIKE ? ESCAPE '_' ORDER BY Audios.id";
    private static final String FIND_BY_ARTIST = "SELECT * FROM audios WHERE artist_id = ?";
    private static final String FIND_JOIN_ARTIST_BY_ARTIST = "SELECT * FROM audios " +
            "inner join Artist on Audios.artist_id = Artist.id " +
            "WHERE Artist.name LIKE ? ESCAPE '_' ORDER BY Audios.id";
    private static final String FIND_JOIN_ARTIST_BY_ALBUM_ID = "SELECT * FROM audios " +
            "inner join Artist on Audios.artist_id = Artist.id " +
            "WHERE album_id = ?";
    private static final String ADD_AUDIO = "INSERT audios (title, artist_id, price, genre) " +
            "VALUES (?,?,?,?)";

    private static final String FIND_AUDIO_JOIN_ARTIST_BY_ALBUM_ID = "SELECT * FROM Audios " +
            "inner join Album_audio on Album_audio.audio_id = Audios.id AND Album_audio.album_id = ? " +
            "inner join Artist on Audios.artist_id = Artist.id";

    private static final String FIND_AUDIOS_JOIN_ARTIST_BY_ARTIST_ID_WITH_NO_ALBUM = "SELECT * FROM Audios " +
            "inner join Artist on Artist.id = Audios.artist_id " +
            "left outer join Album_audio on Album_audio.audio_id = Audios.id" +
            " WHERE Audios.artist_id = ? AND Album_audio.album_id is NULL";

    private static final String DELETE_ALBUM_AUDIO_BY_ID = "DELETE FROM album_audio WHERE audio_id = ?";

    private static final String UPDATE_AUDIO = "UPDATE audios SET title = ?, artist_id = ?, price = ?, genre = ? " +
            "WHERE id = ?";

    public AudioDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return Audio.TABLE;
    }

    @Override
    public void save(Audio item) throws DaoException {
        executeUpdate(ADD_AUDIO, item.getTitle(), item.getArtistId(), item.getPrice(),
                item.getGenre().toString());
    }

//    @Override
//    public List<Audio> findAudiosByTitle(String input) throws DaoException {
//        return executeQuery(getDynamicStatementForAudiosByTitle(input), new AudioRowMapper());
//    }
//
//    @Override
//    public List<Audio> findAudiosByArtist(String input, ArtistDao artistDao) throws DaoException {
//        List<Audio> audios = new ArrayList<>();
//        List<Artist> artists = artistDao.findArtistByName(input);
//        Long[] inputs = new Long[artists.size()];
//        for (int i = 0; i < artists.size(); i++){
//            inputs[i] = artists.get(i).getId();
//        }
//        LOGGER.info(inputs.length);
//        if (inputs.length > 0) {
//            audios = executeQuery(getDynamicStatementForAudiosByArtist(artists.size()), new AudioRowMapper(), (Object[]) inputs);
//            return audios;
//        }
//        else {
//            return audios;
//        }
//    }

//    @Override
//    public Set<Audio> findAudiosBySearch(String input, ArtistDao artistDao) throws DaoException {
//        Set<Audio> audios = new HashSet<Audio>();
//        List<Audio> audiosByTitle = findAudiosByTitle(input);
//        List<Audio> audiosByArtist = findAudiosByArtist(input, artistDao);
//        audios.addAll(audiosByTitle);
//        audios.addAll(audiosByArtist);
//        return audios;
//    }

    @Override
    public List<Audio> findAudiosByArtistId(long artistId) throws DaoException {
        return executeQuery(FIND_BY_ARTIST, new AudioRowMapper(), artistId);
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumId(long albumId) throws DaoException {
        return executeJoinQuery(FIND_JOIN_ARTIST_BY_ALBUM_ID, new AudioArtistInfoDtoRowMapper(), albumId);
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumIdNew(long albumId) throws DaoException {
        return executeJoinQuery(FIND_AUDIO_JOIN_ARTIST_BY_ALBUM_ID, new AudioArtistInfoDtoRowMapper(), albumId);
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByTitle(String input) throws DaoException {
        return executeJoinQuery(FIND_JOIN_ARTIST_BY_TITLE,
                new AudioArtistInfoDtoRowMapper(), "%" + input + "%");
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByArtist(String input) throws DaoException {
        return executeJoinQuery(FIND_JOIN_ARTIST_BY_ARTIST,
                new AudioArtistInfoDtoRowMapper(), "%" + input + "%");
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByArtistWithNoAlbum(Long artistId) throws DaoException {
        return executeJoinQuery(FIND_AUDIOS_JOIN_ARTIST_BY_ARTIST_ID_WITH_NO_ALBUM, new AudioArtistInfoDtoRowMapper(),
                artistId);
    }

    @Override
    public void removeAlbumAudioById(Long audioId) throws DaoException {
        executeUpdate(DELETE_ALBUM_AUDIO_BY_ID, audioId);
    }

    @Override
    public void updateAudio(String title, Long artistId, BigDecimal price, Genre genre, Long audioId)
            throws DaoException {
        executeUpdate(UPDATE_AUDIO, title, artistId, price, genre.toString(), audioId);
    }

//    private String getDynamicStatementForAudiosByArtist(int artistsSize){
//        StringBuilder statement = new StringBuilder(FIND_BY_ARTIST);
//        for (int i = 1; i < artistsSize; i++) {
//            statement.append(" OR artist_id = ?");
//        }
//        return statement.toString();
//    }
//
//    private String getDynamicStatementForAudiosByTitle(String title){
//        return FIND_BY_TITLE + title + "%'";
//    }

//    private String getDynamicStatementForAudiosJoinArtistByTitle(String title){
//        return FIND_JOIN_ARTIST_BY_TITLE + title + "%'";
//    }
//
//    private String getDynamicStatementForAudiosJoinArtistByArtist(String name){
//        return FIND_JOIN_ARTIST_BY_ARTIST + name + "%'";
//    }
}
