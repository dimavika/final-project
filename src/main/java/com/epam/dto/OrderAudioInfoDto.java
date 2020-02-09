package com.epam.dto;

import java.math.BigDecimal;

public class OrderAudioInfoDto implements Dto {

    private Long id;
    private Long audioId;
    private String audioTitle;
    private String dateTime;
    private BigDecimal price;

    public static final String ID = "id";
    public static final String AUDIO_ID = "audio_id";
    public static final String DATE_TIME = "order_datetime";
    public static final String PRICE = "price";
    public static final String AUDIO_TITLE = "Audios.title";

    public OrderAudioInfoDto(Long id, Long audioId, String audioTitle, String dateTime, BigDecimal price) {
        this.id = id;
        this.audioId = audioId;
        this.audioTitle = audioTitle;
        this.dateTime = dateTime;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAudioId() {
        return audioId;
    }
}
