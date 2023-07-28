package section2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterBattleTest {
  StandardOutputStream standard = new MonsterBattleTest.StandardOutputStream();

    @BeforeEach
    void setUp() {//共通部分
        System.setOut(standard);//標準出力されるものを、standardに書き換える
    }
    @Test
    void コンソールの1行目にHelloWorldが表示されること() {
        //オブジェクト生成
        MonsterBattle.main();
        //期待値
        String expected = "HelloWorld";
        //実測値
        String actual = standard.readLine();
        // 比較
        assertEquals(expected, actual);
    }

    public class StandardOutputStream extends PrintStream {
        private BufferedReader br = new BufferedReader(new StringReader(""));//StringReaderで文字列に変換している？

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