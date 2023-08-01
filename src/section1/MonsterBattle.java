package section1;

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

}
