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

	public void decideHand() {
		hand = Hand.decide();
	}

	public Hand getHand() {
		return hand;
	}
}
