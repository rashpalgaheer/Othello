package minimax_two;

import java.util.ArrayList;

public class MinMax_NegaScoutAgent implements MinMax_Agent{
        
    static final int INFINITY = 1000000;
    
    private int mMaxPly = 5;
	
	@Override
	public MoveCoord find_possibleMove(char[][] board, char piece) {
		return abNegascoutDecision(board, piece);
	}
	
	public MoveCoord abNegascoutDecision(char[][] board, char piece){
    	MoveScore moveScore = abNegascout(board,0,-INFINITY,INFINITY,piece);
    	return moveScore.getMove();
    }
    
    
    public MoveScore abNegascout(char[][] board, int ply, int alpha, int beta, char piece){
    	char oppPiece = (piece == My_Othello.BLACK) ? My_Othello.WHITE : My_Othello.BLACK;
    	
    	if (ply==mMaxPly){
            return new MoveScore(null, MinMax_Evaluation.minMax_evaluate(board, piece, oppPiece));
        }
    		
    	int currentScore;
    	int bestScore = -INFINITY;
    	MoveCoord bestMove = null;
    	int adaptiveBeta = beta; 	
    	
    	ArrayList<MoveCoord> moveList = MinMax_Evaluation.priorityMoves(board, piece);
    	if (moveList.isEmpty())
    		return new MoveScore(null, bestScore);
    	bestMove = moveList.get(0);
    	
    	for(int i=0;i<moveList.size();i++){
    		MoveCoord move = moveList.get(i);
    		char[][] newBoard = new char[8][8];
    		for (int r = 0; r < 8; ++r)
    			for (int c=0; c < 8; ++c)
    				newBoard[r][c] = board[r][c];
    		My_Othello.place_nextMove(newBoard, piece, move.getRow(), move.getCol());
    		
    		MoveScore current = abNegascout(newBoard, ply+1, -adaptiveBeta, - Math.max(alpha,bestScore), oppPiece);
    		
    		currentScore = - current.getScore();
    		
    		if (currentScore>bestScore){
    			if (adaptiveBeta == beta || ply>=(mMaxPly-2)){
    				bestScore = currentScore;
					bestMove = move;
    			}else
    			{ 
    				current = abNegascout(newBoard, ply+1, -beta, -currentScore, oppPiece);
    				bestScore = - current.getScore();
    				bestMove = move;
    			}
    			
        		if(bestScore>=beta){
        			return new MoveScore(bestMove,bestScore);
        		}
        		
        		adaptiveBeta = Math.max(alpha, bestScore) + 1;
    		}
    	}
    	return new MoveScore(bestMove,bestScore);
    }
}
