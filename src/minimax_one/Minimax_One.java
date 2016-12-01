package minimax_one;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import minimax_one.Constant.GameState;


public class Minimax_One {
	
	public static final int DEAD_END = Integer.MIN_VALUE/4; 
	public static final int END_GAME_WIN = Integer.MAX_VALUE; 
	public static final int END_GAME_LOSE = Integer.MIN_VALUE;
	public static final int END_GAME_TIE = Integer.MIN_VALUE/2; 
	private static int _maxDepth = 8;
	
	private GameBoard _currentBoard; 
	private GameState _turn;
	private int _value; 
	private ArrayList<Minimax_One> _children; 
	private int _depth; 
	private Point _move;
	
	
	public Minimax_One(GameBoard board, GameState turn) {
		_depth = 0;
		_currentBoard = board.clone();
		_turn = turn;
		_move = null;
		_children = new ArrayList<Minimax_One>();
	}//Constructor
	
	
	private Minimax_One(GameBoard board, GameState turn, int depth, Point move) {
		_currentBoard = board.clone();
		_turn = turn;
		_depth = depth;
		_move = move;
		_children = new ArrayList<Minimax_One>();
	}//Constructor
	
	
	public int negascout() {
		return negascout(END_GAME_LOSE, END_GAME_WIN);
	}//negascout()
	
	
	private int negascout(int alpha, int beta) {
		
	
		int a = alpha;
		int b = beta;
		int score = 0;
		GameState opponent = getOpponent();
		if (_depth > 0) {
			_currentBoard.makeMove(_move, _turn);
		}
		LinkedList<Point> moves = _currentBoard.getAvailableMoves(_turn);
		
		if ( _depth == Minimax_One._maxDepth || moves.isEmpty() ) {
			_value = evaluate();
			return _value;
		}
		Iterator<Point> i = moves.iterator();
		while (i.hasNext()) {
			
			Point curMove = i.next();
			Minimax_One node = new Minimax_One(_currentBoard, opponent, _depth+1, curMove );
			_children.add(node);
			score = -1 * node.negascout(-1*b, -1*a);
			
			if (score > a && b != beta && _depth >= Minimax_One._maxDepth-2 ) {
				score = -1 * node.negascout(-1*beta, -1*score);
			}
			a = max(a,score);
			
			if(a >= beta) {
				_value = a;
				return a;
			}
			b = a+1;
		}
		_value = a;
		return a;
	}
	private int evaluate() {

		GameState opponent = getOpponent();
		LinkedList<Point> moves = _currentBoard.getAvailableMoves(_turn);
		LinkedList<Point> opponentMoves = _currentBoard.getAvailableMoves(opponent);

		if (!opponentMoves.isEmpty() && moves.isEmpty()) {
			_value = DEAD_END;
		}
		else if (opponentMoves.isEmpty() && moves.isEmpty()) { 
			
			int[] score = _currentBoard.getBoardScore();
			
			if ( (score[0] > score[1] && _turn == GameState.BLACK) || (score[0] < score[1] && _turn == GameState.WHITE) ) {
				_value = END_GAME_WIN;
			}
			else if ( (score[0] < score[1] && _turn == GameState.BLACK) || (score[0] > score[1] && _turn == GameState.WHITE) ) {
				_value = END_GAME_LOSE;
			}
			else {
				_value = END_GAME_TIE;
			}
		}
		else {
			
			int[] score = _currentBoard.getBoardScore();
			
			if (_turn == GameState.WHITE) {
				_value = score[1] + (-1 * score[0]);
			}
			else {
				_value = score[0] + (-1 * score[1]);
			}
		}
		return _value;
	}
	private GameState getOpponent() {
		GameState opponent;
		if (_turn == GameState.BLACK)
			opponent = GameState.WHITE;
		else
			opponent = GameState.BLACK;
		return opponent;
	}//getOpponent()
	
	private int max (int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}
	public int getDepth() {
		return _depth;
	}//getDepth()

	public ArrayList<Minimax_One> getChildren() {
		return _children;
	}//getChildren
	
	public Point getPoint() {
		return _move;
	}//getPoint()
	
	public int getValue() {
		return _value;
	}//getValue()
	
	public static void setMaxDepth(int maxDepth) {
		Minimax_One._maxDepth = maxDepth;
	}
	
}//Minimax
