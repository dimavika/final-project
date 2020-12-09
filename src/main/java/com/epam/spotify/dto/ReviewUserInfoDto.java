package com.epam.spotify.dto;

public class ReviewUserInfoDto implements Dto {

    private final Long id;
    private final String  user;
    private final String text;
    private final String dateTime;

    public static final String USER = "Users.login";

    public ReviewUserInfoDto(Long id, String user, String text, String dateTime) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.dateTime = dateTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }
}
