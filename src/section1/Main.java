package section1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Character myCharacter = new Character("フシギダネ", 100, "たいあたり", 10);
        Character enemyCharacter = new Character("ヒトカゲ", 150, "たいあたり", 40);

        MonsterBattle monsterBattle = new MonsterBattle(myCharacter, enemyCharacter);

        while (true) {
            int inputNum = monsterBattle.receiveNum();

            //味方のターン
            BattleResult battleStatus = monsterBattle.selectBattle(inputNum);
            BattleResult result = monsterBattle.myMonstarTurn(battleStatus);
            if (result == BattleResult.ESCAPE_SUCCESSFUL) {
                break;
            }
            monsterBattle.monsterStatus();

            //勝敗を判定
            int enemyMonsterHp = monsterBattle.getEnemyMonsterHp();
            boolean judgeEndMyTurn = monsterBattle.judgeBattle(enemyMonsterHp);
            if (judgeEndMyTurn) {
                break;
            }

            //敵のターン
            monsterBattle.enemyMonsterTurn();
            monsterBattle.monsterStatus();

            int myMonsterHp = monsterBattle.getMyMonsterHp();
            boolean judgeEndEnemyTurn = monsterBattle.judgeBattle(myMonsterHp);
            //勝敗を判定
            if (judgeEndEnemyTurn) {
                break;
            }
        }
        String printResult = monsterBattle.displayBattleResult();
        System.out.println(printResult);
    }
}
