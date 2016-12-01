package mainGUI;

import javax.swing.JButton;
import greedy.*;
import javax.swing.border.LineBorder;

import greedy.GreedySearch;
import minimax_one.GameBoard;
import minimax_one.GameSettings;
import minimax_one.ReversiBot;
import minimax_one.Constant.GameState;
import minimax_two.My_Othello;
import scanning.DetectCordinates;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;

public class XOButton extends JButton implements ActionListener
{
	GameBoard board;
	private ReversiBot bot;
	private static GameSettings _settings;
	
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
	    	Component[] buttonList = this.getParent().getComponents();
	    	for (Component component : buttonList)
	    	{
	    	    component.setBackground(Color.GREEN);
	    	}
	    	
			if(this.getText().equals("GreedyR") )
			{
				Grid.algo = "greedy";
				Grid.name = "rash";
//				Grid.gridColor = "minimax";
				this.setBackground(Color.WHITE);
				
//				Grid.two_player.setBackground(Color.GREEN);
				
				System.out.println("Playing with greedy algorithm");
			}
			if(this.getText().equals("GreedyK") )
			{
				Grid.algo = "greedy";
				Grid.name = "karan";
//				Grid.gridColor = "minimax";
				this.setBackground(Color.WHITE);
//				Grid.two_player.setBackground(Color.GREEN);
				
				System.out.println("Playing with greedy algorithm");
			}
			
