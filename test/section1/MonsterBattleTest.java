package section1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonsterBattleTest {
    @Test
    void testPrintWinner() {
        //オブジェクト生成
        Character myCharacter = new Character("フシギダネ", 100, "たいあたり", 10);
        Character enemyCharacter = new Character("ヒトカゲ", 150, "たいあたり", 10);
        MonsterBattle monsterBattle = new MonsterBattle(myCharacter, enemyCharacter);
        //期待値
        String expected1 = "winner is mymonster";
        String expected2 = "winner is ennemymonster";
        String ecpected3 = "正しく戦闘が終了できませんでした。";
        //実測値
        String actual = String.valueOf(monsterBattle.printWinner());
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || ecpected3.equals(actual));
    }
}
