
import java.util.ArrayList;
import java.util.Scanner;

public class VideoPoker {
	Scanner input = new Scanner(System.in);
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	Deck deck = new Deck();
	
	private Game_Status status;
	private int credit,bet;
	private Score s = new Score();
	
	
	
	public VideoPoker() {
		startGame();
		//credit = 100;
		//bet = 100; //underlättar ev senare implementation av att satsa valfritt belopp
	}
	
	public void startGame() {
		status = Game_Status.ONGOING;
		Scanner input = new Scanner(System.in);
		hand.clear();
		deck.shuffle();
		
		System.out.println("Vänligen ange din kredit.");
		credit = input.nextInt();
	

		for (int i = 0; i < 5; i++) {
			hand.add(deck.draw());
		}
		
		System.out.println("Välkommen till en runda VideoPoker! \nDin hand är: ");
		System.out.println(hand.toString());
		discard();

		
		
		
	}
	
	// private void score() {
		
	// }
	
	
	private void discard() {
		int discard;
		
		String discarded = "";
		System.out.println("Vill du slänga några kort, tryck 1 för ja, 0 för nej");
		discard = input.nextInt();
		
		if (discard == 1) {
			System.out.println("Vilka kort vill du slänga i ordningen 1-5. Ex 123 slänger kort 1, 2 och 3");
			discarded = input.next();
		}else if(discard == 0) {
			//System.out.println("Du valde att stanna, din poäng blev:"); //Ska vi verkligen ha poäng? /Johan
			checkPayOut();
			System.out.println("Vill du köra en ny omgång? Tryck 1 för ja, 0 för nej " );
			Scanner scn = new Scanner(System.in);
			discard = scn.nextInt();
			
			if (discard == 1) {
				resetGame();
			} else
				System.exit(0);
			
		}
		replace(discarded);
		
		
	}
	
	private void replace(String discarded) {
		
		if(!discarded.equals("")) {
			for(int j = 0;j<discarded.length();j++) {
				
				int cardsToRemove=Integer.parseInt(discarded.substring(j,j+1));
				Card card = deck.draw();
				hand.set(cardsToRemove-1, card);
			}	
		System.out.println("Din nya hand blev: " + hand.toString());
		//System.out.println("Din poäng blev: ");
		checkPayOut();
		System.out.println("Vill du köra en ny omgång? Tryck 1 för ja, 0 för nej " );
		Scanner scn = new Scanner(System.in);
		int input = scn.nextInt();
		
		if (input == 1) {
			resetGame();
		} else if (input == 0) {
			System.out.println("Spelet är nu avslutat.");
			System.exit(0);
			}	
		}
	}
	
	private void resetGame() {
		System.out.println("Spelet startas nu om.....\n\n\n\n\n\n\n\n");
	    startGame();
	}
	

	private Game_Status checkPayOut()
	{
		if (s.straight(hand) && s.flush(hand)) {
			credit *= 500;
			System.out.println("Vinst! Färgstege ger 500 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		} else if  (s.fourOfAKind(hand)) {
			credit *= 250;
			System.out.println("Vinst! Fyrtal ger 250 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.fullHouse(hand)) {
			credit *= 100;
			System.out.println("Vinst! Kåk ger 100 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.flush(hand)) {
			credit *= 50;
			System.out.println("Vinst! Färg ger 50 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.straight(hand)) {
			credit *= 20;
			System.out.println("Vinst! Stege ger 20 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.threeOfAKind(hand)) {
			credit *= 10;
			System.out.println("Vinst! Triss ger 10 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.twoPair(hand)) {
			credit *= 5;
			System.out.println("Vinst! Tvåpar ger 5 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		}else if  (s.pair(hand)) {
			credit *= 1;
			System.out.println("Vinst! Par ger 1 gånger pengarna. Du har nu " +
								credit + " kr");
			return setGame_Status(Game_Status.WIN);
		} else {
			credit -= credit;
			System.out.println("Korten gav ingen vinst. Du förlorade dina pengar.");
			
			return Game_Status.LOSS;
		}
	}

	private Game_Status setGame_Status(Game_Status status)
	{
		this.status = status;
		return status;
	}

	private Game_Status getGame_Status(Game_Status status)
	{
		return status;
	}
	
	
}
	
