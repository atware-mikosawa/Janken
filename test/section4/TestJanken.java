package section4;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJanken {
    StandardOutputStream standard = new StandardOutputStream();

    @BeforeEach
    void setUp() {//共通部分
        System.setOut(standard);//標準出力されるものを、standardに書き換える
        Janken.main("mikosawa", "moko", "審判チョコ");//実行する際の引数
    }

	@Test
	void 審判の名前が表示されること() {
		String actual = standard.readLine();
		System.err.println(actual);
		assertTrue(actual.startsWith("審判チョコ"));
	}

    @Test
    void 掛け声が表示されること() {
		String actual = standard.readLine();
		assertTrue(actual.endsWith("「ジャンケンぽん！」"));
    }

    //	@ParameterizedTest
    //	@ValueSource(strings = {"mikosawaの手：グー", "mikosawaの手：チョキ", "mikosawaの手：パー"})
    //	void コンソール上で受け取った一人目の名前とジャンケンの手が表示される(String expected) {
    //		standard.readLine(); //sysoutのスキップ
    //		//実測値
    //		String actual = standard.readLine();
    //		Assertions.assertEquals(expected, actual);
    //	}
    @Test
    void コンソール上で受け取った一人目の名前とジャンケンの手が表示される() {
        //オブジェクトの生成
        standard.readLine();
        //期待値
        String expected1 = "mikosawaの手：グー";
        String expected2 = "mikosawaの手：チョキ";
        String expected3 = "mikosawaの手：パー";
        //実測値
        String actual = standard.readLine();
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual));
    }
	@Test
	void 標準出力2行目の始まりが審判チョコであること(){
		standard.readLine();
		String actual = standard.readLine();
		String expected = "審判チョコ";
		assertTrue(actual.startsWith(expected));
	}

    @Test
    void コンソール上で受け取った二人目の名前とジャンケンの手が表示される() {
        //オブジェクトの生成
        standard.readLine();
        standard.readLine();
        //期待値
        String expected1 = "mokoの手：グー";
        String expected2 = "mokoの手：チョキ";
        String expected3 = "mokoの手：パー";
        //実測値
        String actual = standard.readLine();
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual));
    }

    @Test
    void ジャンケンの手がそれぞれおおよそ3分の1の確率で選択されること() {

        var counts = IntStream.range(0, 100)
                .mapToObj(ignoreUnused -> Hand.decide())
                .collect(groupingBy(identity(), counting()));

        assertTrue(isWithinRange(counts.get(Hand.ROCK), 20, 40));
        assertTrue(isWithinRange(counts.get(Hand.SCISSORS), 20, 40));
        assertTrue(isWithinRange(counts.get(Hand.PAPER), 20, 40));
    }

    private static boolean isWithinRange(Long count, int lowerLimit, int upperLimit) {
        return lowerLimit < count && count < upperLimit;
    }

    @Test
    void 結果はが表示される() {
        standard.readLine();
        standard.readLine();
        standard.readLine();
        assertEquals("結果は？", standard.readLine());
    }

    @Test
    void 勝敗結果が正常に返ってくること() {
        //オブジェクト生成
        Janken janken = new Janken();
        standard.readLine();
        standard.readLine();
        standard.readLine();
        standard.readLine();
        //期待値
        String expected1 = "mikosawaさんの勝利！";
        String expected2 = "mokoさんの勝利！";
        String expected3 = "あいこ!勝負つかず!";
        String expected4 = "エラーが発生しました";
        //実測値
        String actual = standard.readLine();
        //比較
        assertTrue(expected1.equals(actual) || expected2.equals(actual) || expected3.equals(actual)
                || expected4.equals(actual));
    }

    @Test
    void コンソール上で受け取った二人目の名前が表示される() {
        standard.readLine();
        standard.readLine();
        //			assertEquals("mokoの手：グー", standard.readLine());
        var result = standard.readLine();
        assertTrue(nameEqualsTo(result, "moko"));
    }

    private boolean nameEqualsTo(String output, String expected) {
        return output.startsWith(expected);
    }

    //	@Test
    //	void あいこ勝負つかずが表示される() {
    //		standard.readLine();
    //		standard.readLine();
    //		standard.readLine();
    //		standard.readLine();
    //		assertEquals("あいこ!勝負つかず!", standard.readLine());
    //	}

    public class StandardOutputStream extends PrintStream {
        private BufferedReader br = new BufferedReader(new StringReader(""));

        public StandardOutputStream() {
            super(new ByteArrayOutputStream());
        }

        /**
         * 1行分の文字列を読み込む
         *
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
