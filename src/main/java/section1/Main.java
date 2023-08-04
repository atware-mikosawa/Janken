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
            monsterBattle.myMonsterTurn(battleStatus);
            if (battleStatus == BattleResult.ESCAPE_SUCCESSFUL) {
                break;
            }
            String displayMonsterStatus1 = monsterBattle.monsterStatus();
            System.out.println(displayMonsterStatus1);

            //勝敗を判定
            int enemyMonsterHp = monsterBattle.getEnemyMonsterHp();
            System.out.println(monsterBattle.displayJudgeBattle(enemyMonsterHp));
            boolean judgeEndMyTurn = monsterBattle.judgeBattle(enemyMonsterHp);
            if (judgeEndMyTurn) {
                break;
            }

            //敵のターン
            System.out.println(monsterBattle.displayEnemyMonsterTurn());
            String displayMonsterStatus2 = monsterBattle.monsterStatus();
            System.out.println(displayMonsterStatus2);

            int myMonsterHp = monsterBattle.getMyMonsterHp();
            System.out.println(monsterBattle.displayJudgeBattle(myMonsterHp));
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
