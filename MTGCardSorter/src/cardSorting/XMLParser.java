package cardSorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class XMLParser {

	public static ArrayList<Card> readCards(String filename){
		ArrayList<Card> cardList = new ArrayList<Card>();
		
		File XMLFile = new File(filename);
		
		//get Scanner object
		Scanner scan;
			try {
				scan = new Scanner(XMLFile);
			} catch (FileNotFoundException e) {
				System.out.println(filename + " not found");
				return null;
			}
			
		//get through initial non-card XML information
		while(!scan.next().equals("<cards>")){};
		
		boolean doneParsing = false;
		while(doneParsing == false){								//while not done parsing
			if(scan.next().equals("<card>")){							//if there's another card
				
				String cardText = "";	//this is all XML text that composes the card
				
				String nextLine = "";
				while(!nextLine.contains("</card>")){						//get the text until the card is finished
					nextLine = scan.nextLine();					
					cardText += "\n" + nextLine; //add text to cardText
				}
				
				Card finishedCard = parseCard(cardText);
				cardList.add(finishedCard);
			}
			
			else{														//if no more cards, done parsing
				doneParsing = true;
			}
		}
		
		cardList = cleanList(cardList);
		return cardList;
	}

	
	private static Card parseCard(String cardText) {

		boolean isCreature = false;
		boolean isPlaneswalker = false;
		
		Card returnCard = new Card();
			
			Scanner scan = new Scanner(cardText);
			
						/*
						 * To debug the card text to be parsed, use this line 
						 * 
						 * System.out.println(cardText + "\n");
						 */
						
			//get name-----------------------------------------------------------------------------
			String cardName = scan.next();
			while(!cardName.contains("</name>")){
				cardName += " "  + scan.next();
			}
			cardName = cardName.substring(6, cardName.length() - 7);
			
			//remove tokens from list
			if(cardName.contains("token")){
				returnCard.setName("RemoveMe");
				return returnCard;
			}
			
			returnCard.setName(cardName);
			
			
			//sets stuff-----------------------------------------------------------------------------
			boolean setStuff = true;
			String testString = "";
			while(setStuff){
				testString = scan.next();
				
				if(testString.contains("UNH") || testString.contains("UGL")){
					returnCard.setName("RemoveMe");
					return returnCard;
				}
				if(!testString.contains("<set") && !testString.contains("muId=")){
					setStuff = false;
				}
			}
			
			//get colors----------------------------------------------------------------------------
			scan.nextLine();	//move cursor to correct position
			String cardColor = testString;
			while(cardColor.contains("<color>")){
				
				Scanner charScan = new Scanner(cardColor);
				charScan.findInLine("<color>");
				String colorChar = "" + charScan.next().charAt(0);
				charScan.close();
				
				switch(colorChar){
					case "W": returnCard.setWhite(true); break;
					case "U": returnCard.setBlue(true); break;
					case "B": returnCard.setBlack(true); break;
					case "R": returnCard.setRed(true); break;
					case "G": returnCard.setGreen(true); break;	
				}
				
				cardColor = scan.nextLine();
			}
			
			//get manacost--------------------------------------------------------------------------
			String manaCost = cardColor;
			Scanner manaScan = new Scanner(manaCost);
			manaScan.findInLine("<manacost>");
			manaCost = manaScan.nextLine();
			manaCost = manaCost.substring(0, manaCost.length() - 11);
			returnCard.setManaCost(manaCost);
			
			//get type---------------------------------------------------------------------------------
				//get text to search
				String type = scan.nextLine();
				Scanner typeScan = new Scanner(type);
				typeScan.findInLine("<type>");
				type = typeScan.nextLine();
				type = type.substring(0, type.length() - 7);
				
			//dealing with vanguard cards
			if(type.contains("Vanguard")){
				returnCard.setName("RemoveMe");
				return returnCard;
			}
			
			Scanner subtypeScan = new Scanner(type);
			
			//dealing with tribal
			if(type.contains("Tribal")){
				returnCard.setTribal(true);
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			
			//supertypes
			if(type.contains("Basic")){
				returnCard.setSupertype("Basic");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Snow")){
				returnCard.setSupertype("Snow");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Legendary")){
				returnCard.setSupertype("Legendary");if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("World")){
				returnCard.setSupertype("World");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			
			//types
			if(type.contains("Enchantment")){
				returnCard.addType("Enchantment");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Artifact")){
				returnCard.addType("Artifact");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Creature")){
				returnCard.addType("Creature");
				isCreature = true;
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Instant")){
				returnCard.addType("Instant");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Sorcery")){
				returnCard.addType("Sorcery");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Land")){
				returnCard.addType("Land");
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			if(type.contains("Planeswalker")){
				returnCard.addType("Planeswalker");
				isPlaneswalker = true;
				if(subtypeScan.hasNext()){
					subtypeScan.next();
				}
			}
			
			String nextToken = "";
			if(!nextToken.contains("<tablerow>") && !nextToken.contains("<pt>")){
				while(subtypeScan.hasNext()){
					returnCard.addSubtype(subtypeScan.next());
				}
				
				//System.out.println(returnCard.getName());
				//System.out.println(nextToken);
			}
			
			
			//get power/toughness---------------------------------------------
			String PT = scan.next();
			if(isCreature){
				PT = PT.substring(4, PT.length() - 5);
				
				int power;
				int toughness;
				
				//finding the slash
				int slashIndex = PT.indexOf("/");
				
				//finding the power
				String pString = PT.substring(0, slashIndex);
				if(!pString.contains("*") && !pString.contains("+") && !pString.contains(".")){
					power = Integer.parseInt(pString);
				} else{
					power = 500;
				}
				
				//finding the toughness
				String tString = PT.substring(slashIndex + 1, PT.length());
				if(!tString.contains("*") && !tString.contains("+") && !tString.contains(".")){
					toughness = Integer.parseInt(tString);
				} else{
					toughness = 500;
				}
				
				//assigns returncard as a creature card
				returnCard = new Creature(returnCard);
				((Creature) returnCard).setPower(power);
				((Creature) returnCard).setToughness(toughness);
			}
			
			
			//get text--------------------------------------------------------
			scan.nextLine();
			
			String text = scan.nextLine();
			if(text.contains("<tablerow>")){
				text = scan.nextLine();
			}
			
			while(!text.contains("</text>")){
				text += "\n" + scan.nextLine();	
			}
			
			Scanner textScan = new Scanner(text);
			textScan.findInLine("<text>");
			text = textScan.nextLine();
			while(!text.contains("</text>")){
				text += "\n" + textScan.nextLine();	
			}
			text = text.substring(0, text.length() - 7);
			
			returnCard.setText(text);
			
			
			
		return returnCard;
	}
	
	private static ArrayList<Card> cleanList(ArrayList<Card> list){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getName().equals("RemoveMe")){
				list.remove(i);
				i--;
			}
		}
		
		return list;
	}
	
}
