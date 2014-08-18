package cardSorting;

import java.util.ArrayList;

public class Creature extends Card{
	
	int power;
	int toughness;
	
	public Creature(Card card){

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
	
	public String displayPT(){
		String returnString = "";
		returnString += power + "/" + toughness;
		return returnString;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getToughness() {
		return toughness;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}
}
