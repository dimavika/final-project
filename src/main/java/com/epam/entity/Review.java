package com.epam.entity;

public class Review implements Identifiable {

    private final Long id;
    private final Long userId;
    private final String text;
    private final Long audioId;
    private final String dateTime;

    public static final String TABLE = "reviews";
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TEXT = "text";
    public static final String AUDIO_ID = "audio_id";
    public static final String DATE_TIME = "review_datetime";

    public Review(Long id, Long userId, String text, Long audioId, String dateTime) {
        this.id = id;
        this.userId = userId;
        this.text = text;
        this.audioId = audioId;
        this.dateTime = dateTime;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getAudioId() {
        return audioId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Long getUserId() {
        return userId;
    }
}
