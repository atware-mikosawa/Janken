package section2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintBattleTest {
    private PrintBattle printBattle;

    @BeforeEach
    void setUp() {
        //オブジェクト生成
        printBattle = new PrintBattle();
    }

    @Test
    void testDisplayPreGameMessage() {
        //期待値
        String expected = "あっ！ やせいの フシギダネ が とびだしてきた！";
        //実測値
        String actual = printBattle.messageWhenEnemyMonsterAppears("フシギダネ");
        //比較
        assertEquals(expected, actual);
    }

    @Test
    void testMessageWhenMyMonsterAppears() {
        //期待値
        String expected = "ゆけ！ ヒトカゲ！";
        //実測値
        String actual = printBattle.messageWhenMyMonsterAppears("ヒトカゲ");
        //比較
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayOfTurns() {
        String expected = "～～～ターン1～～～";
        String actual = printBattle.displayOfTurns(1);
        assertEquals(expected, actual);
    }

    @Test
    void testGetStatus() {
        String expected = "[フシギダネ lv20 HP70/80]";
        String actual = printBattle.getStatus("フシギダネ", 20, 70, 80);
        assertThat(actual, is(expected));
    }

    @Test
    void testDisplayEnemyMonsterStatus() {
        String expected = "あいて：[フシギダネ lv20 HP80/80]";
        String actual = printBattle.displayEnemyMonsterStatus("[フシギダネ lv20 HP80/80]");
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayMyMonsterStatus() {
        String expected = "こちら：フシギダネのステータス";
        String actual = printBattle.displayMyMonsterStatus("フシギダネのステータス");
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayMessageBeforeBattle() {
        String expected = """
                ヒトカゲのターン
                ヒトカゲは どうする？
                1:たたかう 2:にげる""";
        String actual = printBattle.displayMessageBeforeBattle("ヒトカゲ");
        assertThat(actual, is(expected));
    }

    @Test
    void testDisplayPauseMessage() {
        String expected = "▼";
        String actual = printBattle.displayPauseMessage();
        assertEquals(expected, actual);
    }

}
