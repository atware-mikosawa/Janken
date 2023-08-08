package section1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MonsterBattleTest {
    @Spy
    private MonsterBattle monsterBattle;

    @BeforeEach
    void setUp() {//共通部分
        Character myCharacter = new Character("フシギダネ", 100, "たいあたり", 10);
        Character enemyCharacter = new Character("ヒトカゲ", 150, "たいあたり", 10);
        monsterBattle = spy(new MonsterBattle(myCharacter, enemyCharacter));
    }


    //hamcrest
    @Test
    void testAttackMyMonster() {
        monsterBattle.attackMyMonster();
        //期待値
        int expected = 140;
        //実測値
        int actual = monsterBattle.getEnemyMonsterHp();
        //比較
        assertThat(actual, is(expected));
    }

    @Test
    void testAttackEnemyMonster() {
        monsterBattle.attackEnemyMonster();
        //期待値
        int expected = 90;
        //実測値
        int actual = monsterBattle.getMyMonsterHp();
        //比較
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }

    @Test
    void testDisplayAttackEnemyMonster() {
        int tmpDamege = 10;
        //期待値
        String expected = "敵のキャラクターのダメージ" + tmpDamege + "\n";
        //実測値
        String actual = monsterBattle.displayAttackEnemyMonster(tmpDamege);
        //比較
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }


    @Test
    void testJudgeBattle() {
        //期待値
        boolean expected = false;
        //実測値
        boolean actual = monsterBattle.judgeBattle(monsterBattle.getMyMonsterHp());
        //比較
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }

    @Test
    void testDisplayJudgeBattle() {
        //期待値
        String expected = "まだ生きています";
        //実測値
        String actual = monsterBattle.displayJudgeBattle(1);
        //比較
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }

    //hamcrest
    @Test
    void testDisplayBattleResult() {
        String actual = String.valueOf(monsterBattle.displayBattleResult());
        //比較
//        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual) || ecpected4.equals(actual));

        assertThat(actual, is(anyOf(
                equalTo("バトルから逃げました"),
                equalTo("winner is mymonster"),
                equalTo("winner is ennemymonster"),
                equalTo("正しく戦闘が終了できませんでした"))));
    }

    @Test
    void testDisplayMessageBeforeBattle() {
        //期待値
        String expected = """
                自分のターン
                1か2かを入力してください
                1：たたかう 2：逃げる
                """;
        //実測値
        String actual = monsterBattle.displayMessageBeforeBattle();
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }


    @Test
    void receiveNum() {
        //オブジェクト生成
        String inputNum = "1";
        InputStream originalSystemIn = new ByteArrayInputStream(inputNum.getBytes());
        ByteArrayInputStream in = new ByteArrayInputStream(inputNum.getBytes());
        System.setIn(in);
        //期待値
        int expected = 1;
        //実測値
        int actual = monsterBattle.receiveNum();
        //比較
        System.setIn(originalSystemIn);
//        assertEquals(expected, actual);
        assertThat(actual, is(expected));
    }

    @Test
    void testSelectBattle() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        BattleResult actual = monsterBattle.selectBattle(randomNum);
//        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual));
        assertThat(actual, is(anyOf(
                equalTo(BattleResult.BATTLE),
                equalTo(BattleResult.ESCAPE_SUCCESSFUL),
                equalTo(BattleResult.ESCAPE_FAILED)
        )));
    }

//    //ビヘイビア駆動
//    @Test
//    @DisplayName("引数のstatusにBATTLEが渡された時falseが返ること")
//    void testMyMonsterTurn1() {
//        boolean expected = false;
//        boolean actual = monsterBattle.myMonsterTurn(BattleResult.BATTLE);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("引数のstatusにESCAPE_FAILEDが渡された時falseが返ること")
//    void testMyMonsterTurn2() {
//        boolean expected = false;
//        boolean actual = monsterBattle.myMonsterTurn(BattleResult.ESCAPE_FAILED);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    @DisplayName("引数のstatusにESCAPE_SUCCESSFULが渡された時trueが返ること")
//    void testMyMonsterTurn3() {
//        boolean expected = true;
//        boolean actual = monsterBattle.myMonsterTurn(BattleResult.ESCAPE_SUCCESSFUL);
//        assertEquals(expected, actual);
//    }
//
//
//    @Test
//    @DisplayName("引数のstatusにUNKOWNが渡された時IllegalArgumentExceptionが返ること")
//    void testMyMonsterTurn4() {
//        assertThrows(IllegalArgumentException.class, () -> monsterBattle.myMonsterTurn(BattleResult.UNKNOWN));
//    }

    @Test
    @DisplayName("引数のstatusにESCAPE_SUCCESSFULが渡された時trueが返ること")
    void testMyMonsterTurn3() {
        monsterBattle.myMonsterTurn(BattleResult.BATTLE);
        verify(monsterBattle, times(1)).attackMyMonster();
    }


    @Test
    void testDisplayBattleMyMonsterTurn() {
        Character character = new Character("ゼニガメ", 100, "たいあたり", 20);
        String expected = "敵に20ダメージを与えました";
        int wazaDamage = character.getWaza().getDamage();
        String actual = monsterBattle.displayBattleMyMonsterTurn(wazaDamage);
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayEscapeFailedMyMonsterTurn() {
        String expected = "逃げきれませんでした";
        String actual = monsterBattle.displayEscapeFailedMyMonsterTurn();
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayEnemyMonsterTurn() {
        //期待値
        String expected = """
                敵のターン
                敵のキャラクターのダメージ10
                フシギダネは10ダメージを受けました
                """;
        //実測値
        //比較
        String actual = monsterBattle.displayEnemyMonsterTurn();
        assertEquals(expected, actual);
    }

    @Test
    void testMyMonsterStatus() {
        String expected = "フシギダネのHP：100";
        String actual = monsterBattle.myMonsterStatus();
        assertEquals(expected, actual);
    }

    @Test
    void testEnemyMonsterStatus() {
        String expected = "ヒトカゲのHP：150";
        String actual = monsterBattle.enemyMonsterStatus();
        assertEquals(expected, actual);
    }


    //テスト用メソッド
    BattleResult tmp = null;

    private void randomBattleResult(int num) {
        if (num == 0) {
            tmp = BattleResult.BATTLE;
        } else if (num == 1) {
            tmp = BattleResult.ESCAPE_FAILED;
        } else {
            tmp = BattleResult.ESCAPE_SUCCESSFUL;
        }
    }

    @Test
    void shouldBeAliveAtTheStart() {

        var monster = new Monster();

        assertEquals(false, monster.isDead());
    }


    @Test
    void shouldBeAliveWhenHitPointMoreThanOne() {

        var monster = new Monster();

        monster.receiveDamage(99);

        assertEquals(false, monster.isDead());
    }

    @Test
    void shouldBeAliveWhenHitPointIsZeroOrBelow() {

        var monster = new Monster();

        monster.receiveDamage(100);

        assertEquals(true, monster.isDead());
    }

    class Monster {
        private int hitPoint = 100;

        Monster() {

        }

        void receiveDamage(int attackPoint) {
            hitPoint = hitPoint - attackPoint;
        }

        boolean isDead() {
            return hitPoint <= 0;
        }

        public String print() {
            return String.format("モンスター(hitPoint=%s, isDead=%s)", hitPoint, isDead());
        }
    }
}
