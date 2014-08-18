package cardSorting;

import java.sql.Types;
import java.util.ArrayList;

public class Card {
	
	int cmc;
	
	ArrayList<String> type = new ArrayList<String>();
	String supertype;
	ArrayList<String> subtype;
	
	String name;
	String text;
	
	boolean white;
	boolean blue;
	boolean black;
	boolean red;
	boolean green;
	
	String manaCost;
	boolean tribal;
	
	//set?
	
	public Card(){
		cmc = -1;
		
		type = new ArrayList<String>();
		supertype = "";
		subtype = new ArrayList<String>();
		
		name = "";
		text = "";
		
		white = false;
		blue = false;
		black = false;
		red = false;
		green = false;
		
		manaCost = "";
		tribal = false;
	}
	
	public String getType(){
		String returnString = "";
		
		//supertype
		if(!supertype.equals("")){
			returnString = supertype + " ";
		} 
		
		//types
		for(int i = 0; i < type.size(); i++){
			returnString += type.get(i) + " ";
		}
		
		//dash and subtypes
		if(subtype.size() != 0){
			returnString += "- ";
			
			for(int i = 1; i < subtype.size(); i++){
				returnString += subtype.get(i) + " ";
			}
		}
		
		return returnString;
	}
	
	public void addSubtype(String subtype){
		this.subtype.add(subtype);
	}
	
	public void addType(String type){
		this.type.add(type);
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public int getCmc() {
		return cmc;
	}

	public void setCmc(int cmc) {
		this.cmc = cmc;
	}

	public String getSupertype() {
		return supertype;
	}

	public void setSupertype(String supertype) {
		this.supertype = supertype;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}

	public boolean isBlue() {
		return blue;
	}

	public void setBlue(boolean blue) {
		this.blue = blue;
	}

	public boolean isBlack() {
		return black;
	}

	public void setBlack(boolean black) {
		this.black = black;
	}

	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public boolean isGreen() {
		return green;
	}

	public void setGreen(boolean green) {
		this.green = green;
	}

	public String getManaCost() {
		return manaCost;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}

	public Boolean getTribal() {
		return tribal;
	}

	public void setTribal(Boolean tribal) {
		this.tribal = tribal;
	}
	
	/**
	 * 	used in debugging. remove from final program
	 */
	public String getColors(){
		String output = "";
		
		if(white){
			output += "W";
		}
		if(blue){
			output += "U";
		}
		if(black){
			output += "B";
		}
		if(red){
			output += "R";
		}
		if(green){
			output += "G";
		}
		
		return output;
	}
}
