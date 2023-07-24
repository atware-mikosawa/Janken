package section3;


public class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    JankenResult playersReachOut() {
        String hand1 = player1.play();
        String hand2 = player2.play();
        String result = juge(hand1, hand2);
        Player wonPlayer = null;
        if (result.equals(hand1)) {//player1が勝っている
            wonPlayer = player1;
        } else if (result.equals(hand2)) {//player2が勝ったとき
            wonPlayer = player2;
        }
        return new JankenResult(hand1, hand2, wonPlayer, player1, player2);
    }

    String juge(String hand1, String hand2) {//勝った手が出ること
        if (hand1.equals("グー") && hand2.equals("チョキ") || hand1.equals("チョキ") && hand2.equals("グー")) {
            return "グー";
        } else if (hand1.equals("チョキ") && hand2.equals("パー") || hand1.equals("パー") && hand2.equals("チョキ")) {//チョキが勝つとき
            return "チョキ";
        } else if (hand1.equals("パー") && hand2.equals("グー") || hand1.equals("グー") && hand2.equals("パー")) {//チョキが勝つとき
            return "パー";
        }
        return "あいこ";
    }
}
