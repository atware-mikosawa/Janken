package section2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBattleWild {

    @Test
    void testReceiveNum() {
        //オブジェクト生成
        BattleWild battleWild = new BattleWild();
        String inputNum = "1";
        InputStream originalSystemIn = new ByteArrayInputStream(inputNum.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(inputNum.getBytes());
        System.setIn(in);
        //期待値
        int expected = 1;
        //実測値
        int actual = battleWild.receiveNum();
        //比較
        System.setIn(originalSystemIn);
        assertThat(actual, is(expected));
    }

    @Test
    void decideOnTheFirstAndSecondAttacks() {
        BattleWild battleWild = new BattleWild();
        //期待値
        boolean expected = true;
        //実測値
        boolean actual = battleWild.decideWhoAttackFirstAndSecond(30, 0);
        //比較
        assertThat(actual, is(expected));
    }

    @Test
    void testEstablishmentIsCorrect() {
        BattleWild battleWild = new BattleWild();
        int trueCount = 0;
        int falseCount = 0;
        int totalTest = 1000;
        for (int i = 0; i < totalTest; i++) {
            if (battleWild.judgeFiftyFifty()) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        double trueEstablish = (double) trueCount / totalTest;
        double falseEstablish = (double) falseCount / totalTest;

        assertThat(trueEstablish, is(greaterThan(0.4)));//より大きい
        assertThat(trueEstablish, is(lessThan(0.6)));
        assertThat(falseEstablish, is(greaterThan(0.4)));
        assertThat(falseEstablish, is(lessThan(0.6)));
    }

    @Test
    void testGetCmd() {
        //オブジェクト生成
        String inputName = "フシギダネ";
        InputStream SystemIn = new ByteArrayInputStream(inputName.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(inputName.getBytes());
        System.setIn(in);
        BattleWild battleWild = new BattleWild();
        //期待値
        String expected = """
                フシギダネは　どうする？
                1:たたかう 2:にげる
                ▼
                """;
        //実測値
        int actual = battleWild.getCmd("フシギダネ");
        //比較
        assertThat(actual, is(expected));
    }
}
