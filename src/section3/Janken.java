package section3;

class Janken {
	public static void main(String... args) {
		String name1 = args[0];
		String name2 = args[1];

		Player player1 = new Player(name1);
		Player player2 = new Player(name2);

		//		<<<<<<< mikosawa
		//        displayHand(player1);
		//        displayHand(player2);
		//=======
		//	//勝敗の結果を返すメソッド
		//	public void result(String hand1, String hand2) {
		//		System.out.println("結果は？");
		//		if (//name1が勝つ時
		//		(hand1.equals("グー")) && (hand2.equals("チョキ"))
		//				|| (hand1.equals("チョキ")) && (hand2.equals("パー"))
		//				|| (hand1.equals("パー")) && (hand2.equals("グー"))) {
		//			System.out.println(name1 + "さんの勝利！");
		//		} else if (//name2が勝つ時
		//		(hand2.equals("グー")) && (hand1.equals("チョキ"))
		//				|| (hand2.equals("チョキ")) && (hand1.equals("パー"))
		//				|| (hand2.equals("パー")) && (hand1.equals("グー"))) {
		//			System.out.println(name2 + "さんの勝利！");
		//		} else if (hand1.equals(hand2)) {//あいこの時
		//			System.out.println("あいこ!勝負つかず!");
		//		} else {
		//			System.out.println("エラーが発生しました");
		//		}
		//	}
		//>>>>>>> mikosawa

		System.out.println("じゃんけん・・・ポン！！！");

		Game game = new Game(player1, player2);
		System.out.println(name1 + "さんの手：" + player1.play());
		System.out.println(name2 + "さんの手：" + player2.play());
		//		String result = game.playersReachOut();
		//		System.out.println(result);
	}
}