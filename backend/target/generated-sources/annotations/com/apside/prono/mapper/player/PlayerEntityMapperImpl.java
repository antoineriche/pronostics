package com.apside.prono.mapper.player;

import com.apside.prono.model.player.impl.PlayerAPI;
import com.apside.prono.model.player.impl.PlayerEntity;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-07-24T16:02:10+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
public class PlayerEntityMapperImpl implements PlayerEntityMapper {

    @Override
    public PlayerEntity mapPlayerEntity(PlayerAPI actor) {
        if ( actor == null ) {
            return null;
        }

        PlayerEntity playerEntity = new PlayerEntity();

        playerEntity.setLastName( actor.getLastName() );
        playerEntity.setFirstName( actor.getFirstName() );
        if ( actor.getId() != null ) {
            playerEntity.setId( Long.parseLong( actor.getId() ) );
        }
        playerEntity.setMail( actor.getMail() );
        playerEntity.setSubscribeDate( actor.getSubscribeDate() );

        return playerEntity;
    }
}
