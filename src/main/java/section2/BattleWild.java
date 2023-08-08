package section2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

class BattleWild {

    PrintBattle printBattle = new PrintBattle();
    final int ESCAPE_SUCCESS_ST_RATE = 50;     //「にげる」成功率の基準値（50%）

    public void mainBattle() {
        boolean winFlg = true;       //勝利フラグ（初期値true）
        boolean escapeFlg = false;   //逃走フラグ（初期値false）
        int combatCommand;           //ユーザーから受け取る戦闘コマンド
        int turn = 0;                //ターン数（初期値0）
        BattleResult battleResult = null;

        //モンスターのインスタンス化
        Monster enemy = new Monster("やせい", "フシギダネ", 20);
        Monster myMonster = new Monster("ぼく", "カケ郎", 1);

        int myMonsterHp = myMonster.getHp();
        int enemyMonsterHp = enemy.getHp();

        while (myMonsterHp > 0 && enemyMonsterHp > 0) {
            prinTextAsGame(printBattle.messageWhenEnemyMonsterAppears(enemy.getName()));
            prinTextAsGame(printBattle.displayPauseMessage());

            prinTextAsGame(printBattle.messageWhenMyMonsterAppears(myMonster.getName()));
            prinTextAsGame(printBattle.displayPauseMessage());

            turn = turn + 1;     //繰り返しのたびにターン数を+1する
            combatCommand = 0;
            prinTextAsGame(printBattle.displayOfTurns(turn));
            //互いのステータスの表示
            prinTextAsGame(printBattle.displayEnemyMonsterStatus(
                    printBattle.getStatus(
                            enemy.getName(),
                            enemy.getLv(),
                            enemyMonsterHp,
                            enemy.getHpMax()
                    )));
            prinTextAsGame(printBattle.displayMyMonsterStatus(
                    printBattle.getStatus(
                            myMonster.getName(),
                            myMonster.getLv(),
                            myMonsterHp,
                            myMonster.getHpMax()
                    )));
//            prinTextAsGame();

            //先行後攻の決定
            boolean firstAtkFlg = decideWhoAttackFirstAndSecond(myMonster.getSpd(), enemy.getSpd());

            //マイモンスターが先行の場合、コマンド入力して攻撃、そうでない場合相手の攻撃
            if (firstAtkFlg) {
                prinTextAsGame(printBattle.displayMessageBeforeBattle(myMonster.getName()));
                prinTextAsGame(printBattle.displayPauseMessage());
                combatCommand = receiveNum();
                if (combatCommand == 1) {          //戦う場合
                    int dmgDealt = myMonster.wazaDmg(myMonster.getAtk(), myMonster.getWazaDmgRate(), enemy.getDef());
                    enemyMonsterHp = enemyMonsterHp - dmgDealt;
                    prinTextAsGame(printBattle.displayWazaName(myMonster.getName(), myMonster.getWazaNm()));
                    prinTextAsGame(printBattle.displayDmgDealt(enemy.getName(), dmgDealt));
                } else if (combatCommand == 2) {   //逃げる場合
                    escapeFlg = challengeEscape(myMonster.getSpd(), enemy.getSpd());
                    if (escapeFlg) {
                        battleResult = BattleResult.ESCAPE_SUCCESSFUL;
                        break;
                    } else {
                        prinTextAsGame(printBattle.displayWhenRunningAway());
                    }
                }

                prinTextAsGame(printBattle.displayPauseMessage());
                //相手のモンスターの行動
                if (enemyMonsterHp > 0) {
                    //相手モンスターからダメージを受ける
                    int dmgDealt = enemy.wazaDmg(enemy.getAtk(), enemy.getWazaDmgRate(), myMonster.getDef());
                    myMonsterHp = myMonsterHp - dmgDealt;
                    prinTextAsGame(printBattle.displayWazaName(enemy.getName(), enemy.getWazaNm()));
                    prinTextAsGame(printBattle.displayDmgDealt(myMonster.getName(), dmgDealt));
                    if (myMonsterHp < 0) {
                        winFlg = false;
                    }
                } else {
                    battleResult = BattleResult.WIN;
                    break;
                }

            } else {//自分が後攻の時、相手の攻撃
                //相手モンスターからダメージを受ける
                int dmgDealt = enemy.wazaDmg(enemy.getAtk(), enemy.getWazaDmgRate(), myMonster.getDef());
                myMonsterHp = myMonsterHp - dmgDealt;
                prinTextAsGame(printBattle.displayWazaName(enemy.getName(), enemy.getWazaNm()));
                prinTextAsGame(printBattle.displayDmgDealt(myMonster.getName(), dmgDealt));
                if (myMonsterHp < 0) {//自分のモンスターのHPが0の時
                    battleResult = BattleResult.LOSE;
                    break;
                } else {//自分の攻撃
                    prinTextAsGame(printBattle.displayMessageBeforeBattle(myMonster.getName()));
                    prinTextAsGame(printBattle.displayPauseMessage());
                    combatCommand = receiveNum();
                    if (combatCommand == 1) {          //戦うの場合
                        int enemyDmgDealt = myMonster.wazaDmg(myMonster.getAtk(), myMonster.getWazaDmgRate(), enemy.getDef());
                        enemyMonsterHp = enemyMonsterHp - enemyDmgDealt;
                        prinTextAsGame(printBattle.displayWazaName(myMonster.getName(), myMonster.getWazaNm()));
                        prinTextAsGame(printBattle.displayDmgDealt(enemy.getName(), enemyDmgDealt));
                    } else if (combatCommand == 2) {   //逃げるの場合
                        escapeFlg = challengeEscape(myMonster.getSpd(), enemy.getSpd());
                        if (escapeFlg) {
                            battleResult = BattleResult.ESCAPE_SUCCESSFUL;
                            break;
                        } else {
                            prinTextAsGame(printBattle.displayWhenRunningAway());
                        }
                    }
                    //敵のHPが0か判定
                    if (enemyMonsterHp < 0) {
                        battleResult = BattleResult.WIN;
                        break;
                    }
                }
            }
        }

        prinTextAsGame("（バトル終了）");
        //バトル結果
        if (battleResult == BattleResult.ESCAPE_SUCCESSFUL) {
            prinTextAsGame(myMonster.getName() + "はうまく　にげ切れた!");
        } else if (battleResult == BattleResult.WIN) {
            prinTextAsGame(enemy.getTrainer() + "の " + enemy.getName() + " は たおれた！");
            prinTextAsGame(myMonster.getName() + "の勝利！");
        } else {
            prinTextAsGame(myMonster.getName() + "の敗北…");
            prinTextAsGame(myMonster.getTrainer() + "は めのまえが まっくらに なった！");
        }
    }


