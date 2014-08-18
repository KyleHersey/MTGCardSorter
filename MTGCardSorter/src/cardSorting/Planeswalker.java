package cardSorting;

public class Planeswalker extends Card{
	int loyalty;
	
	public Planeswalker(Card card){
		this.cmc = card.cmc;
		
		this.type = 		card.type;
		this.supertype = 	card.supertype;
		this.subtype = 		card.subtype;
		
		this.name = 		card.name;
		this.text = 		card.text;
		
		this.white = 		card.white;
		this.blue = 		card.blue;
		this.black = 		card.black;
		this.red = 			card.red;
		this.green = 		card.green;
		
		this.manaCost =		card.manaCost;
		this.tribal = 		card.tribal;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}
}
