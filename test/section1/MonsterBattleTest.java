package section1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonsterBattleTest {
    private MonsterBattle monsterBattle;

    @BeforeEach
    void setUp() {//共通部分
        //オブジェクト生成
        Character myCharacter = new Character("フシギダネ", 100, "たいあたり", 10);
        Character enemyCharacter = new Character("ヒトカゲ", 150, "たいあたり", 10);
        monsterBattle = new MonsterBattle(myCharacter, enemyCharacter);
    }

    @Test
    void testAttackMyMonster() {
        monsterBattle.attackMyMonster();
        //期待値
        int expected = 140;
        //実測値
        int actual = monsterBattle.getEnemyMonsterHp();
        //比較
        assertEquals(expected, actual);
    }

    @Test
    void testAttackEnemyMonster() {
        monsterBattle.attackEnemyMonster();
        //期待値
        int expected = 90;
        //実測値
        int actual = monsterBattle.getMyMonsterHp();
        //比較
        assertEquals(expected, actual);
    }

    @Test
    void testJudgeBattle() {
        //期待値
        boolean expected = false;
        //実測値
        boolean actual = monsterBattle.judgeBattle(monsterBattle.getMyMonsterHp());
        //比較
        assertEquals(expected, actual);
    }

    @Test
    void testDisplayJudgeBattle() {
        //期待値
        String expected = "まだ生きています";
        //実測値
        String actual = monsterBattle.displayJudgeBattle(1);
        //比較
        assertEquals(expected, actual);
    }


    @Test
    void testDisplayBattleResult() {
        //期待値
        String expected1 = "バトルから逃げました";
        String expected2 = "winner is mymonster";
        String expected3 = "winner is ennemymonster";
        String ecpected4 = "正しく戦闘が終了できませんでした";
        //実測値
        String actual = String.valueOf(monsterBattle.displayBattleResult());
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual) || ecpected4.equals(actual));
    }

//    @Test
//    void receiveNum() {
//        //オブジェクト生成
//        Character myCharacter = new Character("フシギダネ", 100, "たいあたり", 10);
//        Character enemyCharacter = new Character("ヒトカゲ", 150, "たいあたり", 10);
//        MonsterBattle monsterBattle = new MonsterBattle(myCharacter, enemyCharacter);
//        //期待値
//        int expected1 = 1;
//        int expected2 = 2;
//        //実測値
//        int actual = monsterBattle.receiveNum();
//        //比較
//        assertTrue(expected1 == actual || expected2 == actual);
//    }

    @Test
    void testSelectBattle() {
        //期待値
        BattleResult expected1 = BattleResult.BATTLE;
        BattleResult expected2 = BattleResult.ESCAPE_SUCCESSFUL;
        BattleResult expected3 = BattleResult.ESCAPE_FAILED;
        //実測値
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1;
        BattleResult actual = monsterBattle.selectBattle(randomNum);
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual));
    }

    @Test
    void testMyMonsterTurn() {
        //期待値
        BattleResult expected1 = BattleResult.BATTLE;
        BattleResult expected2 = BattleResult.ESCAPE_FAILED;
        BattleResult expected3 = BattleResult.ESCAPE_SUCCESSFUL;
        //実測値
        Random rand = new Random();
        int randomNum = rand.nextInt(3);//0~2の生成
        randomBattleResult(randomNum);
        BattleResult actual = monsterBattle.myMonstarTurn(tmp);

        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual));
    }

    @Test
    void testEnemyMonsterTurn() {
        //期待値
        String expected = """
                敵のターン
                フシギダネは10ダメージを受けました
                """;
        //実測値
        String actual = monsterBattle.enemyMonsterTurn();
        //比較
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
}
