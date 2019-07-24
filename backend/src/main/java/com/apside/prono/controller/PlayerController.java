package com.apside.prono.controller;

import com.apside.prono.mapper.player.PlayerAPIMapper;
import com.apside.prono.mapper.player.PlayerEntityMapper;
import com.apside.prono.model.player.impl.PlayerAPI;
import com.apside.prono.model.player.impl.PlayerEntity;
import com.apside.prono.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "http://localhost:4021")
public class PlayerController {

    private final Logger log = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;
    @Autowired
    private Environment env;
    private ResourceBundle bundle = ResourceBundle.getBundle("messagesControllerError");

    /**
     * GET  /players : get all player.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of players in body
     */
    @GetMapping("/players")
    public List<PlayerEntity> getAllPlayers() {
        log.debug(bundle.getString("get_all_players"));
        return playerService.getAllPlayers();
    }

    /**
     * GET  /players/:id : get the "id" player.
     *
     * @param id the id of the player to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the player, or with status 404 (Not Found)
     */
    @GetMapping("/players/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable("id") long id) {
        String message = bundle.getString("get_player");
        log.debug(message, id);
        PlayerEntity playerEntity = playerService.getPlayer(id);
        return ResponseEntity.ok().body(PlayerAPIMapper.INSTANCE.mapPlayer(playerEntity));
    }

    /**
     * POST  /players/ : Create a new player.
     *
     * @param postedPlayer the player to create
     * @return the ResponseEntity with status 201 (Created) and with body the new player, or with status 400 (Bad Request) if the player has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/players/")
    public ResponseEntity<?> createPlayer(@Valid @RequestBody PlayerAPI postedPlayer) throws URISyntaxException {
        String message = bundle.getString("post_player");
        log.debug(message, postedPlayer);
        PlayerEntity playerEntity = PlayerEntityMapper.INSTANCE.mapPlayerEntity(postedPlayer);
        playerEntity = playerService.savePlayer(playerEntity);
        return new ResponseEntity<>(PlayerAPIMapper.INSTANCE.mapPlayer(playerEntity), HttpStatus.CREATED);
    }

    /**
     * DELETE  /players/:id : delete the "id" player.
     *
     * @param id the id of the player to delete
     * @return the ResponseEntity with status 200 (OK) and with body the player, or with status 404 (Not Found)
     */
    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") long id) {
        PlayerEntity playerEntity = playerService.getPlayer(id);
        String message = bundle.getString("delete_player");
        log.debug(message, id);
        playerService.deletePlayer(id);
        return new ResponseEntity<>(PlayerAPIMapper.INSTANCE.mapPlayer(playerEntity), HttpStatus.ACCEPTED);
    }

    /**
     * PUT  /players/{id} : update an player.
     *
     * @param player the player to update
     * @return the ResponseEntity with status 201 (Update) and with body the new player, or with status 400 (Bad Request) if the player has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */

    @PutMapping("/players/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable("id") long id, @Valid @RequestBody PlayerAPI player) throws URISyntaxException {
        String message = bundle.getString("put_player");
        log.debug(message, player);
        PlayerEntity playerEntity = PlayerEntityMapper.INSTANCE.mapPlayerEntity(player);
        playerEntity = playerService.updatePlayer(id, playerEntity);
        return new ResponseEntity<>(PlayerAPIMapper.INSTANCE.mapPlayer(playerEntity), HttpStatus.ACCEPTED);
    }
}
