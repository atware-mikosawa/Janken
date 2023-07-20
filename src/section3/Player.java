package section3;

class Player {
	public String name;

	public Player(String name) {//コンストラクタの処理は独自の名前を持つことを可能にしている
		this.name = name;//インスタンスを作成するたびに、コンストラクタは呼び出されるため、１行のみの記述でも良い
	}

	public String play() {//②文字列で返すmakeAnyとpalayByAnyを実行するだけのメソッド
		double randomNum = makeAny();
		return playByAny(randomNum);
	}

	public double makeAny() {//ランダムな数値を返す
		double random = Math.floor(((Math.random() * 10) + 1) % 3);
		return random;
	}

	public String playByAny(double value) {//①ランダムなグーチョキかパーを返す。makeAny()で数字を受け取って文字に変換	
		if (value == 0.0) {
			return "グー";
		} else if (value == 1.0) {
			return "チョキ";
		} else {
			return "パー";
		}
	}
}
