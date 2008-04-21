package GUI;

import java.awt.*;
import javax.swing.*;

import snd.BTSendObject;

import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.util.LinkedList;

public class GUI extends JFrame implements ActionListener {

	JButton preB, cusB, adminB, backBP, backBC, backBA, makeBP, makeBC, addBC,
			remBC, applyBA;
	JRadioButton stirRB, iceRB, alc1RB, alc2RB, alc3RB;
	JFrame chFrame, cusFrame, preFrame, adFrame;
	JPanel contentPanel;
	JLabel selectL, eSymbol, costL;
	JTextArea preIngredsList, cusIngredsList;
	JTextField costP, costC, cost1, cost2, cost3;
	JComboBox cocktail, cusIngreds, drink1, drink2, drink3;
	int numIngreds;
	double cost, temp;
	boolean stir, ice;
	String[] cocktailsAvail = { "Screwdriver", "Sex on the Beach" };
	String[] ingredsList = { "None", "Vodka", "Whiskey", "Malibu", "Gin",
			"Pineapple Juice", "Red Bull", "Coke", "Lime", "Orange Juice",
			"Cranberry Juice" };
	Beverage bev1, bev2, bev3;
	Vector<Cocktail> cocktails = new Vector<Cocktail>();
	Vector<Beverage> bevVec = new Vector<Beverage>();
	Vector<Beverage> allBevVec = new Vector<Beverage>();
	Vector<String> ingredsVec = new Vector<String>();
	Vector<String> cocktailsVec = new Vector<String>();
	Cocktail none;
	BTSendObject send = new BTSendObject();

	public GUI(){
		initCocktails();
		setupPanel();
		ChoiceFrame();
	}
	
