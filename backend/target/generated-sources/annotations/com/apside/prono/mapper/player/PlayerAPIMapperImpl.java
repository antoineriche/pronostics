package com.apside.prono.mapper.player;

import com.apside.prono.model.player.impl.PlayerAPI;
import com.apside.prono.model.player.impl.PlayerEntity;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-24T16:02:09+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
public class PlayerAPIMapperImpl implements PlayerAPIMapper {

    @Override
    public PlayerAPI mapPlayer(PlayerEntity PlayerEntity) {
        if ( PlayerEntity == null ) {
            return null;
        }

        PlayerAPI playerAPI = new PlayerAPI();

        playerAPI.setLastName( PlayerEntity.getLastName() );
        playerAPI.setFirstName( PlayerEntity.getFirstName() );
        if ( PlayerEntity.getId() != null ) {
            playerAPI.setId( String.valueOf( PlayerEntity.getId() ) );
        }
        playerAPI.setMail( PlayerEntity.getMail() );
        playerAPI.setSubscribeDate( PlayerEntity.getSubscribeDate() );

        return playerAPI;
    }
}
