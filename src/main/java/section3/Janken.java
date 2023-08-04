package section3;

class Janken {
	public static void main(String... args) {
		String name1 = args[0];
		String name2 = args[1];

		Player player1 = new Player(name1);
		Player player2 = new Player(name2);

		Game game = new Game(player1, player2);
		String result = game.playersReachOut();

		System.out.println("じゃんけん・・・ポン！！！");
		System.out.println(name1 + "さんの手：" + player1.play());
		System.out.println(name2 + "さんの手：" + player2.play());
		System.out.println("結果は・・・");
		System.out.println(result);
	}
}