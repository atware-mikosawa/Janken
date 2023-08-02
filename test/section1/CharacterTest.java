package section1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void getNameTest() {
        Waza waza = new Waza("わざ", 10);
        Character character = new Character("フシギダネ", 100, "たいあたり", 10);
        String expect = "フシギダネ";
        String actual = character.getName();
        assertEquals(expect, actual);
    }

    @Test
    void getHpTest() {
        Waza waza = new Waza("わざ", 10);
        Character character = new Character("フシギダネ", 100, "たいあたり", 10);
        int expected = 100;
        int actual = character.getHp();
        assertEquals(expected, actual);
    }

}