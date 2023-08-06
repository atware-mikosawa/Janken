package section2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBattleWild {

    //ここから
    @Test
    void testGetCmd() {
        //オブジェクト生成
        BattleWild battleWild = new BattleWild();
        //期待値
        String expected = """
                フシギダネは　どうする？
                1:たたかう 2:にげる
                ▼
                
                """;
        //実測値
        String actual = battleWild.getCmd("フシギダネ");
        //比較
        assertEquals(expected, actual);
    }
}
