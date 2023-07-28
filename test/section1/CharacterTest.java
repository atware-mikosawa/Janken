package section1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void getNameTest(){
        Character character = new Character("フシギダネ", 100, "わざ");
        String expect = "フシギダネ";
        String actual = character.getName();
        assertEquals(expect,actual);
    }
    @Test
    void getHpTest(){
        Character character = new Character("フシギダネ", 100, "わざ");
        int expected = 100;
        int actual = character.getHp();
        assertEquals(expected,actual);
    }
    @Test
    void getWazaTest(){
        Character character = new Character("フシギダネ", 100, "わざ");
        String expected = "わざ";
        String actual = character.getWaza();
        assertEquals(expected,actual);
    }
}