package section4;

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
		Janken.main(new String[] { "mikosawa", "moko" });//実行する際の引数
	}

	@Test
	void 掛け声が表示されること() {
		assertEquals("ジャンケンぽん！", standard.readLine());
	}

	void コンソール上で受け取った一人目の名前とジャンケンの手が表示される() {
		standard.readLine(); //sysoutのスキップ
		assertEquals("mikosawaの手：グー", standard.readLine());
	}

	@Test
	void コンソール上で受け取った二人目の名前が表示される() {
		standard.readLine();
		standard.readLine();
		assertEquals("mokoの手：グー", standard.readLine());
	}

	@Test
	void 結果はが表示される() {
		standard.readLine();
		standard.readLine();
		standard.readLine();
		assertEquals("結果は？", standard.readLine());
	}

	@Test
	void あいこ勝負つかずが表示される() {
		standard.readLine();
		standard.readLine();
		standard.readLine();
		standard.readLine();
		assertEquals("あいこ!勝負つかず!", standard.readLine());
	}

	@Test
	void ジャンケンの手が正常に出力されるかの確認() {
		//オブジェクトの生成
		Janken janken = new Janken();
		janken.makeHandStatus();
		//期待値
		String expectedG = janken.getG();
		String expectedC = janken.getC();
		String expectedP = janken.getP();
		//実測値
		String actual = janken.getHandStatus();
		//比較
		assertTrue(expectedG.equals(actual) || expectedC.equals(actual) || expectedP.equals(actual));
	}

	public class StandardOutputStream extends PrintStream {
		private BufferedReader br = new BufferedReader(new StringReader(""));

		public StandardOutputStream() {
			super(new ByteArrayOutputStream());
		}

		/**
		 * 1行分の文字列を読み込む
		 * @return 改行を含まない文字。終端の場合はnull
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