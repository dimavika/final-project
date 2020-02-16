package com.epam.spotify.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Order implements Serializable, Identifiable {

    private Long id;
    private Long audioId;
    private String dateTime;
    private String cardNumber;
    private Long userId;
    private BigDecimal price;
    private String year;
    private String thirdNum;

    public static final String TABLE = "orders";
    public static final String ID = "id";
    public static final String AUDIO_ID = "audio_id";
    public static final String DATE_TIME = "order_datetime";
    public static final String CARD_NUMBER = "card_num";
    public static final String YEAR = "year";
    public static final String THIRD_NUM = "third_num";
    public static final String USER_ID = "user_id";
    public static final String PRICE = "price";

    public Order(Long id, Long audioId, String dateTime, String cardNumber, Long userId, BigDecimal price, String year, String thirdNum) {
        this.id = id;
        this.audioId = audioId;
        this.dateTime = dateTime;
        this.cardNumber = cardNumber;
        this.userId = userId;
        this.price = price;
        this.year = year;
        this.thirdNum = thirdNum;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getAudioId() {
        return audioId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public String getThirdNum() {
        return thirdNum;
    }
}
