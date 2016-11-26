package mainGUI;

import javax.swing.JButton;
import greedy.*;
import javax.swing.border.LineBorder;

import greedy.GreedySearch;
import scanning.DetectCordinates;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener{
	
	public XOButton(String s)
	{
		super(s);
		super.setMargin(new Insets(0, 0, 0, 0));
		super.setBorderPainted(true);
		super.setBorder(new LineBorder(Color.white, 1));
		super.setBackground(Color.GREEN);
		super.setPreferredSize(new Dimension(60, 60));
		super.setOpaque(true);
		super.setForeground(Color.decode("#008604"));
		this.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String pos = getPos(this);
		System.out.println(pos);
	    if(this.getParent().getName().equals("choose"))
	    {
			if(this.getText().equals("Greedy") )
			{
				Grid.algo = "Greedy";
//				Grid.gridColor = "minimax";
				this.setBackground(Color.WHITE);
				Grid.minimax.setBackground(Color.GREEN);
				
				System.out.println("Playing with greedy algorithm");
			}
			
			else if(this.getText().equals("2 Player"))
			{
				Grid.algo = "2Player";
//				Grid.gridColor = "greedy";
				this.setBackground(Color.WHITE);
				Grid.greedy.setBackground(Color.GREEN);
				System.out.println("Playing with 2 Player");
			}
	    }
		
	    else if(this.getParent().getName().equals("grid"))
	    {
			if(Grid.algo.equals("greedy"))
			{
				if(Grid.turn == "B" && Color.GREEN == this.getBackground())
				{
					
					DetectCordinates.yourColor = Color.BLACK;
					DetectCordinates.opponentColor = Color.WHITE;

					DetectCordinates.scanningTiles(pos);
					System.out.println(DetectCordinates.validate);

					if(DetectCordinates.validate > 0)
					{
						this.setBackground(DetectCordinates.yourColor);
						DetectCordinates.blackCordinates.add(pos);
						Grid.turn = "W";
					}
					DetectCordinates.cordinates.clear();
					DetectCordinates.filterTileList();

				}
				//Greedy working
				if(Grid.turn == "W")
				{
					
					
					DetectCordinates.yourColor = Color.WHITE;
					DetectCordinates.opponentColor = Color.BLACK;
					
					GreedySearch.getGreedyPositions();
					
					DetectCordinates.filterTileList();
					System.out.println("check");
					Grid.turn = "B";
				}
				
				System.out.println("Hor "+GreedySearch.horizontalWhite);
				System.out.println("Dio "+GreedySearch.diagonalWhite);
				System.out.println("Ver "+GreedySearch.verticalWhite);
				System.out.println("Black cord "+DetectCordinates.blackCordinates);
			}
			
			else if(Grid.algo.equals("2Player"))
			{
				if(Grid.turn == "B" && Color.GREEN == this.getBackground())
				{
					
					DetectCordinates.yourColor = Color.BLACK;
					DetectCordinates.opponentColor = Color.WHITE;

					DetectCordinates.scanningTiles(pos);
					System.out.println(DetectCordinates.validate);

					if(DetectCordinates.validate > 0)
					{
						this.setBackground(DetectCordinates.yourColor);
						DetectCordinates.blackCordinates.add(pos);
						Grid.turn = "W";
					}
					DetectCordinates.cordinates.clear();
					DetectCordinates.filterTileList();

				}
				else if(Grid.turn == "W" && Color.GREEN == this.getBackground())
					{
						
						DetectCordinates.yourColor = Color.WHITE;
						DetectCordinates.opponentColor = Color.BLACK;
						
						DetectCordinates.scanningTiles(pos);
						System.out.println(DetectCordinates.validate);
						if(DetectCordinates.validate > 0)
						{
							this.setBackground(DetectCordinates.yourColor);
							DetectCordinates.whiteCordinates.add(pos);
							Grid.turn = "B";
						}
						DetectCordinates.cordinates.clear();
						DetectCordinates.filterTileList();
					}
			
				System.out.println("Black tiles:::"+ DetectCordinates.blackCordinates);
				System.out.println("White tiles:::"+ DetectCordinates.whiteCordinates);
			}
				
				
				
//			}
			
//			else if(Grid.turn == "W" && Color.GREEN == this.getBackground())
//			{
////			Grid.turn = "B";
////			this.setBackground(Color.WHITE);
//				
//				DetectCordinates.yourColor = Color.WHITE;
//				DetectCordinates.opponentColor = Color.BLACK;
//				
//				DetectCordinates.scanningWhites(pos);
//				System.out.println(DetectCordinates.validate);
//				if(DetectCordinates.validate > 0)
//				{
//					this.setBackground(DetectCordinates.yourColor);
//					DetectCordinates.whiteCordinates.add(pos);
//					Grid.turn = "B";
//				}
//				DetectCordinates.cordinates.clear();
//				DetectCordinates.filterTileList();
//			}
	    }
	}
	
	public static void convertColor(ArrayList<String> cordinates) 
	{
		Color setColor = null;
		if(Grid.turn.equals("B"))
			setColor = Color.BLACK;
		else if(Grid.turn.equals("W"))
			setColor = Color.WHITE;
		for (String cord : cordinates) 
		{
			String xypos[] = cord.split(",");
			int xCord = Integer.parseInt(xypos[0]);
			int yCord = Integer.parseInt(xypos[1]);
			Grid.buttons[xCord][yCord].setBackground(setColor);
		}
	}

	private String getPos(XOButton xoButton) 
	{
		Point loc = xoButton.getLocation();
		int x = (loc.x/60);
		int y = (loc.y/60);
		return x+","+y;
	}
}