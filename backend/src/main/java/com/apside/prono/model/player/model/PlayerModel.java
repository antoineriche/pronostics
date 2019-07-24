package com.apside.prono.model.player.model;

import java.util.Date;

public interface PlayerModel {

    String getLastName();
    void setLastName(String lastName);

    String getFirstName();
    void setFirstName(String firstName);

    String getMail();
    void setMail(String mail);

    Date getSubscribeDate();
    void setSubscribeDate(Date subscribeDate);
}
