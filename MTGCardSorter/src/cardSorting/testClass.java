package cardSorting;

import java.util.ArrayList;

public class testClass {
	
	public static void main(String[] args){
		ArrayList<Card> cards = XMLParser.readCards("cards.xml");
		
		
		for(int i = 0; i < cards.size(); i++){	
			
				System.out.printf("%-15s %-2s", "Name:", cards.get(i).getName());
				System.out.printf("\n%-15s %-2s", "Colors:", cards.get(i).getColors());
				System.out.printf("\n%-15s %-2s", "Mana Cost: ", cards.get(i).getManaCost());
				System.out.printf("\n%-15s %-2s", "Type:", cards.get(i).getType());
				
				if(cards.get(i).type.contains("Creature")){
					System.out.printf("\n%-15s %-2s", "P/T:", ((Creature)cards.get(i)).displayPT());
				}
				
				System.out.printf("\n%-15s %-2s", "Text: ", cards.get(i).getText());
				
				if(cards.get(i).type.contains("Planeswalker")){
					System.out.printf("\n%-15s %-2s", "Loyalty:", ((Planeswalker)cards.get(i)).getLoyalty());
				}
				
				System.out.println();
				System.out.println();
		}
	}
}