			else if(this.getText().equals("2 Player"))
			{
				Grid.algo = "2Player";
//				Grid.gridColor = "greedy";
				this.setBackground(Color.WHITE);
//				Grid.greedyRASH.setBackground(Color.GREEN);
				System.out.println("Playing with 2 Player");
			}
			else if(this.getText().equals("minimax"))
			{
				Grid.algo = "minimax";
				_settings = new GameSettings();
				this.setBackground(Color.WHITE);
				board = new GameBoard();
			}
			else if(this.getText().equals("minimax2"))
			{
				Grid.algo = "minimax2";
				My_Othello.getInstance().start_newGame(true);
				this.setBackground(Color.WHITE);
			}
			else if(this.getText().equals("KvsR"))
			{
				Grid.enableKvsR = true;
				this.setBackground(Color.WHITE);
				Grid.algo = "VS";
				_settings = new GameSettings();
				board = new GameBoard();
				/////////////////////////////
				My_Othello.getInstance().start_newGame(true);
			}
	    }
	    
	    else if(this.getParent().getName().equals("miniVSmini") && Grid.enableKvsR == true)
	    {
	    	Component[] buttonList = this.getParent().getComponents();
	    	for (Component component : buttonList)
	    	{
	    	    component.setBackground(Color.GREEN);
	    	}
	    	
	    	Grid.turn = "";
	    	if(this.getText().equals("minimaxONE"))
			{

	    		this.setBackground(Color.WHITE);
	    		
	    		bot = new ReversiBot(GameState.WHITE);
				_settings.setTurn(GameState.WHITE);
				Point move = bot.nextTurn(_settings.getGameBoard());
				submitMove(move, bot.getPlayer());
				
				System.out.println("::::::::::::::Point move :::" + move);	
				
				DetectCordinates.yourColor = Color.WHITE;
				DetectCordinates.opponentColor = Color.BLACK;
				
				DetectCordinates.scanningTiles((int)move.getX()+","+(int)move.getY());
				Grid.lastMoveX = (int)move.getX();
				Grid.lastMoveY = (int)move.getY();
				System.out.println(DetectCordinates.validate);
				
				if(DetectCordinates.validate > 0)
				{
					Grid.buttons[(int)move.getX()][(int)move.getY()].setBackground(DetectCordinates.yourColor);
					DetectCordinates.whiteCordinates.add((int)move.getX()+","+(int)move.getY());
					
				}
				
				System.out.println(GameBoard._board);
				
				DetectCordinates.cordinates.clear();
				DetectCordinates.filterTileList();
	    		
			}
	    	if(this.getText().equals("minimaxTWO"))
			{
	    		
	    		
	    		this.setBackground(Color.WHITE);
	    		
				int row = Grid.lastMoveX;
				int col = Grid.lastMoveY;
				
	    		
	    		DetectCordinates.yourColor = Color.BLACK ;
				DetectCordinates.opponentColor = Color.WHITE;
				
				
				if (row >= 0 && row <8 && col >=0 && col < 8)
					My_Othello.getInstance().move_player(row, col);
			}
	    }
	    else if(this.getParent().getName().equals("grid"))
	    {
	    	
	    	
			if(Grid.algo.equals("greedy") && Grid.name.equals("rash") )
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
			else if(Grid.algo.equals("greedy") && Grid.name.equals("karan") )
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
					
					GreedySearchKaran.getGreedyPositions();
					
					DetectCordinates.filterTileList();
					System.out.println("check");
					Grid.turn = "B";
				}
				
				System.out.println("Hor "+GreedySearchKaran.storeWhiteHORIZONTAL);
				System.out.println("Dio "+GreedySearchKaran.storeWhiteHORIZONTAL);
				System.out.println("Ver "+GreedySearchKaran.storeWhiteHORIZONTAL);
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
			
			
			if(Grid.algo.equals("minimax"))
			{
				System.out.println("minimaX:::::::"+pos);
				System.out.println(GameBoard._board);

				String xypos[] = pos.split(",");
				int x = Integer.parseInt(xypos[0]);
				int y = Integer.parseInt(xypos[1]);
//				if(Grid.turn == "B" && Color.GREEN == this.getBackground())
//				{
					System.out.println("Yes");
					if(_settings.getGameBoard().makeMove(new Point(x, y), _settings.getTurn()) > 0)
					{
						DetectCordinates.yourColor = Color.BLACK;
						DetectCordinates.opponentColor = Color.WHITE;

						DetectCordinates.scanningTiles(x+","+y);
						System.out.println(DetectCordinates.validate);
						if(DetectCordinates.validate > 0)
						{
							Grid.buttons[x][y].setBackground(DetectCordinates.yourColor);
							DetectCordinates.blackCordinates.add(pos);
							Grid.turn = "W";
							changeTurn();
						}
						DetectCordinates.cordinates.clear();
						DetectCordinates.filterTileList();
						
						
						if(Grid.turn == "W")
						{
							bot = new ReversiBot(GameState.WHITE);
							_settings.setTurn(GameState.WHITE);
							Point move = bot.nextTurn(_settings.getGameBoard());
							submitMove(move, bot.getPlayer());
							
							System.out.println("::::::::::::::Point move :::" + move);	
							
							DetectCordinates.yourColor = Color.WHITE;
							DetectCordinates.opponentColor = Color.BLACK;
							
							DetectCordinates.scanningTiles((int)move.getX()+","+(int)move.getY());
							System.out.println(DetectCordinates.validate);
							
							if(DetectCordinates.validate > 0)
							{
								Grid.buttons[(int)move.getX()][(int)move.getY()].setBackground(DetectCordinates.yourColor);
								DetectCordinates.whiteCordinates.add(pos);
								Grid.turn = "B";
							}
							
							DetectCordinates.cordinates.clear();
							DetectCordinates.filterTileList();
						}
					}
					else
						declareWinner();
//				}
				

			}
				
			if(Grid.algo.equals("minimax2"))
			{
				if (My_Othello.getInstance().getGameState() == My_Othello.PLAYING && 
						!My_Othello.getInstance().getIsCompTurn()) 
				{
					
					
					String xypos[] = pos.split(",");
					int row = Integer.parseInt(xypos[0]);
					int col = Integer.parseInt(xypos[1]);
					

					DetectCordinates.yourColor = Color.BLACK;
					DetectCordinates.opponentColor = Color.WHITE;

					DetectCordinates.scanningTiles(pos);
					System.out.println(DetectCordinates.validate);
					if(DetectCordinates.validate > 0)
					{
						Grid.buttons[row][col].setBackground(DetectCordinates.yourColor);
						DetectCordinates.blackCordinates.add(pos);
						Grid.turn = "W";
					}
					DetectCordinates.cordinates.clear();
					DetectCordinates.filterTileList();
					
					if(Grid.turn == "W")
					{
						DetectCordinates.yourColor = Color.WHITE;
						DetectCordinates.opponentColor = Color.BLACK;
						
						
						if (row >= 0 && row <8 && col >=0 && col < 8)
							My_Othello.getInstance().move_player(row, col);
					}
					System.out.println("Black Sizw::::"+ DetectCordinates.blackCordinates.size());
					System.out.println("White Sizw::::"+ DetectCordinates.whiteCordinates.size());

				}
				else
					declareWinner();
			}
		declareWinner();		
	    }
	}
	
	 private void declareWinner() 
	 {

	    	if(checkWiningstate())
	    	{
	    		if(DetectCordinates.blackCordinates.size() > DetectCordinates.whiteCordinates.size())
	    		{
	    			System.out.println("User win with total socre ->" + DetectCordinates.blackCordinates.size());
	    		}
	    		else if(DetectCordinates.blackCordinates.size() < DetectCordinates.blackCordinates.size())
	    		{
	    			System.out.println("Computer win with total socre ->" + DetectCordinates.whiteCordinates.size());
	    		}
	    		else
	    		{
	    			System.out.println("Its a draw");
	    		}
	    	}		
	}

	private boolean checkWiningstate() 
	{
		 boolean flag = true;
		 
		 for (int i=0; i< 8; ++i)
				for (int j=0; j < 8; ++j)
				{
						if(Grid.buttons[i][j].getBackground() == Color.green)
						{
							flag = false;
						}
				}
		
		 return flag;
	}

	/* submitMove
	 * Takes a given move and updates the game board with it.
	 * @param move - the move being made
	 * @param turn - the player the move is being submitted for
	 */
	public void submitMove(Point move, GameState turn) {
		//Make sure the move wasn't submitted by the wrong bot or something
		if ( turn == _settings.getTurn() && move != null ) {
			_settings.getGameBoard().makeMove(move, turn);
//			_boardPane.repaint();
		}//if - parameter checks
		changeTurn();
	}//submitMove(move,turn)
	
	/**
	 * changeTurn
	 * Changes to the opposing player's turn.
	 */
	public void changeTurn() {
		if (_settings.getTurn() == GameState.BLACK) {
			setTurn(GameState.WHITE);
		}
		else {
			setTurn(GameState.BLACK);
		}
	}
	
	/**
	 * setTurn
	 * sets the turn to the given player.
	 * @param turn - Either white or black from GameState. Who's turn it is.
	 */
	private void setTurn(GameState turn) {
		
		//Set the turn in the game settings.
		_settings.setTurn(turn);
		//Update the turn GUI
		
		if (turn == GameState.WHITE) {
//			_lblTurn.setText("Turn: White");
		}
		else {
//			_lblTurn.setText("Turn: Black");
		}
		//Update the score
//		updateScore();
		//repaint the game board.
//		_boardPane.repaint();
	}//setTurn(turn)

	public static void convertColor(ArrayList<String> cordinates) 
	{
		Color setColor = null;
		if(Grid.turn.equals("B"))
			setColor = Color.BLACK;
		else if(Grid.turn.equals("W"))
			setColor = Color.WHITE;
		else if(Grid.algo.equals("VS") && DetectCordinates.yourColor == Color.WHITE)
			setColor = Color.WHITE;
		else if(Grid.algo.equals("VS") && DetectCordinates.yourColor == Color.BLACK)
			setColor = Color.BLACK;
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