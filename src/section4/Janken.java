package section4;

class Janken {
	private static final String ROCK = "グー";
	private static final String SCISSORS = "チョキ";
	private static final String PAPER = "パー";
	String handStatus;
	private String name1;
	private String name2;

	public static void main(String... args) {
		Janken janken = new Janken();
		janken.name1 = args[0];
		janken.name2 = args[1];

		System.out.println("ジャンケンぽん！");
		janken.makeHandStatus();
		String hand1 = janken.getHandStatus();
		janken.makeHandStatus();
		String hand2 = janken.getHandStatus();

		System.out.println(janken.name1 + "の手：" + hand1);
		System.out.println(janken.name2 + "の手：" + hand2);
		janken.result(hand1, hand2);
	}

	//ランダムな1~3の数値を出す
	public void makeHandStatus() {
		double round = Math.random() * 2;
		int randomInt = (int) Math.round(round);
		if (randomInt == 0) {
			this.handStatus = ROCK;
		} else if (randomInt == 1) {
			this.handStatus = SCISSORS;
		} else if (randomInt == 2) {
			this.handStatus = PAPER;
		}
	}

	//勝敗の結果を返すメソッド
	public void result(String hand1, String hand2) {
		System.out.println("結果は？");
		if (//name1が勝つ時
		(hand1.equals(ROCK)) && (hand2.equals(SCISSORS))
				|| (hand1.equals(SCISSORS)) && (hand2.equals(PAPER))
				|| (hand1.equals(PAPER)) && (hand2.equals(ROCK))) {
			System.out.println(name1 + "さんの勝利！");
		} else if (//name2が勝つ時
		(hand2.equals(ROCK)) && (hand1.equals(SCISSORS))
				|| (hand2.equals(SCISSORS)) && (hand1.equals(PAPER))
				|| (hand2.equals(PAPER)) && (hand1.equals(ROCK))) {
			System.out.println(name2 + "さんの勝！");
		} else if (hand1.equals(hand2)) {//あいこの時
			System.out.println("あいこ!勝負つかず!");
		} else {
			System.out.println("エラーが発生しました");
		}
	}

	//ゲッター
	public String getHandStatus() {
		return handStatus;
	}

	public String getG() {
		return ROCK;
	}

	public String getC() {
		return SCISSORS;
	}

	public String getP() {
		return PAPER;
	}

}
