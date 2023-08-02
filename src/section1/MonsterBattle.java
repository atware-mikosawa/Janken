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
        System.out.println("判定するHP：" + hpState);
        if (hpState <= 0) {
            System.out.println("HPが0になりました");
            return true;
        } else {
            System.out.println("まだ生きています");
            return false;
        }
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
        System.out.println("0か1かを入力してください");
        System.out.println("1：たたかう" + " " + "0：逃げる\n");
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        return test;
    }

    //1の場合戦う、2の場合逃げる
    public BattleResult selectBattle(int selectionNum) {
        if (selectionNum == 1) {//戦う
            System.out.println("たたかうが選択されました");
            return BattleResult.BATTLE;
        } else if (selectionNum == 0) {
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

            default:
                throw new IllegalArgumentException("無効な戦闘結果が渡されました: " + status);
        }
    }

    //敵モンスターのターンでの行動
    public void enemyMonsterTurn() {
        System.out.println("敵のターン");
        attackEnemyMonster();
        System.out.println(this.myMonster.getName() + "は" + enemyMonster.getWaza().getDamage() + "ダメージを受けました");
    }

    //味方モンスターのステータスを返すメソッド
    public void mymonsterStatus() {
        System.out.println(this.myMonster.getName() + "のHP：" + this.myMonsterHp);
    }

    //敵モンスターのステータスを返すメソッド
    public void enemyMonsterStatus() {
        System.out.println(this.enemyMonster.getName() + "：のHP：" + this.enemyMonsterHp);
    }

    public void monsterStatus() {
        mymonsterStatus();
        enemyMonsterStatus();
    }
}
