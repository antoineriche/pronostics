package com.apside.prono.service;

import com.apside.prono.model.player.impl.PlayerAPI;
import com.apside.prono.model.player.impl.PlayerEntity;
import com.apside.prono.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.TransactionScoped;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
@Transactional(readOnly = true)
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private Environment env;
    private ResourceBundle bundle = ResourceBundle.getBundle("messagesServicesError");

    @Transactional
    public List<PlayerEntity> getAllPlayers() {
        return playerRepository.findAll();
    }

    public  PlayerEntity getPlayer(long id) {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(id);
        if (!playerEntity.isPresent()) {
            String pattern = bundle.getString("player_wrong_id");
            String message = MessageFormat.format(pattern, id);
            throw new EntityNotFoundException(message);
        }
        return playerEntity.get();
    }

    @Transactional
    public PlayerEntity savePlayer(PlayerEntity playerEntity){
        if(playerEntity != null) {
            if (playerEntity.getId() != null) {
                throw new RuntimeException(bundle.getString("new_player_create"));
            }
            return playerRepository.save(playerEntity);
        } else {
            throw new RuntimeException(bundle.getString("new_player_empty"));
        }
    }

    @Transactional
    public void deletePlayer(long id){
        if (!playerRepository.findById(id).isPresent()) {
            String pattern = bundle.getString("player_wrong_id");
            String message = MessageFormat.format(pattern, id);
            throw new EntityNotFoundException(message);
        } else {
            playerRepository.deleteById(id);
        }
    }

//    @Transactional
//    public PlayerEntity update(PlayerEntity playerEntity) {
//        if(playerEntity !=null) {
//            Optional<PlayerEntity> player = playerRepository.findById(playerEntity.getId());
//            if (!player.isPresent()) {
//                String pattern = bundle.getString("player_wrong_id");
//                String message = MessageFormat.format(pattern, playerEntity.getId());
//                throw new EntityNotFoundException(message);
//            }
//            playerRepository.findById(playerEntity.getId()).get().setSubscribeDate(playerEntity.getSubscribeDate());
//            playerRepository.findById(playerEntity.getId()).get().setMail(playerEntity.getMail());
//            playerRepository.findById(playerEntity.getId()).get().setLastName(playerEntity.getLastName());
//            playerRepository.findById(playerEntity.getId()).get().setFirstName(playerEntity.getFirstName());
//            PlayerEntity playerEntity1 = playerRepository.findById(playerEntity.getId()).get();
//            playerRepository.flush();
//            return playerEntity1;
//        }
//        throw new EntityNotFoundException(bundle.getString("update_player_empty"));
//    }


    @Transactional
    public PlayerEntity updatePlayer(long id, PlayerEntity playerEntity){
        if(playerEntity !=null) {
            Optional<PlayerEntity> player = playerRepository.findById(id);
            if (!player.isPresent()) {
                String pattern = bundle.getString("player_wrong_id");
                String message = MessageFormat.format(pattern, playerEntity.getId());
                throw new EntityNotFoundException(message);
            }
            return playerRepository.save(playerEntity);
        } else {
            throw new EntityNotFoundException(bundle.getString("update_player_empty"));
        }
    }
}
