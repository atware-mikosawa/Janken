package section4;

class Janken {

    public static void main(String... args) {

        var player1 = new Player(args[0]);
        var player2 = new Player(args[1]);

        displayCallOut();

        player1.decideHand();
        player2.decideHand();

        displayHand(player1);
        displayHand(player2);

        var result = player1.getHand().judgeAgainst(player2.getHand());

        displayResult(result, player1.getName(), player2.getName());
    }

    private static void displayCallOut() {
        System.out.println("ジャンケンぽん！");
    }

    private static void displayHand(Player player) {
        System.out.printf("%sの手：%s%n", player.getName(), player.getHand().getJapaneseName());
    }

    private static void displayResult(Result result, String player1Name, String player2Name) {
        System.out.println("結果は？");
        if (result == Result.DRAW) {
            System.out.println("あいこ!勝負つかず!");
            return;
        }
        var nameOfTheWinner = result == Result.WIN ? player1Name : player2Name;
        System.out.printf("%sさんの勝利！%n", nameOfTheWinner);
    }
}
