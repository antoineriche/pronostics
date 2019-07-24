package com.apside.prono.mapper.player;

import com.apside.prono.model.player.impl.PlayerAPI;
import com.apside.prono.model.player.impl.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerAPIMapper {

    PlayerAPIMapper INSTANCE = Mappers.getMapper(PlayerAPIMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "mail", source = "mail")
    @Mapping(target = "subscribeDate", source = "subscribeDate")
    PlayerAPI mapPlayer(PlayerEntity PlayerEntity);
}
