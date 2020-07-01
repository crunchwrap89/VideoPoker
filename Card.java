
public class Card {
	int value;
	Suit suit;

	public Card(int value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public Suit getSuit() {
		return suit;
	}
	
	@Override
	public String toString() {
		
		String prettyValue;
		
		switch(value) {
		case 1:
			prettyValue = "A";
			break;
		case 11:
			prettyValue = "J";
			break;
		case 12:
			prettyValue = "Q";
			break;
		case 13:
			prettyValue = "K";
			break;
		default:
			prettyValue = "" + value;
		}
		
		switch(suit) {
		case Hearts:
			return prettyValue + "♥";
		case Spades:
			return prettyValue + "♠";
		case Diamonds:
			return prettyValue + "♦";
		case Clubs:
			return prettyValue + "♣";
		default:
			throw new RuntimeException("Vad är " + suit + "?");
		}
	}

//	@Override
//	public boolean equals(Object object) {
//		Card objCard = (Card) object;
//		if (this.value == objCard.value && this.suit == objCard.suit) {
//			return true;
//		} else {
//			return false;
//		}
//
//	}
//	public String toString() {
//		return " " + value + " " + suit.Type;
//	}
}
