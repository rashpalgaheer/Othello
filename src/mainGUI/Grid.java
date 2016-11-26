 package mainGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import greedy.GreedySearch;
import scanning.DetectCordinates;

public class Grid implements ActionListener, MouseListener {

	public static String turn = "B";
	
//	public static String gridColor = "greedy";
	public static String algo = "greedy";
	public static XOButton buttons[][]=new XOButton[8][8]; 
	public static XOButton greedy = new XOButton("Greedy");
	public static XOButton minimax = new XOButton("2 Player");
	
	public static void main(String main[])
	{
		JFrame frame = new JFrame("Grid");
		JPanel panel = new JPanel(new GridLayout(8,8));
		panel.setName("grid");
		JPanel panel1 = new JPanel(new GridLayout());
		panel1.setName("choose");
		
		for(int i =0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				buttons[j][i]=new XOButton(j+","+i);
				panel.add(buttons[j][i]);
			}
		}
		setStartignBoard();
		greedy.setBorder(new LineBorder(Color.BLACK, 1));
		minimax.setBorder(new LineBorder(Color.BLACK, 1));
		panel1.add(greedy);
		panel1.add(minimax);
		
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel1, BorderLayout.SOUTH);
		frame.pack();
		
		frame.setVisible(true);
//		buttons[0][1].setBackground(Color.BLACK);
//		buttons[4][1].setBackground(Color.BLACK);
//		buttons[1][1].setBackground(Color.WHITE);
//		buttons[3][1].setBackground(Color.WHITE);
//		buttons[3][2].setBackground(Color.WHITE);
//		buttons[5][4].setBackground(Color.BLACK);
		//test 2
//		buttons[5][4].setBackground(Color.BLACK);
//		buttons[5][3].setBackground(Color.BLACK);
//		buttons[4][2].setBackground(Color.WHITE);
//		buttons[2][2].setBackground(Color.WHITE);
//		
//		buttons[1][1].setBackground(Color.BLACK);
//		buttons[2][1].setBackground(Color.BLACK);
//		buttons[3][1].setBackground(Color.BLACK);
//		
//		buttons[5][5].setBackground(Color.WHITE);
//		
//		if(Grid.turn == "W")
//		{
//			DetectCordinates.yourColor = Color.WHITE;
//			DetectCordinates.opponentColor = Color.BLACK;
//			
//			GreedySearch.getGreedyPositions();
//			
//			DetectCordinates.filterTileList();
//			System.out.println("check");
//			System.out.println(GreedySearch.diagonalWhite);
//		}
	}

	private static void setStartignBoard() 
	{
		buttons[3][3].setBackground(Color.BLACK);
		buttons[3][4].setBackground(Color.WHITE);
		buttons[4][3].setBackground(Color.WHITE);
		buttons[4][4].setBackground(Color.BLACK);
		DetectCordinates.whiteCordinates.add(3+","+4);
		DetectCordinates.whiteCordinates.add(4+","+3);
		DetectCordinates.blackCordinates.add(3+","+3);
		DetectCordinates.blackCordinates.add(4+","+4);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	    int x=e.getX();
	    int y=e.getY();
	    System.out.println(x+","+y);//these co-ords are relative to the component
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
