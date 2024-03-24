package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// The instructions are loose, so my assumption is that the following must be correct in order to construct a player object:
// 1. Every user has a user ID - there was no criteria set on how a valid id looks, so we just accept any non-empty string as valid
// 2. weight/age/height etc. should be positive numbers
// 4. for dates - day lies between 1, 31 range, month lies between 1,12, year - I suppose any positive integer could be valid
// 5. birth city/ birth state / address/ death city / death state etc. are all optional - they don't necessarily have to exist
// 6. All cities/countries/states could be valid - In general you would want a database of all valid addresses to verify against

public class Player {
    // describes the dominant hand of the player
    public enum Handedness {
        L,
        R
    }

    // A general exception when parsing a value from the CSV file
    public static class ParseException extends Exception {
        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private String playerID;

    public String getPlayerID() {
        return playerID;
    }

    // primitives are not optional and must exist on a player
    private int birthYear;

    public int getBirthYear() {
        return birthYear;
    }


    private int birthMonth;

    public int getBirthMonth() {
        return birthMonth;
    }

    private int birthDay;

    public int getBirthDay() {
        return birthDay;
    }

    private String birthCountry;

    public String getBirthCountry() {
        return birthCountry;
    }

    private String birthState;

    public String getBirthState() {
        return birthState;
    }

    private String birthCity;

    public String getBirthCity() {
        return birthCity;
    }

    // Integers (ref types in general) can be empty/null and the non-existence doesn't interfere in creating a valid player object
    // in cases where the value doesn't exist, we set the value to null (not possible to set null on primitive type in Java)
    private Integer deathYear;

    public Integer getDeathYear() {
        return deathYear;
    }

    private Integer deathMonth;

    public Integer getDeathMonth() {
        return deathMonth;
    }

    private Integer deathDay;

    public Integer getDeathDay() {
        return deathDay;
    }

    private String deathCountry;

    public String getDeathCountry() {
        return deathCountry;
    }

    private String deathState;

    public String getDeathState() {
        return deathState;
    }

    private String deathCity;

    public String getDeathCity() {
        return deathCity;
    }

    private String nameFirst;

    public String getNameFirst() {
        return nameFirst;
    }

    private String nameLast;

    public String getNameLast() {
        return nameLast;
    }

    private String nameGiven;

    public String getNameGiven() {
        return nameGiven;
    }

    private Integer weight;

    public Integer getWeight() {
        return weight;
    }

    private Integer height;

    public Integer getHeight() {
        return height;
    }

    private Handedness bats;

    public Handedness getBats() {
        return bats;
    }
    @JsonProperty("throws")
    private Handedness throwsVal;

    public Handedness getThrowsVal() {
        return throwsVal;
    }

    private LocalDate debut;

    public LocalDate getDebut() {
        return debut;
    }

    private LocalDate finalGame;

    public LocalDate getFinalGame() {
        return finalGame;
    }

    private String retroID;

    public String getRetroID() {
        return retroID;
    }

    private String bbrefID;

    public String getBbrefID() {
        return bbrefID;
    }

    static private Integer parseNullableInt(String value) throws ParseException {
        try {
            Integer i = Integer.parseInt(value);
            if (i < 0) {
                throw new ParseException("Negative number", null);
            }
            return i;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static private Handedness parseHand(String value) {
        if (value == null) {
            return null;
        }
        if (value.equalsIgnoreCase("l")) {
            return Handedness.L;
        }
        if (value.equalsIgnoreCase("r")) {
            return Handedness.R;
        }
        return null;
    }

    static private LocalDate parseNullableDate(String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // Empty strings should be nulled to avoid confusion
    static private String parseNullableEmptyString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return value;
    }

    static Player createPlayer(String[] data) throws NumberFormatException, ParseException {
        Player player = new Player();
        // this must exist
        player.playerID = data[0];
        if (player.playerID == null || player.playerID.isEmpty()) {
            throw new ParseException("Invalid playerId", null);
        }
        try {
            player.birthYear = Integer.parseInt(data[1]);
            // any year could be valid - in reallity we would expect that a user be within some valid range but we will allow all for this example
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid birth year", e);
        }

        try {
            player.birthMonth = Integer.parseInt(data[2]);
            if (player.birthMonth < 1 || player.birthMonth > 12) {
                throw new ParseException("Invalid birth month range", null);
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid birth month", e);
        }

        try {
            player.birthDay = Integer.parseInt(data[3]);
            if (player.birthDay < 1 || player.birthDay > 31) {
                throw new ParseException("Invalid birth day range", null);
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid birth day", e);
        }


        player.birthCountry = data[4];
        player.birthState = data[5];
        player.birthCity = data[6];
        player.deathYear = parseNullableInt(data[7]);
        player.deathMonth = parseNullableInt(data[8]);
        player.deathDay = parseNullableInt(data[9]);
        player.deathCountry = parseNullableEmptyString(data[10]);
        player.deathState = parseNullableEmptyString(data[11]);
        player.deathCity = parseNullableEmptyString(data[12]);
        player.nameFirst = data[13];
        player.nameLast = data[14];
        player.nameGiven = data[15];
        player.weight = parseNullableInt(data[16]);
        player.height = parseNullableInt(data[17]);
        player.bats = parseHand(data[18]);
        player.throwsVal = parseHand(data[19]);
        player.debut = parseNullableDate(data[20]);
        player.finalGame = parseNullableDate(data[21]);
        player.retroID = data[22];
        player.bbrefID = data[23];
        return player;
    }
}
