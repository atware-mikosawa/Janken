package section3;

public class Game {

	String juge(String hand1, String hand2) {//グーが勝つ
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
