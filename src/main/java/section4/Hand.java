package section4;

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

    //    public static Hand decide() {
    public static int decide() {
        double round = Math.random() * 2;
        int randomInt = (int) Math.round(round);
        if (randomInt == 0) {
//            return ROCK;
            return 0;
        } else if (randomInt == 1) {
//            return SCISSORS;
            return 1;
        } else if (randomInt == 2) {
//            return PAPER;
            return 2;
        }
        throw new IllegalStateException("unexpected random value: " + randomInt);
    }
}
