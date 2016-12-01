package minimax_two;


import java.util.ArrayList;
import java.util.PriorityQueue;
import minimax_two.MinMax_Agent.MoveCoord;
import minimax_two.MinMax_Agent.MoveScore;

public class MinMax_Evaluation 
{
	
	private static int[][] BOARD_VALUE = {
												{100, -1, 5, 2, 2, 5, -1, 100},
												{-1, -10,1, 1, 1, 1,-10, -1},
												{5 , 1,  1, 1, 1, 1,  1,  5},
												{2 , 1,  1, 0, 0, 1,  1,  2},
												{2 , 1,  1, 0, 0, 1,  1,  2},
												{5 , 1,  1, 1, 1, 1,  1,  5},
												{-1,-10, 1, 1, 1, 1,-10, -1},
												{100, -1, 5, 2, 2, 5, -1, 100}
										 };
	
	private static final int BOARD_SIZE = My_Othello.BOARD_SIZE;	

	public static ArrayList<MoveCoord> priorityMoves(char[][] game_board, char curr_player) 
	{
		ArrayList<MoveCoord> to_move_list = My_Othello.findValidMove(game_board, curr_player, false);
		PriorityQueue<MoveScore> to_move_queue = new PriorityQueue<MoveScore>();
		
		for (int i=0; i < to_move_list.size(); ++i) 
		{
			MoveCoord move = to_move_list.get(i);
			MoveScore moveScore = new MoveScore(move, BOARD_VALUE[move.getRow()][move.getCol()]);
			to_move_queue.add(moveScore);
		}
		
		to_move_list = new ArrayList<MoveCoord>();
		
		while (!to_move_queue.isEmpty()) {
			to_move_list.add(to_move_queue.poll().getMove());
		}
		
		return to_move_list;
	}
	
	public static int minMax_evaluate(char[][] game_board, char curr_player, char opp_player) {
		int game_score = 0;
		for (int r = 0; r < BOARD_SIZE; ++r) 
		{
			for (int c = 0; c < BOARD_SIZE; ++c) 
			{
				if (game_board[r][c] == curr_player)
					game_score = game_score + BOARD_VALUE[r][c];
				else if (game_board[r][c] == opp_player)
					game_score = game_score - BOARD_VALUE[r][c];
			}
		}
		return game_score;
	}
	
}
