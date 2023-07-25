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
    StandardOutputStream standard = new StandardOutputStream();//標準出力をカスタムのStandardOutputStreamにリダイレクト
//    private final ByteArrayOutputStream outPut = new ByteArrayOutputStream();//19行目とこの処理は何が違うのか？

    @BeforeEach
    void setUp() {//共通部分
        System.setOut(standard);//標準出力されるものを、standardに書き換える
        Janken.main("mikosawa", "moko", "審判チョコ");//実行する際の引数
    }

    @Test
    void 全ての標準出力で審判の名前が先頭に表示されること() {
        String line;
        while ((line = standard.readLine()) != null) {//lineがnullになるまでテストする
            String expected = "審判チョコ";
            assertTrue(line.startsWith(expected));
        }
    }

    @Test
    void 掛け声が表示されること() {
        String actual = standard.readLine();
        assertTrue(actual.endsWith("「ジャンケンぽん！」"));
    }

    @Test
    void コンソール上で受け取った一人目の名前が表示されること() {
        //オブジェクト生成
        standard.readLine();
        //期待値
        String expected = "mikosawa";
        //実測値
        var actual = standard.readLine();
        //比較
        assertTrue(actual.contains(expected));
    }

    @Test
    void 一人目のジャンケンの手が表示されること() {
        //オブジェクトの生成
        standard.readLine();
        //期待値
        String expected1 = "の手：グー」";
        String expected2 = "の手：チョキ」";
        String expected3 = "の手：パー」";
        //実測値
        String actual = standard.readLine();
        //比較
        assertTrue(actual.contains(expected1) || actual.contains(expected2) || actual.contains(expected3));
    }

    @Test
    void コンソール上で受け取った二人目の名前が表示されること() {
        //オブジェクト生成
        standard.readLine();
        standard.readLine();
        //期待値
        String expected = "moko";
        //実測値
        var result = standard.readLine();
        //比較
        assertTrue(result.contains(expected));
    }

    @Test
    void 二人目のジャンケンの手が表示される() {
        //オブジェクトの生成
        standard.readLine();
        standard.readLine();
        //期待値
        String expected1 = "の手：グー";
        String expected2 = "の手：チョキ";
        String expected3 = "の手：パー";
        //実測値
        String actual = standard.readLine();
        System.err.println(actual);
        //比較
        assertTrue(actual.contains(expected1) || actual.contains(expected2) || actual.contains(expected3));
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
        //オブジェクト生成
        standard.readLine();
        standard.readLine();
        standard.readLine();
        //期待値
        String expected = "「結果は？」";
        //実測値
        String actual = standard.readLine();
        assertTrue(actual.endsWith(expected));
    }

    @Test
    void 勝敗結果が正常に返ってくること() {
        //オブジェクト生成
        standard.readLine();
        standard.readLine();
        standard.readLine();
        standard.readLine();
        //期待値
        String expected1 = "「mikosawaさんの勝利！」";
        String expected2 = "「mokoさんの勝利！」";
        String expected3 = "「あいこ!勝負つかず!」";
        String expected4 = "「エラーが発生しました」";
        //実測値
        String actual = standard.readLine();
        //比較
        assertTrue(actual.endsWith(expected1) || actual.endsWith(expected2) || actual.endsWith(expected3)
                || actual.endsWith(expected4));
    }

    //	@ParameterizedTest
    //	@ValueSource(strings = {"mikosawaの手：グー", "mikosawaの手：チョキ", "mikosawaの手：パー"})
    //	void コンソール上で受け取った一人目の名前とジャンケンの手が表示される(String expected) {
    //		standard.readLine(); //sysoutのスキップ
    //		//実測値
    //		String actual = standard.readLine();
    //		Assertions.assertEquals(expected, actual);
    //	}

    //    @Test
//    void 標準出力2行目の始まりが審判チョコであること() {
//        standard.readLine();
//        String actual = standard.readLine();
//        String expected = "審判チョコ";
//        assertTrue(actual.startsWith(expected));
//    }


    private boolean nameEqualsTo(String output, String expected) {
        return output.startsWith(expected);
    }

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
