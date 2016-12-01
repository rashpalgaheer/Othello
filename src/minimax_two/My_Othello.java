package minimax_two;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Vector;

import mainGUI.Grid;
import minimax_one.GameBoard;
import minimax_one.ReversiBot;
import minimax_one.Constant.GameState;
import minimax_two.MinMax_Agent.MoveCoord;
import scanning.DetectCordinates;


public class My_Othello{
	
	
	public static final int PLAYING = 0;
	public static final int ENDED = 1;

	
	public static final char BLACK = 'b';
	public static final char WHITE = 'w';
	public static final char EMPTY = '-';

	public static final int BOARD_SIZE = 8;
	
	private static final int[] row_offset = {-1, -1, -1,  0,  0,  1,  1,  1};
	
	private static final int[] col_offset = {-1,  0,  1, -1,  1, -1,  0,  1};
	
	private static final char[][] NEW_BOARD = {	{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },	// 1
													{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY },	// 2
													{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY }, // 3
													{ EMPTY, EMPTY, EMPTY, WHITE, BLACK, EMPTY, EMPTY, EMPTY },	// 4
													{ EMPTY, EMPTY, EMPTY, BLACK, WHITE, EMPTY, EMPTY, EMPTY }, // 5
													{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY }, // 6
													{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY }, // 7
													{ EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY }};// 8
													// 		a    		  b    			c    	      d    		 	e    	  	  f    			g    		  h
	
	private boolean bool_isBlack_turn = true;
	private boolean bool_isAI_turn = false;
	private static My_Othello instance;
	public static char[][] mainBoard;
	
	private int mState;
	
	private MinMax_Agent AIAgent;
	
	private int mNewPieceRow;
	private int mNewPieceCol;
	
	private boolean[][] mIsEffectedPiece;
	
	private Vector<String> place_list;
	
	private My_Othello() {
		initiallize();
	}
	
	public static My_Othello getInstance() {
		if (instance == null)
			instance = new My_Othello();
		
		return instance;
	}
	
    private void initiallize() {
		mainBoard = new char[BOARD_SIZE][BOARD_SIZE];
		mIsEffectedPiece = new boolean[BOARD_SIZE][BOARD_SIZE];
		bool_isAI_turn = false;
		place_list = new Vector<String>();
		
		AIAgent = new MinMax_NegaScoutAgent();
	}
    
    public char[][] getBoard() {
    	return mainBoard;
    }
    
    public int getGameState() {
    	return mState;
    }
    
    public void setGameState(int state) {
    	mState = state;
    }
    
    public void setIsCompTurn(boolean value) {
    	bool_isAI_turn = value;
    }
    
    public boolean getIsCompTurn() {
    	return bool_isAI_turn;
    }
    
    public boolean isNewPiece(int row, int col) {
    	return (mNewPieceRow == row && mNewPieceCol == col);
    }
    
    public Vector<String> getMoveList() {
    	return place_list;
    }
    
	public void start_newGame(boolean isBlack) {
		reset_game();		
		resetEffectedPieces();
		bool_isBlack_turn = isBlack;
		bool_isAI_turn = !isBlack;

		mState = PLAYING;
		stateChange();
		
	}
    
	private void reset_game() 
	{
		for (int i=0; i < BOARD_SIZE; ++i)
			for (int j=0; j < BOARD_SIZE; ++j)
				mainBoard[i][j] = NEW_BOARD[i][j];
		
		mNewPieceRow = -1;
		mNewPieceCol = -1;
		
		place_list.removeAllElements();
	}
	
	public void resetEffectedPieces() 
	{
		for (int i=0; i < BOARD_SIZE; ++i)
			for (int j=0; j < BOARD_SIZE; ++j)
				mIsEffectedPiece[i][j] = false;
	}
	
	public void setEffectedPiece(int row, int col) {
		mIsEffectedPiece[row][col] = true;
	}
	
	public boolean isEffectedPiece(int row, int col) {
		return mIsEffectedPiece[row][col];
	}
	
