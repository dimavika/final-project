package com.epam.spotify.entity;

import com.epam.spotify.entity.enums.Role;

import java.io.Serializable;

public class User implements Serializable, Identifiable {

    private final Long id;
    private final String login;
    private final Role role;
    private final Boolean isBlocked;
    public static final String TABLE = "users";
    public static final String LOGIN = "login";
    public static final String ROLE = "role";
    public static final String ID = "id";
    public static final String IS_BLOCKED = "is_blocked";


    public User(Long id, String login, Role role, Boolean isBlocked) {
        this.login = login;
        this.role = role;
        this.id = id;
        this.isBlocked = isBlocked;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }
}
