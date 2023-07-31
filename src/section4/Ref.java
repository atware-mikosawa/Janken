package section4;

public class Ref {

    private final String refName;
    private static int winRecordPlayer1 = 0;
    private static int winRecordPlayer2 = 0;
    private static int loseRecordPlayer1 = 0;
    private static int loseRecordPlayer2 = 0;


    Ref(String refName) {
        this.refName = refName;
    }

    public String getRefName() {
        return refName;
    }

    public static int getWinRecordPlayer1() {
        return winRecordPlayer1;
    }

    public static int getWinRecordPlayer2() {
        return winRecordPlayer2;
    }

    public static int getLoseRecordPlayer1() {
        return loseRecordPlayer1;
    }

    public static int getLoseRecordPlayer2() {
        return loseRecordPlayer2;
    }

    //元々14,18行目のメソッドはprivateだったのはなぜ？,publicにしても良いの？、なぜstaticだったの？
    public void displayCallOut() {
        System.out.println(this.refName + "「ジャンケンぽん！」");
    }

    public void displayHand(Player player) {
        System.out.printf(this.refName + "「%sの手：%s%n", player.getName(), player.getHand().getJapaneseName() + "」");
    }


    public void displayResult(Result result, String player1Name, String player2Name, String ref) {
        String nameOfTheWinner;
        System.out.println(ref + "「結果は？」");
        if (result == Result.DRAW) {
            System.out.println(ref + "「あいこ!勝負つかず!」");
            return;
        } else if (result == Result.WIN) {
            nameOfTheWinner = player1Name;
            winRecordPlayer1++;
            loseRecordPlayer2++;
            System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
        } else {
            nameOfTheWinner = player2Name;
            loseRecordPlayer1++;
            winRecordPlayer2++;
            System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
        }
    }

    public String displayWinLoseResult(String player1Name, String player2Name, String refName) {
        String result = "";
        result += refName + "「" + player1Name + "：" + getWinRecordPlayer1() + "勝" + getLoseRecordPlayer1() + "敗" + "」\n";
        result += refName + "「" + player2Name + "：" + getWinRecordPlayer2() + "勝" + getLoseRecordPlayer2() + "敗" + "」\n";
        return result;
    }

//    public void winLoseCount(Result result) {
//        if (result == Result.DRAW) {//あいこの時
////何もしない
//        } else if (result == Result.WIN) {//WINを受け取った時
//            winRecordPlayer1++;
//            loseRecordPlayer2++;
//        } else {//LOSEを受け取った時
//            loseRecordPlayer1++;
//            winRecordPlayer2++;
//        }
//    }
}
