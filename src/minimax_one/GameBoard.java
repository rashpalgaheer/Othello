
package minimax_one;

import java.awt.Point;
import java.util.LinkedList;

import mainGUI.Grid;
import minimax_one.Constant.Direction;
import minimax_one.Constant.GameState;
import minimax_two.My_Othello;

public class GameBoard {
	
	public static GameState[][] _board;

	public GameBoard() 
	{
		initialize();
	}
	
	public GameBoard(GameState[][] game_board) 
	{
		if(game_board.length == 8 && game_board[0].length == 8)
			_board = game_board;
		else
			initialize();
	}
	
	private void initialize() 
	{
		_board = new GameState[8][8];

		for (int x = 0; x < _board.length; x++) 
		{
			for (int y = 0; y < _board[x].length; y++) 
			{
				_board[x][y] = GameState.EMPTY;
			}
		}
		_board[3][4] = GameState.BLACK;
		_board[4][3] = GameState.BLACK;
		_board[3][3] = GameState.WHITE;
		_board[4][4] = GameState.WHITE;
		
		
		
	}
	

	public GameState[][] getGameBoard() 
	{
		return _board;
	}

	public LinkedList<Point> getAvailableMoves(GameState turn) 
	{
		LinkedList<Point> moves = new LinkedList<Point>();
		
		for (int x = 0 ; x < _board.length ; x++)
		{
			for (int y = 0; y < _board[0].length ; y++) 
			{
				Point p = new Point(x,y);
				if (checkMove(p,turn)) 
				{
					moves.add(p);
				}
			}
		}
		
		return moves;
	}
	
	public boolean checkMove(Point move, GameState turn) 
	{
		
		boolean valid = false;
		if(checkMove(move,turn,Direction.Up)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.UpRight)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.Right)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.DownRight)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.Down)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.DownLeft)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.Left)) {
			valid = true;
		}
		else if(checkMove(move,turn,Direction.UpLeft)) {
			valid = true;
		}
		
		return valid;
	}
	
	private boolean checkMove(Point move, GameState turn, Direction direction) 
	{
		
		boolean valid = false; 
		short game_state = 0; 
		Point position = (Point)move.clone();
		
		
		while (position.x >= 0 && position.x < 8 && position.y >=0 && position.y < 8) 
		{
			
			if ( game_state == 0 && _board[position.x][position.y] == GameState.EMPTY) 
			{
				game_state = 1;
				position = moveCell(position, direction);
			}

			else if (game_state == 1 && _board[position.x][position.y] != turn && _board[position.x][position.y] != GameState.EMPTY ) 
			{
				game_state = 2;
				position = moveCell(position, direction);
			}

			else if (game_state == 2 && _board[position.x][position.y] != GameState.EMPTY) 
			{
				if (_board[position.x][position.y] != turn ) 
				{
					position = moveCell(position, direction);
				}
				else if (_board[position.x][position.y] == turn) 
				{
					valid = true;
					break;
				}
				else {
					break;
				}
			}
			else {
				break;
			}
			
		}
		
		return valid;
	}

	public int makeMove(Point move, GameState turn) 
	{
		int tilesFlipped = 0;

		tilesFlipped += makeFinalMove(move,turn,Direction.Up);
		tilesFlipped += makeFinalMove(move,turn,Direction.UpRight);
		tilesFlipped += makeFinalMove(move,turn,Direction.Right);
		tilesFlipped += makeFinalMove(move,turn,Direction.DownRight);
		tilesFlipped += makeFinalMove(move,turn,Direction.Down);
		tilesFlipped += makeFinalMove(move,turn,Direction.DownLeft);
		tilesFlipped += makeFinalMove(move,turn,Direction.Left);
		tilesFlipped += makeFinalMove(move,turn,Direction.UpLeft);
		
		if ( tilesFlipped > 0 ) 
		{
			_board[move.x][move.y] = turn;
			
			if(My_Othello.mainBoard != null && Grid.algo == "VS")
			{
				My_Othello.mainBoard[move.x][move.y] = 'w';
			}
			
			tilesFlipped++;
		}
		
		return tilesFlipped;
	}
	
	private int makeFinalMove(Point move, GameState turn, Direction direction) 
	{
		Point position = new Point(move.x,move.y);
		
		if (!checkMove(position,turn,direction)) {
			return 0;
		}
		
		int score = 0;
		position = moveCell(position, direction);
		
		while (position.x >= 0 && position.x < 8 && position.y >=0 && position.y < 8) { 
			
			if (_board[position.x][position.y] == turn) {
				return score;
			}
			
			_board[position.x][position.y] = turn;
			
			if(My_Othello.mainBoard != null && Grid.algo == "VS")
			{
				My_Othello.mainBoard[position.x][position.y] = 'w';
			}
			
			score++;
			position = moveCell(position, direction);
			
		}
		return score;
	}
	
	private Point moveCell(Point p, Direction direction) 
	{
		int x = p.x;
		int y = p.y;
		
		switch (direction) {
		case Up:
			y--;
			break;
		case UpRight:
			y--;
			x++;
			break;
		case Right:
			x++;
			break;
		case DownRight:
			y++;
			x++;
			break;
		case Down:
			y++;
			break;
		case DownLeft:
			y++;
			x--;
			break;
		case Left:
			x--;
			break;
		case UpLeft:
			y--;
			x--;
			break;
		}
		
		return new Point(x,y);
	}
	
	public int[] getBoardScore() 
	{
		int blackScore = 0;
		int whiteScore = 0;
		
		for (int y = 0; y < 8; y ++ ) {
    		for (int x = 0; x < 8; x++) {
    			if (_board[x][y] == GameState.BLACK) {
    				blackScore++;
    			}
    			else if (_board[x][y] == GameState.WHITE) {
    				whiteScore++;
    			}
    		}
    	}
		
		return new int[] {blackScore, whiteScore};
	}
	
	public GameState getCellState(int x, int y) {
		return _board[x][y];
	}
	
	public GameBoard clone() {
		
		GameState newBoard[][] = new GameState[_board.length][_board[0].length];
		
		for(int x = 0; x < _board.length ; x++) {
			for(int y = 0; y < _board[0].length; y++) {
				newBoard[x][y] = _board[x][y];
			}
		}
		
		return new GameBoard(newBoard);
	}
	
}
