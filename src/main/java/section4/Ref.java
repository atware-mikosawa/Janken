package section4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ref {

    private final String refName;
    private int winRecordPlayer1;
    private int winRecordPlayer2;
    private int loseRecordPlayer1;
    private int loseRecordPlayer2;


    Ref(String refName) {
        this.refName = refName;
    }

    public String getRefName() {
        return refName;
    }

    public int getWinRecordPlayer1() {
        return winRecordPlayer1;
    }

    public int getWinRecordPlayer2() {
        return winRecordPlayer2;
    }

    public int getLoseRecordPlayer1() {
        return loseRecordPlayer1;
    }

    public int getLoseRecordPlayer2() {
        return loseRecordPlayer2;
    }


    public String displayPreparationCallOut(Player player1, Player player2) {
        String result = "";
        loadRecord();
        result += this.refName + "「現在の勝敗」\n";
        result += this.refName + "「" + player1.getName() + "：" + getWinRecordPlayer1() + "勝" + getLoseRecordPlayer1() + "敗" + "」\n";
        result += this.refName + "「" + player2.getName() + "：" + getWinRecordPlayer2() + "勝" + getLoseRecordPlayer2() + "敗" + "」\n";
        return result;
    }

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
        } else if (result == Result.WIN) {
            nameOfTheWinner = player1Name;
            winRecordPlayer1++;
            loseRecordPlayer2++;
            saveRecords(winRecordPlayer1, loseRecordPlayer1, winRecordPlayer2, loseRecordPlayer2);
            System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
        } else {
            nameOfTheWinner = player2Name;
            loseRecordPlayer1++;
            winRecordPlayer2++;
            saveRecords(winRecordPlayer1, loseRecordPlayer1, winRecordPlayer2, loseRecordPlayer2);
            System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
        }
    }

    public String displayWinLoseResult(String player1Name, String player2Name, String refName) {
        String result = "";
        result += refName + "「" + player1Name + "：" + getWinRecordPlayer1() + "勝" + getLoseRecordPlayer1() + "敗" + "」\n";
        result += refName + "「" + player2Name + "：" + getWinRecordPlayer2() + "勝" + getLoseRecordPlayer2() + "敗" + "」\n";
        return result;
    }

    public void saveRecords(int winRecordPlayer1, int loseRecordPlayer1, int winRecordPlayer2, int loseRecordPlayer2) {
        String data = winRecordPlayer1 + "," + loseRecordPlayer1 + "," + winRecordPlayer2 + "," + loseRecordPlayer2;
        try {
            Files.write(Paths.get("jankenRecords.txt"), data.getBytes());
        } catch (IOException e) {
            System.out.println("エラーが発生しました：" + e.getMessage());
        }
    }

    public void loadRecord() {
        try {
            String data = new String(Files.readAllBytes(Paths.get("jankenRecords.txt")));
            String[] parts = data.split(",");
            winRecordPlayer1 = Integer.parseInt(parts[0]);
            loseRecordPlayer1 = Integer.parseInt(parts[1]);
            winRecordPlayer2 = Integer.parseInt(parts[2]);
            loseRecordPlayer2 = Integer.parseInt(parts[3]);
        } catch (IOException e) {
            System.out.println("エラーが発生しました：" + e.getMessage());
        }
    }

}
