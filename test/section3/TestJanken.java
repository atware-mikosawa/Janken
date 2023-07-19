package section3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestJanken {
	//	StandardOutputStream standard = new StandardOutputStream();

	//	@BeforeEach
	//	void setUp() {
	//		System.setOut(standard);
	//	}
	//
	//	@Test
	//	void 掛け声が表示されること() {
	//		Janken.main();
	//		assertEquals("abc", standard.readLine());
	//	}

	//	@Test
	//	void 一人目の名前が表示される() {
	//		Janken.main();
	//		//		assertEquals("グー", );
	//	}

	@Test
	void プレイヤーからグーチョキパーの値が出力されること() {
		Player player = new Player("一人目");
		String result = player.play();
		assertTrue("グー".equals(result) || ("チョキ").equals(result) || ("パー").equals(result));
	}

	@Test
	void プレイヤーから名前が取れる事() {
		Player player = new Player("mikosawa");
		assertEquals("mikosawa", player.name);
	}

	@Test
	void プレイヤーが任意のグーかチョキかパーが出力されること() {
		Player player = new Player("mikosawa");
		String result = player.playByAny();
		assertTrue("グー".equals(result) || ("チョキ").equals(result) || ("パー").equals(result));
	}

	@Test
	void ジャンケンの判定ができるパーとグーではパーが勝つ() {
		Game game = new Game();
		String result = game.juge("パー", "グー");
		assertEquals("パー", result);
	}

	@Test
	void ジャンケンの判定ができるグーとチョキでグーが勝つ() {
		Game game = new Game();
		String result = game.juge("グー", "チョキ");
		assertEquals("グー", result);
	}

	@Test
	void ジャンケンの判定ができるチョキとパーではチョキが勝つ() {
		Game game = new Game();
		String result = game.juge("チョキ", "パー");
		assertEquals("チョキ", result);
	}

	@Test
	void ジャンケンの判定がアイコになること() {
		Game game = new Game();
		String result = game.juge("チョキ", "チョキ");
		assertEquals("あいこ", result);
		result = game.juge("グー", "グー");
		assertEquals("あいこ", result);
		result = game.juge("パー", "パー");
		assertEquals("あいこ", result);
	}

	//	public class StandardOutputStream extends PrintStream {
	//		private BufferedReader br = new BufferedReader(new StringReader(""));
	//
	//		public StandardOutputStream() {
	//			super(new ByteArrayOutputStream());
	//		}}
	//
	//		/**
	//		 * 1行分の文字列を読み込む
	//		 * @return 改行を含まない文字。終端の場合はnull
	//		 */
	//		public String readLine() {
	//			String line = "";
	//			try {
	//				if ((line = br.readLine()) != null)
	//					return line;
	//				br = new BufferedReader(new StringReader(out.toString()));
	//				((ByteArrayOutputStream) out).reset();
	//				return br.readLine();
	//			} catch (IOException e) {
	//				throw new RuntimeException(e);
	//			}
	//		}
	//	}

}