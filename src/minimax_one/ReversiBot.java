package minimax_one;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import minimax_one.Constant.GameState;

public class ReversiBot {
	
	private GameState _player;
	Minimax_One _rootState;
	
	
	public ReversiBot(GameState player) {
		if(player == GameState.WHITE || player == GameState.BLACK) {
			_player = player;
		}
	}
	
	public Point nextTurn(GameBoard currentBoard) {
		_rootState = new Minimax_One(currentBoard, _player);
		
		
		int score = _rootState.negascout();
		
		
		System.out.println("Chance of Victory: " + score);
		
		return getMove(score, _rootState.getChildren());

	}
	private Point getMove(int move, ArrayList<Minimax_One> children) {
		move *= -1;
		if (children.isEmpty()) {
			return null;
		}
		Iterator<Minimax_One> i = children.iterator();
		Point p = null;

		while (i.hasNext()) {
			Minimax_One node = i.next();
			if (node.getValue() == move ) {
				p = node.getPoint();
				break;
			}
		}
		return p;
	}
	
	public GameState getPlayer() {
		return _player;
	}
	
	public void setDepth(byte depth) {
		Minimax_One.setMaxDepth(depth);
	}
}
