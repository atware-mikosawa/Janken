package section4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRef {

    @Test
    void testDisplayPreparationCallOut() {
        //オブジェクト生成
        Player player1 = new Player("mikosawa");
        Player player2 = new Player("moko");
        Ref ref = new Ref("審判チョコ");

        String expected = """
                審判チョコ「現在の勝敗」
                審判チョコ「mikosawa：\\d+勝\\d+敗」
                審判チョコ「moko：\\d+勝\\d+敗」
                """;
        String actual = ref.displayPreparationCallOut(player1, player2);
        assertTrue(actual.matches(expected));
    }

    @Test
    void displayTestDisplayWinLoseResult() {
        //オブジェクト生成
        Player player1 = new Player("mikosawa");
        Player player2 = new Player("moko");
        Ref ref = new Ref("審判チョコ");
        //期待値
        String expected = """
                審判チョコ「mikosawa：\\d+勝\\d+敗」
                審判チョコ「moko：\\d+勝\\d+敗」
                """;
        //実測値
        String actual = ref.displayWinLoseResult(player1.getName(), player2.getName(), ref.getRefName());
        //比較
        assertTrue(actual.matches(expected));
    }

    @Test
    void mikosawaが勝った時勝敗記録に1足されること() {//
        //オブジェクト生成
        Player player1 = new Player("mikosawa");
        Player player2 = new Player("moko");
        Ref ref = new Ref("審判チョコ");
        Record record = new Record();
        ref.displayResult(Result.WIN, player1.getName(), player2.getName(), ref.getRefName());
        //期待値
        int winExpected = 1;
        int loseExpected = 1;
        //実測値
        int recordPlayer1 = ref.getWinRecordPlayer1();
        int recordPlayer2 = ref.getLoseRecordPlayer2();
        //比較
        assertEquals(winExpected, recordPlayer1);
        assertEquals(loseExpected, recordPlayer2);
    }

    @Test
    void mokoが勝った時勝敗記録に1足されること() {
        //オブジェクト生成
        Player player1 = new Player("mikosawa");
        Player player2 = new Player("moko");
        Ref ref = new Ref("審判チョコ");
        Record record = new Record();
        ref.displayResult(Result.LOSE, player1.getName(), player2.getName(), ref.getRefName());
        //期待値
        int loseExpected = 1;
        int winExpected = 1;
        //実測値
        int recordPlayer1 = ref.getLoseRecordPlayer1();
        int recordPlayer2 = ref.getWinRecordPlayer2();
        //比較
        assertEquals(loseExpected, recordPlayer1);
        assertEquals(loseExpected, recordPlayer2);
    }

}
