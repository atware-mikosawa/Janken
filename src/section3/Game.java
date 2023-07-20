package section3;

public class Game {
	private Player player1;
	private Player player2;

	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	String playersReachOut() {
		String hand1 = player1.play();
		String hand2 = player2.play();
		String result = juge(hand1, hand2);
		if (result.equals(hand1)) {//player1が勝っている
			return player1.name + "さんの勝利！";
		} else if (result.equals(hand2)) {//player2が勝ったとき
			return player2.name + "さんの勝利！";
		} else {//あいこの時
			return "あいこ勝負つかず！";
		}
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
