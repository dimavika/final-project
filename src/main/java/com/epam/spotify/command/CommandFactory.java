package com.epam.spotify.command;

import com.epam.spotify.command.album.*;
import com.epam.spotify.command.artist.AddArtistCommand;
import com.epam.spotify.command.artist.SendArtistsCommand;
import com.epam.spotify.command.audio.*;
import com.epam.spotify.command.locale.ChangeLocaleCommand;
import com.epam.spotify.command.order.AddOrderCommand;
import com.epam.spotify.command.order.SendOrdersCommand;
import com.epam.spotify.command.review.AddReviewCommand;
import com.epam.spotify.command.review.SendReviewsCommand;
import com.epam.spotify.command.user.*;
import com.epam.spotify.dao.DaoHelperFactory;
import com.epam.spotify.service.*;
import com.epam.spotify.service.exception.ServiceException;

public class CommandFactory {

    public static Command create(String command) throws ServiceException {

        switch (command){
            case "login":
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
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
                return new AddAlbumCommand(new AlbumService(new DaoHelperFactory()));
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
            case "showAddOrder":
                return  new ShowPageCommand("addOrder.jsp");
            case "sendAudioIdAndPriceForAddOrder":
                return new SendAudioIdAndPriceForOrderAdding();
            case "addOrder":
                return new AddOrderCommand(new OrderService(new DaoHelperFactory()));
            case "sendOrders":
                return new SendOrdersCommand(new OrderService(new DaoHelperFactory()));
            case "showOrders":
                return new ShowPageCommand("orders.jsp");
            case "changeLocale":
                return new ChangeLocaleCommand();
            case "deleteAlbum":
                return new DeleteAlbumCommand(new AlbumService(new DaoHelperFactory()));
            case "sendAudiosForChangeAlbum":
                return new SendAudiosForAddAudioInAlbumCommand(new AudioService(new DaoHelperFactory()));
            case "showAddAudioInAlbum":
                return new ShowPageCommand("addAudioInAlbum.jsp");
            case "addAudioInAlbum":
                return new AddAudioInAlbumCommand(new AlbumService(new DaoHelperFactory()));
            case "deleteAlbumAudio":
                return new DeleteAlbumAudioCommand(new AlbumService(new DaoHelperFactory()));
            case "changeAlbumTitle":
                return new ChangeAlbumTitleCommand(new AlbumService(new DaoHelperFactory()));
            case "sendArtistForUpdateAudio":
                return new SendArtistsCommand(new ArtistService(new DaoHelperFactory()), "showChangeAudio");
            case "updateAudio":
                return new UpdateAudioCommand(new AudioService(new DaoHelperFactory()));
//            case "registration":
//                return new ShowPageCommand("/registration.jsp");
//            case "register":
//                return new RegisterCommand(new UserService(new DaoHelperFactory()));
            default:
                throw new ServiceException("Unknown command " + command);
        }
    }

}
