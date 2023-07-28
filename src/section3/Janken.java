package section3;


import java.util.Objects;

class Janken {
    public static void main(String... args) {
        String name1 = args[0];
        String name2 = args[1];
        String name3 = args[2];

        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Referee referee = new Referee(name3);

        Game game = new Game(player1, player2, referee);
        JankenResult result = game.playersReachOut();

        System.out.println(result.output());
    }
}