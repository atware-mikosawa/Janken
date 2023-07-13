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
	void setUp() {
		System.setOut(standard);
	}

	@Test
	void 掛け声が表示されること() {
		Janken.main();
		assertEquals("abc", standard.readLine());
	}

	@Test
	void 一人目の名前が表示される() {
		Janken.main();
		//		assertEquals("グー", );
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