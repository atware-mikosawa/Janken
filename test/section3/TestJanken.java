package section3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJanken {
	StandardOutputStream standard = new StandardOutputStream();

	@BeforeEach
	void setUp() {//共通部分
		System.setOut(standard);//標準出力されるものを、standardに書き換える
	}

	//標準出力
	@Test
	void 標準出力1行目でコンソール上でジャンケンぽんが出力されること() {
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		assertEquals("じゃんけん・・・ポン！！！", standard.readLine());
	}

	@Test
	void 標準出力2行目でコンソール上で受け取った一人目の名前が表示されること() {
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		standard.readLine();
		var result = standard.readLine();
		assertTrue(nameEqualsTo(result, "mikosawa"));
	}

	@Test
	void 標準出力2行目でコンソール上で受け取った一人目の手が表示されること() {
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		standard.readLine();
		//期待値
		String endWord1 = "グー";
		String endWord2 = "チョキ";
		String endWord3 = "パー";
		//実測値
		var actual = standard.readLine();
		assertTrue(actual.endsWith(endWord1) || actual.endsWith(endWord2) || actual.endsWith(endWord3));
	}

	@Test
	void 標準出力3行目コンソール上で受け取った二人目の名前が表示されること() {
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		standard.readLine();
		standard.readLine();
		var result = standard.readLine();
		assertTrue(nameEqualsTo(result, "moko"));
	}

	@Test
	void 標準出力3行目コンソール上で受け取った二人目の手が表示されること() {
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		standard.readLine();
		standard.readLine();
		//期待値
		String endWord1 = "グー";
		String endWord2 = "チョキ";
		String endWord3 = "パー";
		//実測値
		var actual = standard.readLine();
		assertTrue(actual.endsWith(endWord1) || actual.endsWith(endWord2) || actual.endsWith(endWord3));
	}

	@Test
	void 標準出力4行目で結果はが表示されること() {
		//オブジェクトの生成
		Janken.main(new String[] { "mikosawa", "moko" });
		standard.readLine();
		standard.readLine();
		standard.readLine();
		//期待値
		String expected = "結果は・・・";
		//実測値
		String actual = standard.readLine();
		//比較
		assertEquals(expected, actual);
	}

	@Test
	void 標準出力5行目で勝敗の判定が正常に帰ってくること() {
		//オブジェクト生成
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
		Game game = new Game(new Player("mikosawa"), new Player("moko"));
		JankenResult result = game.playersReachOut();
		standard.readLine();
		standard.readLine();
		standard.readLine();
		standard.readLine();

		//期待値
		String endWord = "さんの勝利";
		String expected2 = "あいこ・・・勝負つかず";
		//実測値
		String actual = standard.readLine();
		//比較
		assertTrue(actual.endsWith(endWord) || expected2.equals(actual));
	}

	private boolean nameEqualsTo(String output, String expected) {
		return output.startsWith(expected);
	}
	//標準出力

	@Test
	void プレイヤーから名前が取れる事() {
		Player player = new Player("mikosawa");
		assertEquals("mikosawa", player.name);
	}

	@Test
	void プレイヤーが任意のグーかチョキかパーが帰ってくること() {
		Player player = new Player("mikosawa");
		String result = player.play();
		assertTrue("グー".equals(result) || ("チョキ").equals(result) || ("パー").equals(result));
	}

	//ジャンケンの判定
	@Test
	void ジャンケンの判定ができるパーとグーではパーが勝つ() {
		Game game = new Game(new Player("mikosawa"), new Player("mi"));
		String result = game.juge("パー", "グー");
		assertEquals("パー", result);
	}

	@Test
	void ジャンケンの判定ができるグーとチョキでグーが勝つ() {
		Game game = new Game(new Player("mikosawa"), new Player("mi"));
		String result = game.juge("グー", "チョキ");
		assertEquals("グー", result);
	}

	@Test
	void ジャンケンの判定ができるチョキとパーではチョキが勝つ() {
		Game game = new Game(new Player("mikosawa"), new Player("mi"));
		String result = game.juge("チョキ", "パー");
		assertEquals("チョキ", result);
	}

	@Test
	void ジャンケンの判定がアイコになること() {
		Game game = new Game(new Player("mikosawa"), new Player("mi"));
		String result = game.juge("チョキ", "チョキ");
		assertEquals("あいこ", result);
		result = game.juge("グー", "グー");
		assertEquals("あいこ", result);
		result = game.juge("パー", "パー");
		assertEquals("あいこ", result);
	}

	@Test
	void ランダムな数字0から2が出力されるか() {
		Player player = new Player("mikosawa");

		double result = player.makeAny();
		assertTrue(0 == result || 1 == result || 2 == result);
	}

	public class StandardOutputStream extends PrintStream {
		private BufferedReader br = new BufferedReader(new StringReader(""));

		public StandardOutputStream() {
			super(new ByteArrayOutputStream());
		}

		/**
		 * 1行分の文字列を読み込む
		 * @return 改行を含まない文字 終端の場合はnull
		 */
		public String readLine() {
			String line = "";
			try {
				if ((line = br.readLine()) != null)
					return line;
				br = new BufferedReader(new StringReader(out.toString()));
				((ByteArrayOutputStream) out).reset();
				return br.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}