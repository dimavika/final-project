package com.epam.dao.audio;

import com.epam.dao.AbstractDao;
import com.epam.dao.DaoException;
import com.epam.dto.AudioArtistInfoDto;
import com.epam.entity.Audio;
import com.epam.rowMapper.dto.AudioArtistInfoDtoRowMapper;
import com.epam.rowMapper.AudioRowMapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AudioDaoImpl extends AbstractDao<Audio> implements AudioDao {

    private static final Logger LOGGER = Logger.getLogger(AudioDaoImpl.class);

    private static final String FIND_BY_TITLE = "SELECT * FROM Audios WHERE title LIKE '%";
    private static final String FIND_JOIN_ARTIST_BY_TITLE = "SELECT * FROM Audios " +
            "inner join Artist on Audios.artist_id = Artist.id" +
            " WHERE title LIKE ? ESCAPE '_'";
    private static final String FIND_BY_ARTIST = "SELECT * FROM audios WHERE artist_id = ?";
    private static final String FIND_JOIN_BY_ARTIST = "SELECT * FROM audios " +
            "inner join Artist on Audios.artist_id = Artist.id " +
            "WHERE Artist.name LIKE ? ESCAPE '_'";
    private static final String FIND_JOIN_ARTIST_BY_ALBUM_ID = "SELECT * FROM audios " +
            "inner join Artist on Audios.artist_id = Artist.id " +
            "WHERE album_id = ?";
    private static final String UPDATE_ALBUM_ID = "UPDATE audios SET album_id = ? WHERE id = ?";
    private static final String ADD_AUDIO = "INSERT audios (title, artist_id, price, genre) " +
            "VALUES (?,?,?,?)";

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
    public void updateAlbumId(List<Long> audioIds, Long albumId) throws DaoException {
        for (Long audioId : audioIds) {
            executeUpdate(UPDATE_ALBUM_ID, albumId, audioId);
        }
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByAlbumId(long albumId) throws DaoException {
        return executeJoinQuery(FIND_JOIN_ARTIST_BY_ALBUM_ID, new AudioArtistInfoDtoRowMapper(), albumId);
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByTitle(String input) throws DaoException {
        return executeJoinQuery(FIND_JOIN_ARTIST_BY_TITLE,
                new AudioArtistInfoDtoRowMapper(), "%" + input + "%");
    }

    @Override
    public List<AudioArtistInfoDto> findAudiosJoinArtistByArtist(String input) throws DaoException {
        return executeJoinQuery(FIND_JOIN_BY_ARTIST,
                new AudioArtistInfoDtoRowMapper(), "%" + input + "%");
    }

    @Override
    public Set<AudioArtistInfoDto> findAudiosJoinArtistBySearch(String input) throws DaoException {
        List<AudioArtistInfoDto> audiosByTitle = findAudiosJoinArtistByTitle(input);
        List<AudioArtistInfoDto> audiosByArtist = findAudiosJoinArtistByArtist(input);
        Set<AudioArtistInfoDto> audios = new HashSet<>();
        audios.addAll(audiosByTitle);
        audios.addAll(audiosByArtist);
        return audios;
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

    private String getDynamicStatementForAudiosJoinArtistByTitle(String title){
        return FIND_JOIN_ARTIST_BY_TITLE + title + "%'";
    }

    private String getDynamicStatementForAudiosJoinArtistByArtist(String name){
        return FIND_JOIN_BY_ARTIST + name + "%'";
    }
}
