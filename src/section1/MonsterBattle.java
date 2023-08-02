package section1;

import java.util.Random;
import java.util.Scanner;

public class MonsterBattle {
    private Character myMonster;
    private Character enemyMonster;

    private int myMonsterHp;
    private int enemyMonsterHp;

    public MonsterBattle(Character myMonster, Character enemyMonster) {
        this.myMonster = myMonster;
        this.enemyMonster = enemyMonster;
        this.myMonsterHp = myMonster.getHp();
        this.enemyMonsterHp = enemyMonster.getHp();
    }

    public void attackMyMonster() {
        enemyMonsterHp = enemyMonsterHp - myMonster.getWaza().getDamage();
    }

    public void attackEnemyMonster() {
        myMonsterHp = myMonsterHp - enemyMonster.getWaza().getDamage();
    }

    public boolean judgeBattle() {
        if (enemyMonsterHp <= 0) {
            return true;
        }
        if (myMonsterHp <= 0) {
            return true;
        }
        return false;
    }

    public String printWinner() {
        String result = "";
        if (enemyMonsterHp <= 0) {
            result = "winner is mymonster";
            return result;
        } else if (myMonsterHp <= 0) {
            result = "winner is ennemymonster";
            return result;
        } else {
            result = "正しく戦闘が終了できませんでした。";
            return result;
        }

    }

    //標準入力を受け取るメソッドを作成
    public int receiveNum() {
        System.out.println("0か1かを入力してください");
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        return test;
    }

    //1の場合戦う、2の場合逃げる
    public BattleResult selectBattle(int selectionNum) {
        if (selectionNum == 1) {
            //戦う
            enemyMonsterHp = enemyMonsterHp - myMonster.getWaza().getDamage();
            return BattleResult.BATTLE;
        } else if (selectionNum == 0) {
            //50%の確率で逃げたい
            Random rand = new Random();
            int num = rand.nextInt(2);
            if (num < 1) {//逃げる
                return BattleResult.ESCAPE_SUCCESSFUL;
            } else {//バトル続行
                return BattleResult.ESCAPE_FAILED;
            }
        } else {
            throw new RuntimeException("1か0以外が入力されました");
        }
    }

    //BattleResult型を受け取って、それに対応する処理を返すメソッドを作成


}
