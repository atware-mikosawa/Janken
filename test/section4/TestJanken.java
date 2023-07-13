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

	@Test
	void コンソール上で受け取った一人目の名前が表示される() {
		standard.readLine(); //sysoutのスキップ
		assertEquals("mikosawaの手：", standard.readLine());
	}

	@Test
	void コンソール上で受け取った二人目の名前が表示される() {
		assertEquals("mokoの手：", standard.readLine(2));
	}

	@Test
	void 結果はが表示される() {
		assertEquals("結果は？", standard.readLine(3));
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
		 /**
	     * 指定した行数分の文字列を読み飛ばし、次の行を読み込む
	     * @param linesToSkip 読み飛ばす行数
	     * @return 読み飛ばした後の最初の行。終端の場合はnull
	     */
	    public String readLine(int linesToSkip) {
	        try {
	            for (int i = 0; i < linesToSkip; i++) {
	                br.readLine();
	            }
	            return readLine();  // 既存の readLine を使用
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }
	}

}