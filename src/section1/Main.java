package section1;

public class Main {
    public static void main(String[] args) {
        Waza myCharacterWaza = new Waza("たいあたり", 10);
        Character myCharacter = new Character("フシギダネ", 100, myCharacterWaza);

        Waza enemyCharacterWaza = new Waza("たいあたり", 7);
        Character enemyCharacter = new Character("ヒトカゲ", 150, enemyCharacterWaza);

        MonsterBattle monsterBattle = new MonsterBattle(myCharacter, enemyCharacter);
        for (; ; ) {
            monsterBattle.attackMyMonster();
            boolean flag = monsterBattle.judgeBattle();
            if (flag) {
                break;
            }
            monsterBattle.attackEnemyMonster();
            flag = monsterBattle.judgeBattle();
            if (flag) {
                break;
            }
        }
        String printResult =  monsterBattle.printWinner();
        System.out.println(printResult);
    }
}
