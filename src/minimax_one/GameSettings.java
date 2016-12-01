
package minimax_one;

import minimax_one.Constant.GameState;
import minimax_one.Constant.PlayerTurn;

public class GameSettings {
	
	private byte _difficulty;
	private PlayerTurn _whitePlayer;
	private PlayerTurn _blackPlayer;
	private GameBoard _gameBoard;
	private GameState _turn;
	
	/**
	 * Default Constructor
	 * Initializes with default game settings
	 */
	public GameSettings(){
		_difficulty = 5;
		_whitePlayer = PlayerTurn.COMPUTER;
		_blackPlayer = PlayerTurn.HUMAN;
		_turn = GameState.BLACK;
		_gameBoard = new GameBoard();
	}
	
	/*
	 * Synchronized getters and setters for all data.
	 */
	
	public synchronized GameBoard getGameBoard() {
		return _gameBoard;
	}

	public synchronized void setGameBoard(GameBoard gameBoard) {
		_gameBoard = gameBoard;
	}

	public synchronized GameState getTurn() {
		return _turn;
	}

	public synchronized void setTurn(GameState turn) {
		_turn = turn;
	}
	
	public synchronized byte getDifficulty() {
		return _difficulty;
	}
	public synchronized void setDifficulty(byte difficulty) {
		_difficulty = difficulty;
	}
	public synchronized PlayerTurn getWhitePlayer() {
		return _whitePlayer;
	}
	public synchronized void setWhitePlayer(PlayerTurn whitePlayer) {
		_whitePlayer = whitePlayer;
	}
	public synchronized PlayerTurn getBlackPlayer() {
		return _blackPlayer;
	}
	public synchronized void setBlackPlayer(PlayerTurn blackPlayer) {
		_blackPlayer = blackPlayer;
	}
}//class
