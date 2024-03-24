package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class PlayerController {
    private PlayerDirectory directory = new PlayerDirectory();
    // returns all players
    @GetMapping("/api/players")
    public ResponseEntity<List<Player>> players() {
        List<Player> list = directory.allPlayers().stream().toList();
        return ResponseEntity.ok().body(list);
    }

    // returns player by playerId
    @GetMapping("/api/players/{playerID}")
    public ResponseEntity<Player> players(@PathVariable("playerID") String playerID) {
        Player p = directory.getPlayer(playerID);
        return ResponseEntity.ok().body(p);
    }

    // pagination of players
    // GET /api/players/page/1
    // HEADER: pageSize 10
    @GetMapping("/api/players/page/{page}")
    public ResponseEntity<List<Player>> page(@RequestHeader("pageSize") int pageSize, @PathVariable("page") int pageNumber) {
        List<Player> players = directory.players(pageNumber, pageSize);
        return ResponseEntity.ok().body(players);
    }
}
