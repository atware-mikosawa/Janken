package section1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void getNameTest() {
        Waza waza = new Waza("わざ", 10);
        Character character = new Character("フシギダネ", 100, waza);
        String expect = "フシギダネ";
        String actual = character.getName();
        assertEquals(expect, actual);
    }

    @Test
    void getHpTest() {
        Waza waza = new Waza("わざ", 10);
        Character character = new Character("フシギダネ", 100, waza);
        int expected = 100;
        int actual = character.getHp();
        assertEquals(expected, actual);
    }

//    @Test
//    void getWazaTest() {
//        Waza waza = new Waza("わざ", 10);
//        Character character = new Character("フシギダネ", 100, waza);
//        Waza expected = "わざ";
//        Waza actual = character.getWaza();
//        assertEquals(expected, actual);
//    }
}