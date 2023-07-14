package section4;

class Janken {
	private static final String G = "グー";
	private static final String C = "チョキ";
	private static final String P = "パー";
	String handStatus;

	public static void main(String... args) {
		String name1 = args[0];
		String name2 = args[1];

		System.out.println("ジャンケンぽん！");
		System.out.println(name1 + "の手：" + G);
		System.out.println(name2 + "の手：" + G);
		System.out.println("結果は？");
		System.out.println("あいこ!勝負つかず!");
	}

	//ランダムな1~3の数値を出す
	public void makeHandStatus() {
		double round = Math.random() * 2;
		int rondomInt = (int) Math.round(round);
		if (rondomInt == 0) {
			this.handStatus = G;
		} else if (rondomInt == 1) {
			this.handStatus = C;
		} else if (rondomInt == 2) {
			this.handStatus = P;
		}
	}

	//ゲッター
	public String getHandStatus() {
		return handStatus;
	}

	public String getG() {
		return G;
	}

	public String getC() {
		return C;
	}

	public String getP() {
		return P;
	}

}