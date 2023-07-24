package section3;



import java.util.Objects;

class Janken {
	public static void main(String... args) {
		String name1 = args[0];
		String name2 = args[1];

		Player player1 = new Player(name1);
		Player player2 = new Player(name2);

		Game game = new Game(player1, player2);
		JankenResult result = game.playersReachOut();

		System.out.println("じゃんけん・・・ポン！！！");
		System.out.println(name1 + "さんの手：" + result.getHand1());
		System.out.println(name2 + "さんの手：" + result.getHand2());
		System.out.println("結果は・・・");
		if (Objects.nonNull(result.getWonHandPlayer())) {
			System.out.println(result.getWonHandPlayer().getName() + "さんの勝利");
		} else {
			System.out.println("あいこ・・・勝負つかず");
		}
		//		System.out.println(result.toString());
	}
}