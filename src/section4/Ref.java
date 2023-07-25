package section4;

public class Ref {
    private final String refName;

    Ref(String refName) {
        this.refName = refName;
    }

    public String getRefName() {
        return refName;
    }

    //元々14,18行目のメソッドはprivate、なぜ？,publicにしても良いの？、なぜstaticだったの？
    public void displayCallOut() {
        System.out.println(this.refName + "「ジャンケンぽん！」");
    }

    public void displayHand(Player player) {
        System.out.printf(this.refName + "「%sの手：%s%n", player.getName(), player.getHand().getJapaneseName() + "」");
    }


    public void displayResult(Result result, String player1Name, String player2Name, String ref) {
        System.out.println(ref + "「結果は？」");
        if (result == Result.DRAW) {
            System.out.println(ref + "「あいこ!勝負つかず!」");
            return;
        }
        var nameOfTheWinner = result == Result.WIN ? player1Name : player2Name;
        System.out.printf(ref + "「%sさんの勝利！」%n", nameOfTheWinner);
    }
}
