package section1;

import java.util.InputMismatchException;
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

    public int getMyMonsterHp() {
        return myMonsterHp;
    }

    public int getEnemyMonsterHp() {
        return enemyMonsterHp;
    }


    public void attackMyMonster() {
        enemyMonsterHp = enemyMonsterHp - myMonster.getWaza().getDamage();
    }

    public void attackEnemyMonster() {
        System.out.println("敵のキャラクターのダメージ" + enemyMonster.getWaza().getDamage());
        myMonsterHp = myMonsterHp - enemyMonster.getWaza().getDamage();
    }


    public boolean judgeBattle(int hpState) {
        if (hpState <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String displayJudgeBattle(int hpState) {
        String displayResult = "";
        if (hpState <= 0) {
            displayResult += "HPが0になりました";
        } else {
            displayResult += "まだ生きています";
        }
        return displayResult;
    }

    public String displayBattleResult() {
        String result = "";
        //hpが残っている場合、逃げるを選択したと判定する。
        if (myMonsterHp > 0) {
            result = "バトルから逃げました";
        } else if (myMonsterHp < 0) {
            result = "winner is ennemymonster";
        } else if (enemyMonsterHp < 0) {
            result = "winner is mymonster";
        } else {
            result = "正しく戦闘が終了できませんでした";
        }
        return result;
    }

    //標準入力を受け取るメソッドを作成
    public int receiveNum() {
        System.out.println("自分のターン");
        System.out.println("1か2かを入力してください");
        System.out.println("1：たたかう" + " " + "2：逃げる\n");
        Scanner sc = new Scanner(System.in);
        int test = 0;

        try {
            test = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("入力する値が不正です");
        }
        return test;
    }

    //1の場合戦う、2の場合逃げる
    public BattleResult selectBattle(int selectionNum) {
        if (selectionNum == 1) {//戦う
            System.out.println("たたかうが選択されました");
            return BattleResult.BATTLE;
        } else if (selectionNum == 2) {
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
    public BattleResult myMonstarTurn(BattleResult status) {
        switch (status) {
            case BATTLE://BATTLEの時
                attackMyMonster();
                System.out.println("敵に" + myMonster.getWaza().getDamage() + "ダメージを与えました");
                return BattleResult.BATTLE;

            case ESCAPE_FAILED://逃げるが失敗した時
                System.out.println("逃げきれませんでした");
                return BattleResult.ESCAPE_FAILED;

            case ESCAPE_SUCCESSFUL://逃げるが成功した時
                return BattleResult.ESCAPE_SUCCESSFUL;

        }
        throw new IllegalArgumentException("無効な戦闘結果が渡されました: " + status);
    }

    //敵モンスターのターンでの行動
    public String enemyMonsterTurn() {
        String result = "";
        result += "敵のターン\n";
        attackEnemyMonster();
        result += this.myMonster.getName() + "は" + enemyMonster.getWaza().getDamage() + "ダメージを受けました\n";
        return result;
    }

    //味方モンスターのステータスを返すメソッド
    public String myMonsterStatus() {
        String result = "";
        result += this.myMonster.getName() + "のHP：" + this.myMonsterHp;
        return result;
    }

    //敵モンスターのステータスを返すメソッド
    public String enemyMonsterStatus() {
        String result = "";
        result += this.enemyMonster.getName() + "のHP：" + this.enemyMonsterHp;
        return result;
    }

    public String monsterStatus() {
        String result = "";
        result += myMonsterStatus() + "\n";
        result += enemyMonsterStatus() + "\n";
        return result;
    }
}
