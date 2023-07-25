package section4;

class Janken {

	public static void main(String... args) {

		var player1 = new Player(args[0]);
		var player2 = new Player(args[1]);
		var ref = new Player(args[2]);

		displayCallOut(ref);

		player1.decideHand();
		player2.decideHand();

		displayHand(ref, player1);
		displayHand(ref, player2);

		var result = player1.getHand().judgeAgainst(player2.getHand());

		displayResult(result, player1.getName(), player2.getName(), ref.getName());
	}

	private static void displayCallOut(Player ref) {
		System.out.println( ref.getName() + "「ジャンケンぽん！」");
	}

	private static void displayHand(Player ref, Player player) {
		System.out.printf(ref.getName() +  "「%sの手：%s%n", player.getName(), player.getHand().getJapaneseName() + "」");
	}

	private static void displayResult(Result result, String player1Name, String player2Name, String ref) {
		System.out.println(ref + "「結果は？」");
		if (result == Result.DRAW) {
			System.out.println(ref + "「あいこ!勝負つかず!」");
			return;
		}
		var nameOfTheWinner = result == Result.WIN ? player1Name : player2Name;
		System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
	}
}
