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
    void 逃走成功率が正しく計算されていること() {
        BattleWild battleWild = new BattleWild();
        int trueCount = 0;
        int falseCount = 0;
        int totalTest = 1000;
        for (int i = 0; i < totalTest; i++) {
            if (battleWild.challengeEscape(10, 20)) {
                trueCount++;
            } else {
                falseCount++;
            }
        }
        double trueEstablish = (double) trueCount / totalTest;
        double falseEstablish = (double) falseCount / totalTest;

        assertThat(trueEstablish, is(greaterThan(0.2)));//より大きい
        assertThat(trueEstablish, is(lessThan(0.3)));
        assertThat(falseEstablish, is(greaterThan(0.7)));
        assertThat(falseEstablish, is(lessThan(0.8)));

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
    void 与えるダメージが正常に計算されること() {
        Monster monster = new Monster("ぼく", "ヒトカゲ", 1);
        int expected = (15 * 1) - 10;
        int actual = monster.wazaDmg(monster.getAtk(), monster.getWazaDmgRate(), 10);
        assertThat(actual, is(expected));
    }

    @Test
    void 与えるダメージが0を下回った時1ダメージになること() {
        Monster monster = new Monster("ぼく", "ヒトカゲ", 2);
        int expected = 1;
        int actual = monster.wazaDmg(monster.getAtk(), monster.getWazaDmgRate(), 100);
        assertThat(actual, is(expected));
    }


//    @Test
//    void testMonsterAttack() {
//        //オブジェクト生成
//        Monster monster = new Monster("mikosawa", "フシギダネ", 1);//attack:15 def:10
//        //期待値
//        int calSubtraction = 15 / (1 + (10 / 120));
//        int expected = calSubtraction * 15;
//        //実測値
//        int actual = monster.calculationDamaged(monster.getAtk());
//        //比較
//        assertThat(actual, is(expected));
//    }
}
