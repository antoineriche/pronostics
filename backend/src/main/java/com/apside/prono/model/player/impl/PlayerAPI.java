package com.apside.prono.model.player.impl;

import com.apside.prono.model.player.model.PlayerModel;

import java.util.Date;

public class PlayerAPI implements PlayerModel {

    private String id;
    private String lastName;
    private String firstName;
    private String mail;
    private Date subscribeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public Date getSubscribeDate() {
        return subscribeDate;
    }

    @Override
    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }
}
