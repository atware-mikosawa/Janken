package section4;

class Janken {

    private static final String ROCK = "グー";
    private static final String SCISSORS = "チョキ";
    private static final String PAPER = "パー";

    enum Hand {
        ROCK("グー") {
            @Override
            Result judgeAgainst(Hand other) {
                return switch (other) {
                    case ROCK -> Result.DRAW;
                    case SCISSORS -> Result.WIN;
                    case PAPER -> Result.LOSE;
                };
            }
        },
        SCISSORS("チョキ") {
            @Override
            Result judgeAgainst(Hand other) {
                return switch (other) {
                    case ROCK -> Result.LOSE;
                    case SCISSORS -> Result.DRAW;
                    case PAPER -> Result.WIN;
                };
            }
        },
        PAPER("パー") {
            @Override
            Result judgeAgainst(Hand other) {
                return switch (other) {
                    case ROCK -> Result.WIN;
                    case SCISSORS -> Result.LOSE;
                    case PAPER -> Result.DRAW;
                };
            }
        };

        private final String japaneseName;

        Hand(String japaneseName) {
            this.japaneseName = japaneseName;
        }

        abstract Result judgeAgainst(Hand other);

        public String getJapaneseName() {
            return japaneseName;
        }

        public static Hand decide() {
            double round = Math.random() * 2;
            int randomInt = (int) Math.round(round);
            if (randomInt == 0) {
                return ROCK;
            } else if (randomInt == 1) {
                return SCISSORS;
            } else if (randomInt == 2) {
                return PAPER;
            }
            throw new IllegalStateException("unexpected random value: " + randomInt);
        }
    }

    enum Result {
        WIN,
        LOSE,
        DRAW
    }

    private static class Player {
        private final String name;
        private Hand hand = null;

        Player(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void decideHand() {
            hand = Hand.decide();
        }

        public Hand getHand() {
            return hand;
        }
    }

    String handStatus;

    public static void main(String... args) {

        var player1 = new Player(args[0]);
        var player2 = new Player(args[1]);

        System.out.println("ジャンケンぽん！");

        player1.decideHand();
        player2.decideHand();

        displayHand(player1);
        displayHand(player2);

        var result = player1.getHand().judgeAgainst(player2.getHand());

        displayResult(result, player1.getName(), player2.getName());
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

    private static void displayHand(Player player) {
        System.out.printf("%sの手：%s%n", player.getName(), player.getHand().getJapaneseName());
    }

    //ランダムな1~3の数値を出す
    public void makeHandStatus() {
        double round = Math.random() * 2;
        int randomInt = (int) Math.round(round);
        if (randomInt == 0) {
            this.handStatus = ROCK;
        } else if (randomInt == 1) {
            this.handStatus = SCISSORS;
        } else if (randomInt == 2) {
            this.handStatus = PAPER;
        }
    }

    //ゲッター
    public String getHandStatus() {
        return handStatus;
    }

    public String getG() {
        return ROCK;
    }

    public String getC() {
        return SCISSORS;
    }

    public String getP() {
        return PAPER;
    }

}
