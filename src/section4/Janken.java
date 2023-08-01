package section4;

class Janken {

    public static void main(String... args) {
        var player1 = new Player(args[0]);
        var player2 = new Player(args[1]);
        var ref = new Ref(args[2]);
//        Record record = new Record();

        String preparationCallOutResult = ref.displayPreparationCallOut(player1,player2);
        System.out.print(preparationCallOutResult);
        ref.displayCallOut();
        player1.decideHand();
        player2.decideHand();
        ref.displayHand(player1);
        ref.displayHand(player2);

        Hand hand1 = player1.getHand();
        Hand hand2 = player2.getHand();
        var result = hand1.judgeAgainst(hand2);
        ref.displayResult(result, player1.getName(), player2.getName(), ref.getRefName());
        String recordResult = ref.displayWinLoseResult(player1.getName(), player2.getName(), ref.getRefName());
        System.out.println(recordResult);
    }
}