	public void setupPanel() {
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.white);
		contentPanel.setOpaque(true);
	}

	public void initCocktails() {
		none = new Cocktail("None");
		Cocktail tempCock = new Cocktail("Vodka + Coke");
		tempCock.addDrink("Coke");
		tempCock.addDrink("Vodka");
		cocktails.add(tempCock);
		
		tempCock = new Cocktail("Whiskey + Coke");
		tempCock.addDrink("Whiskey");
		tempCock.addDrink("Coke");
		cocktails.add(tempCock);
	}
	
	public Beverage matchBev(String s){
		Beverage matchedBev = new Beverage("None", 0, false, 0);
		for(int i=0; i<bevVec.size(); i++){
			if(bevVec.get(i).getName().equalsIgnoreCase(s)){
				matchedBev = bevVec.get(i);
				break;
			}
		}
		return matchedBev;
	}
	
	public Cocktail matchCocktail(String s){
		Cocktail matchedCocktail = new Cocktail("None");
		for(int i=0; i<cocktails.size(); i++){
			if(s.equalsIgnoreCase(cocktails.get(i).getName())){
				matchedCocktail = cocktails.get(i);
			}
		}
		return matchedCocktail;
	}
	
	public boolean bevAvail(String s){
		for(int i=0; i<bevVec.size(); i++){
			if(bevVec.get(i).getName().equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}

	public void setAvail() {
		ingredsVec.clear();
		for (int i = 0; i < bevVec.size(); i++) {
			ingredsVec.add(bevVec.get(i).getName());
		}
		cocktailsAvail();
	}
	
	public void resetDrinks() {
		for(int i = 0; i<allBevVec.size(); i++) {
			allBevVec.get(i).setAvailable(false);
		}
	}
	
	public void cocktailsAvail() {
		cocktailsVec.clear();
		cocktailsVec.add(none.getName());
		for(int i=0; i<cocktails.size(); i++){
			LinkedList<String> toCheck = cocktails.get(i).getList();
			for(int j=0; j<toCheck.size(); j++){
				if(!bevAvail(toCheck.get(j))){
					cocktails.get(i).setAvail(false);
					break;
				}
				else{
					cocktails.get(i).setAvail(true);
				}
			}
			if(cocktails.get(i).getAvail()){
				cocktailsVec.add(cocktails.get(i).getName());
			}
		}
	}

	public void updateCCost() {
		temp = cost / 100;
		costC.setText(Double.toString(temp));
	}

	public void AdminFrame() {
		adFrame = new JFrame("Auto-Cock: Admin Panel");
		adFrame.setContentPane(contentPanel);
		adFrame.setResizable(false);

		adFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		adFrame.setLayout(null);
		adFrame.setSize(600, 400);

		costL = new JLabel("Cost");
		costL.setLocation(390, 50);
		costL.setSize(100, 30);

		drink1 = new JComboBox(ingredsList);
		drink1.setLocation(50, 80);
		drink1.setSize(200, 30);
		drink1.setSelectedIndex(0);
		drink1.addActionListener(this);

		alc1RB = new JRadioButton("Alcoholic?", false);
		alc1RB.setLocation(270, 80);
		alc1RB.setSize(100, 30);
		alc1RB.setOpaque(false);
		alc1RB.addActionListener(this);

		cost1 = new JTextField("0");
		cost1.setLocation(390, 80);
		cost1.setSize(100, 30);
		cost1.setHorizontalAlignment(JTextField.RIGHT);

		drink2 = new JComboBox(ingredsList);
		drink2.setLocation(50, 120);
		drink2.setSize(200, 30);
		drink2.setSelectedIndex(0);
		drink2.addActionListener(this);

		alc2RB = new JRadioButton("Alcoholic?", false);
		alc2RB.setLocation(270, 120);
		alc2RB.setSize(100, 30);
		alc2RB.setOpaque(false);
		alc2RB.addActionListener(this);

		cost2 = new JTextField("0");
		cost2.setLocation(390, 120);
		cost2.setSize(100, 30);
		cost2.setHorizontalAlignment(JTextField.RIGHT);

		drink3 = new JComboBox(ingredsList);
		drink3.setLocation(50, 160);
		drink3.setSize(200, 30);
		drink3.setSelectedIndex(0);
		drink3.addActionListener(this);

		alc3RB = new JRadioButton("Alcoholic?", false);
		alc3RB.setLocation(270, 160);
		alc3RB.setSize(100, 30);
		alc3RB.setOpaque(false);
		alc3RB.addActionListener(this);

		cost3 = new JTextField("0");
		cost3.setLocation(390, 160);
		cost3.setSize(100, 30);
		cost3.setHorizontalAlignment(JTextField.RIGHT);

		backBA = new JButton("Back");
		backBA.setLocation(50, 320);
		backBA.setSize(100, 30);
		backBA.addActionListener(this);

		applyBA = new JButton("Apply");
		applyBA.setLocation(390, 320);
		applyBA.setSize(100, 30);
		applyBA.addActionListener(this);

		adFrame.add(costL);
		adFrame.add(drink1);
		adFrame.add(alc1RB);
		adFrame.add(cost1);
		adFrame.add(drink2);
		adFrame.add(alc2RB);
		adFrame.add(cost2);
		adFrame.add(drink3);
		adFrame.add(alc3RB);
		adFrame.add(cost3);
		adFrame.add(backBA);
		adFrame.add(applyBA);

		adFrame.setVisible(true);
	}

	public void ChoiceFrame() {
		chFrame = new JFrame("Auto-Cock");
		chFrame.setContentPane(contentPanel);
		chFrame.setResizable(false);

		ImageIcon preButtonI = new ImageIcon("images/premadeB.jpg");
		ImageIcon cusButtonI = new ImageIcon("images/customB.jpg");

		// Add a window listener for close button
		chFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		chFrame.setLayout(null);
		chFrame.setSize(600, 400);

		selectL = new JLabel("Please Select a Section");
		selectL.setLocation(225, 40);
		selectL.setSize(150, 20);
		preB = new JButton(preButtonI);
		preB.setLocation(112, 100);
		preB.setSize(160, 200);
		preB.addActionListener(this);
		cusB = new JButton(cusButtonI);
		cusB.setLocation(337, 100);
		cusB.setSize(160, 200);
		cusB.addActionListener(this);
		adminB = new JButton("Admin Panel");
		adminB.setLocation(220, 310);
		adminB.setSize(160, 30);
		adminB.addActionListener(this);

		chFrame.add(selectL);
		chFrame.add(preB);
		chFrame.add(cusB);
		chFrame.add(adminB);
		chFrame.setVisible(true);
	}

	public void PremadeFrame() {
		preFrame = new JFrame("Auto-Cock: Premade Section");
		preFrame.setContentPane(contentPanel);
		preFrame.setResizable(false);

		preFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		preFrame.setLayout(null);
		preFrame.setSize(600, 400);

		cocktail = new JComboBox(cocktailsVec);
		cocktail.setLocation(50, 80);
		cocktail.setSize(200, 30);
		cocktail.setSelectedIndex(0);
		cocktail.addActionListener(this);

		backBP = new JButton("Back");
		backBP.setLocation(50, 320);
		backBP.setSize(100, 30);
		backBP.addActionListener(this);

		preIngredsList = new JTextArea(250, 240);
		preIngredsList.setLocation(300, 80);
		preIngredsList.setLineWrap(false);
		preIngredsList.setRows(10);
		preIngredsList.setSize(250, 240);
		preIngredsList.setEditable(false);
		preIngredsList.setText("Ingredients");

		eSymbol = new JLabel("�");
		eSymbol.setLocation(330, 320);
		eSymbol.setSize(20, 30);

		costP = new JTextField();
		costP.setLocation(350, 320);
		costP.setSize(100, 30);
		costP.setEditable(false);
		costP.setHorizontalAlignment(JTextField.RIGHT);

		makeBP = new JButton("Make");
		makeBP.setLocation(450, 320);
		makeBP.setSize(100, 30);
		makeBP.addActionListener(this);

		preFrame.add(backBP);
		preFrame.add(cocktail);
		preFrame.add(preIngredsList);
		preFrame.add(eSymbol);
		preFrame.add(costP);
		preFrame.add(makeBP);

		preFrame.setVisible(true);
	}

	public void CustomFrame() {
		cusFrame = new JFrame("Auto-Cock: Custom Section");
		cusFrame.setContentPane(contentPanel);
		cusFrame.setResizable(false);

		cusFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		cusFrame.setLayout(null);
		cusFrame.setSize(600, 400);

		numIngreds = 0;
		cost = 0;
		stir = false;

		cusIngreds = new JComboBox(ingredsVec);
		cusIngreds.setLocation(50, 80);
		cusIngreds.setSize(200, 30);
		cusIngreds.setSelectedIndex(0);
		cusIngreds.addActionListener(this);

		addBC = new JButton("Add");
		addBC.setLocation(50, 120);
		addBC.setSize(100, 30);
		addBC.addActionListener(this);

		remBC = new JButton("Remove");
		remBC.setLocation(50, 160);
		remBC.setSize(100, 30);
		remBC.addActionListener(this);

		stirRB = new JRadioButton("Stir?", false);
		stirRB.setLocation(50, 200);
		stirRB.setSize(100, 30);
		stirRB.setOpaque(false);
		stirRB.addActionListener(this);

		iceRB = new JRadioButton("Ice?", false);
		iceRB.setLocation(50, 230);
		iceRB.setSize(100, 30);
		iceRB.setOpaque(false);
		iceRB.addActionListener(this);

		backBC = new JButton("Back");
		backBC.setLocation(50, 320);
		backBC.setSize(100, 30);
		backBC.addActionListener(this);

		cusIngredsList = new JTextArea(250, 240);
		cusIngredsList.setLocation(300, 80);
		cusIngredsList.setLineWrap(false);
		cusIngredsList.setRows(10);
		cusIngredsList.setSize(250, 240);
		cusIngredsList.setEditable(false);

		eSymbol = new JLabel("�");
		eSymbol.setLocation(330, 320);
		eSymbol.setSize(20, 30);

		costC = new JTextField();
		costC.setLocation(350, 320);
		costC.setSize(100, 30);
		costC.setEditable(false);
		costC.setHorizontalAlignment(JTextField.RIGHT);

		makeBC = new JButton("Make");
		makeBC.setLocation(450, 320);
		makeBC.setSize(100, 30);
		makeBC.addActionListener(this);

		cusFrame.add(cusIngreds);
		cusFrame.add(addBC);
		cusFrame.add(remBC);
		cusFrame.add(stirRB);
		cusFrame.add(iceRB);
		cusFrame.add(backBC);
		cusFrame.add(cusIngredsList);
		cusFrame.add(eSymbol);
		cusFrame.add(costC);
		cusFrame.add(makeBC);

		updateCCost();
		cusFrame.setVisible(true);
	}

	public void actionPerformed(ActionEvent aE) {
		Object src = aE.getSource();

		if (src == preB) {
			if (cocktailsVec.isEmpty()) {

			} else {
				chFrame.setVisible(false);
				setupPanel();
				PremadeFrame();
			}
		} else if (src == cusB) {
			if (bevVec.isEmpty()) {

			} else {
				chFrame.setVisible(false);
				setupPanel();
				CustomFrame();
			}
		} else if (src == adminB) {
			chFrame.setVisible(false);
			setupPanel();
			AdminFrame();
		} else if (src == backBA) {
			adFrame.setVisible(false);
			setupPanel();
			chFrame.setVisible(true);
		} else if (src == backBP) {
			preFrame.setVisible(false);
			setupPanel();
			chFrame.setVisible(true);
		} else if (src == backBC) {
			cusFrame.setVisible(false);
			setupPanel();
			chFrame.setVisible(true);
		} else if (src == makeBP) {
			System.out.println("Making your drink!");
			Cocktail selected = matchCocktail((String)cocktail.getSelectedItem());
			int[] toSend = selected.getArray(bevVec);
			send.startClient(toSend);
			send.stopClient();
		} else if (src == makeBC) {
			if (numIngreds != 0) {
				System.out.println("Making your drink!");
				BufferedReader in = new BufferedReader(new StringReader(
						cusIngredsList.getText()));
				String line;
				int[] position = new int[numIngreds + 2];
				try {
					for (int i = 0; i < numIngreds; i++) {
						line = in.readLine();
						Beverage tempBev = matchBev(line);
						position[i] = tempBev.getId();
						// System.out.println(tempBev.getId());
					}
				} catch (Exception e) {
				}
				if(iceRB.isSelected()){
					position[numIngreds] = 499;
				}
				else{
					position[numIngreds] = 498;
				}
				if(stirRB.isSelected()){
					position[numIngreds+1] = 999;
				}
				else{
					position[numIngreds+1] = 998;
				}
				send.startClient(position);
				send.stopClient();
			}
		} else if (src == addBC) {
			if (numIngreds < 6) {
				String selIngred = (String) cusIngreds.getSelectedItem();
				cusIngredsList.append(selIngred + "\n");
				numIngreds++;
				// int curIndex = cusIngreds.getSelectedIndex();
				cost += matchBev(selIngred).getPrice();
				updateCCost();
			} else {
				System.out
						.println("Too many ingredients. Please remove one if you would like to add more.");
			}
		} else if (src == remBC) {
			String selIngred = (String) cusIngreds.getSelectedItem();
			BufferedReader in = new BufferedReader(new StringReader(
					cusIngredsList.getText()));
			String line;
			String toUpdate = "";
			boolean removed = false;
			try {
				while ((line = in.readLine()) != null) {
					if (line.equalsIgnoreCase(selIngred) && removed == false) {
						System.out.println("Removing: " + selIngred + "\n");
						// int curIndex = cusIngreds.getSelectedIndex();
						cost -= matchBev(selIngred).getPrice();
						updateCCost();
						numIngreds--;
						removed = true;
					} else {
						toUpdate = toUpdate + line + "\n";
					}
				}
			} catch (IOException e) {

			}
			cusIngredsList.setText(toUpdate);
		} else if (src == cocktail) {
			JComboBox tempBox = (JComboBox) src;
			String drink = (String) tempBox.getSelectedItem();
			//System.out.println(drink);
			Cocktail selected = matchCocktail(drink);
			selected.calcPrice(bevVec);
			Double tempCost = Double.valueOf(selected.getPrice()/100).doubleValue();
			//costP.setText(String.valueOf(selected.getPrice()));
			costP.setText(String.valueOf(tempCost));
			preIngredsList.setText("");
			LinkedList<String> list = selected.getList();
			for(int i=0; i<list.size(); i++){
				preIngredsList.append(list.get(i) + "\n");
			}
		} else if (src == applyBA) {
			Double tempCost;
			int tempCost2;
			resetDrinks();
			bevVec.clear();
			cocktailsVec.clear();

			JComboBox tempBox = (JComboBox) drink1;
			if ((String) tempBox.getSelectedItem() == "None"
					|| cost1.getText() == "") {

			} else {
				tempCost = Double.valueOf(cost1.getText()).doubleValue();
				tempCost2 = (int) (tempCost * 100);
				bev1 = new Beverage((String) tempBox.getSelectedItem(),
						tempCost2, alc1RB.isSelected(), 1);
				bevVec.add(bev1);
			}

			tempBox = (JComboBox) drink2;
			if ((String) tempBox.getSelectedItem() == "None"
					|| cost2.getText() == "") {
			} else {
				tempCost = Double.valueOf(cost2.getText()).doubleValue();
				tempCost2 = (int) (tempCost * 100);
				bev2 = new Beverage((String) tempBox.getSelectedItem(),
						tempCost2, alc2RB.isSelected(), 2);
				bevVec.add(bev2);
			}

			tempBox = (JComboBox) drink3;
			if ((String) tempBox.getSelectedItem() == "None"
					|| cost3.getText() == "") {

			} else {
				tempCost = Double.valueOf(cost3.getText()).doubleValue();
				tempCost2 = (int) (tempCost * 100);
				bev3 = new Beverage((String) tempBox.getSelectedItem(),
						tempCost2, alc3RB.isSelected(), 3);
				bevVec.add(bev3);
			}
			setAvail();
		}
	}

}