	public void move_player(int row, int col) 
	{
		
		if(!Grid.algo.equals("VS"))
		{
		
		char player_turn = (bool_isBlack_turn) ? BLACK : WHITE;
		
			place_nextMove(mainBoard, player_turn, row, col);
			mNewPieceRow = row;
			mNewPieceCol = col;
			
			keep_to_placeList(row, col, player_turn);
			stateChange();
			
			toggle_turn();
			get_BestMove();
		}
		else
		{
			bool_isBlack_turn = true;
			bool_isAI_turn = true;
			get_BestMove();
		}
	}
    
	
	private void get_BestMove()
	{
		if (!bool_isAI_turn) 
		{
			char piece = (bool_isBlack_turn) ? BLACK : WHITE;
			if ((findValidMove(mainBoard, piece, true)).isEmpty()) {
				char opPiece = (piece == BLACK) ? WHITE : BLACK;
				if ((findValidMove(mainBoard, opPiece, false)).isEmpty())
				{
					mState = ENDED;
					stateChange();	
					return;
				}
				toggle_turn();
				get_BestMove();
			}	
		}
		else
		{
			char curr_player = (bool_isBlack_turn) ? BLACK : WHITE;
			char[][] temp = new char[8][8];
			
			for (int i=0; i< BOARD_SIZE; ++i)
				for (int j=0; j < BOARD_SIZE; ++j)
					temp[i][j] = mainBoard[i][j];
			
			MoveCoord move = AIAgent.find_possibleMove(temp, curr_player);
			
			if (move != null)
			{
				place_nextMove(mainBoard, curr_player, move.getRow(), move.getCol());
				keep_to_placeList(move.getRow(), move.getCol(), curr_player);
				mNewPieceRow = move.getRow();
				mNewPieceCol = move.getCol();
				
				if(Grid.turn == "W" && !Grid.algo.equals("VS"))
				{
					System.out.println("::::::::::::::Point move :::" + move);	
					
					DetectCordinates.yourColor = Color.WHITE;
					DetectCordinates.opponentColor = Color.BLACK;
					
					DetectCordinates.scanningTiles(mNewPieceRow+","+mNewPieceCol);
					System.out.println(DetectCordinates.validate);
					
					if(DetectCordinates.validate > 0)
					{
						Grid.buttons[mNewPieceRow][mNewPieceCol].setBackground(DetectCordinates.yourColor);
						DetectCordinates.whiteCordinates.add(mNewPieceRow+","+mNewPieceCol);
						Grid.turn = "B";
					}
					
					DetectCordinates.cordinates.clear();
					DetectCordinates.filterTileList();
				}
				if(Grid.algo.equals("VS"))
				{
					for (int i=0; i< BOARD_SIZE; ++i)
						for (int j=0; j < BOARD_SIZE; ++j)
						{
								if(mainBoard[i][j] == 'b')
								{
									Grid.buttons[i][j].setBackground(Color.BLACK);
									if(!DetectCordinates.blackCordinates.contains(i+","+j))
									DetectCordinates.blackCordinates.add(i+","+j);
								}
						}
				}
				DetectCordinates.cordinates.clear();
				DetectCordinates.filterTileList();
				stateChange();
			}
			
			toggle_turn();
			get_BestMove();
		}
	}
	
	private void keep_to_placeList(int row, int col, char currPlayer) {
		String a = String.format("%s:\t%s", String.valueOf(currPlayer).toUpperCase(), MoveCoord.encode(row, col));
		place_list.add(a);
	}
	
	private void toggle_turn() {
		bool_isBlack_turn = !bool_isBlack_turn;
		bool_isAI_turn = !bool_isAI_turn;
	}
	
	public static ArrayList<MoveCoord> findValidMove(char[][] board, char piece, boolean isSuggest) {
		
		ArrayList<MoveCoord> moveList = new ArrayList<MoveCoord>();
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j) {
				if (board[i][j] == 'p' || board[i][j] == 'u')
					board[i][j] = EMPTY;
				
				if (isValidMove(board,piece, i, j))
				{
					moveList.add(new MoveCoord(i, j));
				}
			}
		
		return moveList;
	}
	
	public static boolean isValidMove(char[][] board, char piece, int row, int col) {
		if (board[row][col] != EMPTY)
			return false;
		
		char oppPiece = (piece == BLACK) ? WHITE : BLACK;
		
		boolean isValid = false;
		for (int i = 0; i < 8; ++i) {
			int curRow = row + row_offset[i];
			int curCol = col + col_offset[i];
			boolean hasOppPieceBetween = false;
			while (curRow >=0 && curRow < 8 && curCol >= 0 && curCol < 8) {
				
				if (board[curRow][curCol] == oppPiece)
					hasOppPieceBetween = true;
				else if ((board[curRow][curCol] == piece) && hasOppPieceBetween)
				{
					isValid = true;
					break;
				}
				else
					break;
				
				curRow += row_offset[i];
				curCol += col_offset[i];
			}
			if (isValid)
				break;
		}
		
		return isValid;
	}
	
	public static char[][] place_nextMove(char[][] board, char piece, int row, int col) {
		board[row][col] = piece;
		
		My_Othello.getInstance().resetEffectedPieces();
		
		for (int i = 0; i < 8; ++i) 
		{
			boolean is_opponentBetween = false;

			int curr_Row = row + row_offset[i];
			int curr_Col = col + col_offset[i];
			
			while (curr_Row >=0 && curr_Col >= 0 && curr_Row < 8 && curr_Col < 8) 
			{
				
				if (board[curr_Row][curr_Col] == EMPTY)
					break;
				
				if (board[curr_Row][curr_Col] != piece)
					is_opponentBetween = true;
				
				if ((board[curr_Row][curr_Col] == piece) && is_opponentBetween)
				{
					int placeMoveRow = row + row_offset[i];
					int placeMoveCol = col + col_offset[i];
					while (placeMoveRow != curr_Row || placeMoveCol != curr_Col)
					{
						My_Othello.getInstance().setEffectedPiece(placeMoveRow, placeMoveCol);
						board[placeMoveRow][placeMoveCol] = piece;
						
						placeMoveRow += row_offset[i];
						placeMoveCol += col_offset[i];
					}
					break;
				}
				curr_Row += row_offset[i];
				curr_Col += col_offset[i];
			}
		}
		
		for (int i=0; i< BOARD_SIZE; ++i)
			for (int j=0; j < BOARD_SIZE; ++j)
			{
				if(Grid.algo.equals("VS"))
				{
					if(board[i][j] == '-')
					{
						GameBoard._board[i][j] = GameState.EMPTY;
					}
					else if(board[i][j] == 'b')
					{
						GameBoard._board[i][j] = GameState.BLACK;
					}
					else if(board[i][j] == 'w')
					{
						GameBoard._board[i][j] = GameState.WHITE;
					}
				}
			}
		
		return board;
	}
	
	
	private void stateChange() {
	}

}
