package com.epam.command;

import com.epam.command.album.AddAlbumCommand;
import com.epam.command.album.SendAlbumsCommand;
import com.epam.command.artist.AddArtistCommand;
import com.epam.command.artist.SendArtistsCommand;
import com.epam.command.audio.*;
import com.epam.command.review.AddReviewCommand;
import com.epam.command.review.SendReviewsCommand;
import com.epam.command.user.BlockUserCommand;
import com.epam.command.user.LoginCommand;
import com.epam.command.user.ShowUsersCommand;
import com.epam.command.user.UnblockUserCommand;
import com.epam.dao.DaoHelperFactory;
import com.epam.service.*;
import com.epam.service.exception.ServiceException;

public class CommandFactory {

    public static Command create(String command) throws ServiceException {

        switch (command){
            case "login":
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "loginPage":
                return new ShowPageCommand("login.jsp");
            case "main":
                return new ShowPageCommand("main.jsp");
            case "error":
                return new ShowPageCommand("error.jsp");
            case "showUsers":
                return new ShowUsersCommand(new UserService(new DaoHelperFactory()));
            case "users":
                return new ShowPageCommand("users.jsp");
            case "audios":
                return new ShowPageCommand("audios.jsp");
            case "audio":
                return new SendArtistsCommand(new ArtistService(new DaoHelperFactory()), "showAddAudio");
            case "showAddAudio":
                return new ShowPageCommand("addAudio.jsp");
            case "showAudios":
                return new ShowAudiosBySearchCommand(new AudioService(new DaoHelperFactory()));
            case "addAudio":
                return new AddAudioCommand(new AudioService(new DaoHelperFactory()));
            case "showAddArtist":
                return new ShowPageCommand("addArtist.jsp");
            case "addArtist":
                return new AddArtistCommand(new ArtistService(new DaoHelperFactory()));
            case "sendArtistForAddAlbum":
                return new SendArtistsCommand(new ArtistService(new DaoHelperFactory()), "showAddAlbum");
            case "showAddAlbumSecond":
                return new ShowPageCommand("addAlbumSecond.jsp");
            case "showAlbums":
                return new ShowPageCommand("albums.jsp");
            case "showAddAlbum":
                return new ShowPageCommand("addAlbum.jsp");
            case "sendAudiosForAddAlbumSecond":
                return new SendAudiosByArtistCommand(new AudioService(new DaoHelperFactory()));
            case "addAlbum":
                return new AddAlbumCommand(new AlbumService(new DaoHelperFactory()),
                        new AudioService(new DaoHelperFactory()));
            case "sendAlbum":
                return new SendAudiosForAlbumCommand(new AudioService(new DaoHelperFactory()));
            case "showAlbum":
                return new ShowPageCommand("album.jsp");
            case "sendAlbums":
                return new SendAlbumsCommand(new AlbumService(new DaoHelperFactory()));
            case "block":
                return new BlockUserCommand(new UserService(new DaoHelperFactory()));
            case "unblock":
                return new UnblockUserCommand(new UserService(new DaoHelperFactory()));
            case "sendAudioIdForAddReview":
                return new SendAudioIdForReviewAdding();
            case "showAddReview":
                return new ShowPageCommand("addReview.jsp");
            case "addReview":
                return new AddReviewCommand(new ReviewService(new DaoHelperFactory()));
            case "sendReviews":
                return new SendReviewsCommand(new ReviewService(new DaoHelperFactory()));
            case "showReviews":
                return new ShowPageCommand("reviews.jsp");
            case "deleteAudio":
                return new DeleteAudioCommand(new AudioService(new DaoHelperFactory()));
//            case "registration":
//                return new ShowPageCommand("/registration.jsp");
//            case "register":
//                return new RegisterCommand(new UserService(new DaoHelperFactory()));
            default:
                throw new ServiceException("Unknown command " + command);
        }
    }

}
