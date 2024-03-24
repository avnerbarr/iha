package com.example.demo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PlayerDirectory {
    // maps between playerId to player object
    private HashMap<String, Player> players;


    public PlayerDirectory() {
        players = new HashMap<>();
        try {
            InputStream inputStream = getClass().getResourceAsStream("/player.csv");
            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
            String[] headers = reader.readNext(); // Skip headers
            String[] data;
            while ((data = reader.readNext()) != null) {
                Player player = playerOrNull(data);
                if (player != null) {
                    // we will map the player id to the play object to support lookup
                    players.put(player.getPlayerID(), player);
                }
            }

        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // get the player object by player id
    public @Nullable Player getPlayer(String playerId) {
        return players.get(playerId);
    }

    // get all the players
    public Collection<Player> allPlayers() {
        return players.values();
    }

    // support paging of players
    // given a page size and a page number will return the relevant players
    // if the page is invalid will return the closest result
    public List<Player> players(int pageNumber, int pageSize) {
        if (players.isEmpty() || pageNumber < 0 || pageSize < 0) {
            // return fast
            return new ArrayList<>();
        }
        if (pageSize > players.size()) {
            pageNumber = 0;
            pageSize = players.size() - 1;
        }
        int offset = pageNumber * pageSize;

        if (offset > players.size()) {
            // take last page
            offset = max((players.size() / pageSize) - 1, 0);
        }
        return players.values().stream().toList().subList(offset, min(offset + pageSize, players.size()));
    }

    private static Player playerOrNull(String[] data) {
        try {
            return Player.createPlayer(data);
        } catch (Error | Player.ParseException e) {
            return null;
        }
    }
}
