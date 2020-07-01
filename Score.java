
import java.util.*;
import java.lang.*;



public class Score{
	
	public boolean pair(ArrayList<Card> hand) {
		 
		 Card card1;
		 Card card2;
		 
		 for(int i = 0; i < hand.size(); i++) {  //Loopen tittar om det finns två kort med samma värde
				card1 = hand.get(i);
				
				for(int j = i + 1; j < hand.size(); j++) {
					card2 = hand.get(j); 
					if (card1.getValue() == (card2.getValue())) {
						return true;
					}
				} 
			} return false;
	}
	
	public boolean threeOfAKind(ArrayList<Card> hand) {
		
		 Card card1;
		 Card card2;
		 Card card3;
		 
		 for(int i = 0; i < hand.size(); i++) { //Loopen tittar om det finns tre kort med samma värde
				card1 = hand.get(i);
				
				for(int j = i + 1; j < hand.size(); j++) {
					card2 = hand.get(j); 
					if (card1.getValue() == (card2.getValue())) {
						
						for(int k = j + 1; k < hand.size(); k++) {
							card3 = hand.get(k);
							if (card2.getValue() == card3.getValue()) {
								return true;
							}
						}
					} 
				} 
			}
		
		return false;
	}

	boolean fourOfAKind(ArrayList<Card> hand) {

		Card card1;
		Card card2;
		Card card3;
		Card card4;
		
		for (int i = 0; i < hand.size(); i++) {
			card1 = hand.get(i);

			for (int j = i + 1; j < hand.size(); j++) {
				card2 = hand.get(j);
				if (card1.getValue() == card2.getValue()) {

					for (int k = j + 1; k < hand.size(); k++) {
						card3 = hand.get(k);
						if (card2.getValue() == card3.getValue()) {
							
							for (int l = k + 1; l < hand.size(); l++) {
								card4 = hand.get(l);
								if (card3.getValue() == card4.getValue()) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	boolean twoPair(ArrayList<Card> hand) {

		ArrayList<Card> tempHand;
		tempHand = (ArrayList<Card>)hand.clone();

		Card card1;
		Card card2;
		Card card3;
		Card card4;
		
		for (int i = 0; i < tempHand.size(); i++) {
			card1 = tempHand.get(i);

			for (int j = i + 1; j < tempHand.size(); j++) {
				card2 = tempHand.get(j);
				
				if (card1.getValue() == card2.getValue()) {
					tempHand.remove(card1);
					tempHand.remove(card2);
					
					for(int k = 0; k < tempHand.size(); k++) {
						card3 = tempHand.get(k);
						
						for (int l = k + 1; l < tempHand.size(); l++) {
							card4 = tempHand.get(l);
							
							if (card3.getValue() == card4.getValue()) {
								return true;
							}
						}
					}
				}
			}
		}
		
	return false;
	}

	boolean fullHouse(ArrayList<Card> hand) {

		ArrayList<Card> tempHand;
		tempHand = (ArrayList<Card>)hand.clone();

		Card card1;
		Card card2;
		Card card3;
		Card card4;
		Card card5;
		
		for (int i = 0; i < tempHand.size(); i++) {
			card1 = tempHand.get(i);

			for (int j = i + 1; j < tempHand.size(); j++) {
				card2 = tempHand.get(j);
				if (card1.getValue() == card2.getValue()) {

					for (int k = j + 1; k < hand.size(); k++) {
						card3 = tempHand.get(k);
						if (card2.getValue() == card3.getValue()) {
							tempHand.remove(card1);
							tempHand.remove(card2);
							tempHand.remove(card3);
							card4 = tempHand.get(0);
							card5 = tempHand.get(1);
							
							if (card4.getValue() == card5.getValue()) {
								return true;
							}						
						}
					}
				}
			}
		}
		
	return false;
	}

	boolean flush (ArrayList<Card> hand) {

		final Suit firstSuit = hand.get(0).getSuit();

		for (int i = 1; i < hand.size(); i++)
		{
			if (firstSuit != hand.get(i).getSuit())
				return false;
		}

		return true;
	}

	boolean straight (ArrayList<Card> hand) {
		ArrayList<Card> tempHand;
		tempHand = (ArrayList<Card>)hand.clone();
		
		tempHand.sort(Comparator.comparing(Card::getValue)); //sorterar efter valör

		if (tempHand.get(0).getValue() != 1) //om ESS saknas
		{
			return increasesWithOne(tempHand);
		} else //här är första kortet ett ESS
		{
			tempHand.remove(0);
			return (increasesWithOne(tempHand) && tempHand.get(0).getValue() == 2 || //liten stege börjar med 2
					increasesWithOne(tempHand) && tempHand.get(0).getValue() == 10 ); //stor stege börjar med 10
		}
	}

	private boolean increasesWithOne(ArrayList<Card> hand) 
				//hjälp till straight() pga DRY, säger om värdet ökar med 1 för varje kort
	{
		hand.sort(Comparator.comparing(Card::getValue)); //sorterar efter valör

		for (int i = 0; i+1 < hand.size(); i++)
		{
			if (hand.get(i).getValue() != (hand.get(i+1).getValue() - 1) ) //måste öka med ett hela tiden annars är det ingen stege
			{
				return false;
			}
		}
		return true;
	}

	

}