    public boolean decideWhoAttackFirstAndSecond(int myMonsterSpd, int enemySpd) {
        boolean firstAtkFlg;
        //先攻・後攻を決定する
        if (myMonsterSpd < enemySpd) {
            //相手のモンスターとスピードを比較し、相手が速ければ先攻フラグをfalseに変える
            return false;
        } else if (myMonsterSpd == enemySpd) {
            //スピードが全く同じであれば50%の確率で先攻フラグをfalseに変える
            return false;
        } else {
            return true;
        }
    }


    public int receiveNum() {
        Scanner sc = new Scanner(System.in);
        int tmp = 0;
        try {
            tmp = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage() + "入力する値が不正です");
            System.out.println("再入力してください");
        }
        return tmp;
    }

    //何かしらの入力があるまで待機する
    //※内部的に使うメソッドなのでprivate設定にする
    private String pushBtn() {
        BufferedReader br = null;
        String cmdin = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            cmdin = br.readLine();
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cmdin;
    }

    //コマンド「にげる」選択時の処理
    public boolean challengeEscape(int sppedFleeingMonster, int speedChasingMonster) {
        boolean escapeFlag;
        int success_rate = (int) ESCAPE_SUCCESS_ST_RATE * sppedFleeingMonster / speedChasingMonster;

        int rndmNum = 1 + (int) (Math.random() * 100.0);

        //(逃げる側のモンスターのスピードが速いほど、成功率が高くなる）
        if (rndmNum < success_rate) {
            escapeFlag = true;
        } else {
            escapeFlag = false;
        }
        return escapeFlag;
    }

    //50%の確率でtrueを返す（すばやさが同じだった場合にどちらを先攻とするか決める際に使用）
    public boolean judgeFiftyFifty() {
        boolean judge = true;

        //0か1をランダムで取得
        int rndmNum = (int) (Math.random() * 2.0);

        if (rndmNum == 0) {
            judge = false;
        }
        return judge;
    }

    //文字をゲームのように表示（50ミリ秒に1文字）
    private void prinTextAsGame(String txt) {
        //文字列を配列に1文字ずつセット
        char data[] = txt.toCharArray();
        //配列数を取得
        int arr_num = data.length;
        for (int i = 0; i < arr_num; i++) {

            try {
                //指定ミリ秒の間眠る
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print(data[i]);
        }
        System.out.print("\n");
    }
}
