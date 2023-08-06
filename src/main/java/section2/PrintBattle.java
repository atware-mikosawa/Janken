package section2;

public class PrintBattle {

    public String messageWhenEnemyMonsterAppears(String enemyName) {
        String result = "";
        result += "あっ！ やせいの " + enemyName + " が とびだしてきた！";
        return result;
    }

    public String messageWhenMyMonsterAppears(String myMonster) {
        String result = "";
        result += "ゆけ！ " + myMonster + "！";
        return result;
    }

    public String displayOfTurns(int turn) {
        String result = "";
        result += "～～～ターン" + turn + "～～～";
        return result;
    }

    public String displayEnemyMonsterStatus(String enemyMonsterStatus) {
        String result = "";
        result += "あいて：" + enemyMonsterStatus;
        return result;
    }

    public String displayMyMonsterStatus(String myMonsterStatus) {
        String result = "";
        result += "こちら：" + myMonsterStatus;
        return result;
    }

    public String displayMessageBeforeBattle(String monsterName) {
        String result = "";
        result += monsterName + "は どうする？" + "\n";
        result += "1:たたかう 2:にげる";
        return result;
    }

    public String displayPauseMessage() {
        return "▼";
    }


}
