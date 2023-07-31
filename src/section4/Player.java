package section4;

class Player {

    private final String name;
    private Hand hand = null;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //	public void decideHand() {
//hand = Hand.decide();
//	}
    public void decideHand() {
//		hand = Hand.decide();
        int decideHandNum = Hand.decide();
        if (decideHandNum == 0) {
            hand = Hand.ROCK;
        } else if (decideHandNum == 1) {
            hand = Hand.SCISSORS;
        } else if (decideHandNum == 2) {
            hand = Hand.PAPER;
        }
    }

    public Hand getHand() {
        return hand;
    }
}
