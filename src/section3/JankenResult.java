package section3;

import java.util.Objects;

public class JankenResult {
    private String hand1;
    private String hand2;
    //誰が何の手を出したのかを返すメソッド
    private Player wonHandPlayer;
    private Player player1;
    private Player player2;
    private final Referee referee;

    //どっちのプレイヤーが勝ったのかを表示
    public JankenResult(String hand1, String hand2, Player wonHandPlayer, Player player1, Player player2, Referee referee) {
        this.hand1 = hand1;
        this.hand2 = hand2;
        this.wonHandPlayer = wonHandPlayer;
        this.player1 = player1;
        this.player2 = player2;
        this.referee = referee;
    }

    public String getHand1() {
        return hand1;
    }

    public String getHand2() {
        return hand2;
    }

    public Player getWonHandPlayer() {
        return wonHandPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String sayReferee(String text) {
        return "審判タロー「" + text + "」";
    }

    public String output() {
        String result = "";
        result += sayReferee("じゃんけん・・・ポン！！") + "\n";
        result += sayReferee(player1.getName() + "さんの手：" + getHand1()) + "\n";
        result += sayReferee(player2.getName() + "さんの手：" + getHand2()) + "\n";
        result += sayReferee("結果は・・・") + "\n";
        if (Objects.nonNull(getWonHandPlayer())) {
            result += sayReferee(getWonHandPlayer().getName() + "さんの勝利");
        } else {
            result += sayReferee("あいこ・・・勝負つかず");
        }
        return result;
    }

    @Override
    public String toString() {
        return "JankenResult [hand1=" + hand1 + ", hand2=" + hand2 + ", wonHandPlayer=" + wonHandPlayer + ", player1="
                + player1 + ", player2=" + player2 + "]";
    }


}

