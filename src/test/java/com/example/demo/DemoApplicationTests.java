package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class DemoApplicationTests {
    @Test
    void testParsingOfCsv() {
        PlayerDirectory playerDirectory = new PlayerDirectory();
        Player nonExistantInstance = playerDirectory.getPlayer("nonExistantId");
        assertNull(nonExistantInstance);
        Player aardsda01 = playerDirectory.getPlayer("aardsda01");
        assertEquals(aardsda01.getPlayerID(), "aardsda01");
        assertEquals(aardsda01.getBirthYear(), 1981);
        assertEquals(aardsda01.getBirthMonth(), 12);
        assertEquals(aardsda01.getBirthDay(), 27);
        assertEquals(aardsda01.getBirthCountry(), "USA");
        assertEquals(aardsda01.getBirthState(), "CO");
        assertEquals(aardsda01.getBirthCity(), "Denver");
        assertNull(aardsda01.getDeathYear());
        assertNull(aardsda01.getDeathMonth());
        assertNull(aardsda01.getDeathDay());
        assertNull(aardsda01.getDeathCountry());
        assertNull(aardsda01.getDeathState());
        assertNull(aardsda01.getDeathCity());
        assertEquals(aardsda01.getNameFirst(), "David");
        assertEquals(aardsda01.getNameLast(), "Aardsma");
        assertEquals(aardsda01.getNameGiven(), "David Allan");
        assertEquals(aardsda01.getWeight(), 215);
        assertEquals(aardsda01.getHeight(), 75);
        assertEquals(aardsda01.getBats(), Player.Handedness.R);
        assertEquals(aardsda01.getThrowsVal(), Player.Handedness.R);
        assertEquals(aardsda01.getDebut(), LocalDate.parse("2004-04-06"));
        assertEquals(aardsda01.getFinalGame(), LocalDate.parse("2015-08-23"));
        assertEquals(aardsda01.getRetroID(), "aardd001");
        assertEquals(aardsda01.getBbrefID(), "aardsda01");





    }

}
