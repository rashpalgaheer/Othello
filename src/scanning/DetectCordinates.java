package scanning;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import greedy.GreedySearch;
import mainGUI.*;
public class DetectCordinates 
{
	static int xCord = 0;
	static int yCord = 0;
	static int xDir = 0;
	static int yDir = 0;
	static String direction;
	public static Color yourColor;
	public static Color opponentColor;
	public static int validate = 0;
	public static ArrayList<String> cordinates = new ArrayList<>();
	public static ArrayList<String> whiteCordinates = new ArrayList<>();
	public static ArrayList<String> blackCordinates = new ArrayList<>();

	public static void scanningTiles(String pos) 
	{
		String xypos[] = pos.split(",");
		xCord = Integer.parseInt(xypos[0]);
		yCord = Integer.parseInt(xypos[1]);
		validate = 0;

		detectHoizontal("left", xCord, yCord);
		detectHoizontal("right", xCord, yCord);
		detectVertical("up", xCord, yCord);
		detectVertical("down", xCord, yCord);
		detectDiagonal("upRight", xCord, yCord);
		detectDiagonal("upLeft", xCord, yCord);
		detectDiagonal("downRight", xCord, yCord);
		detectDiagonal("downLeft", xCord, yCord);
		
	}

	

	public static void detectDiagonal(String direction, int xCord, int yCord) 
	{
		int nextX, nextY;

		boolean convert = false;
		if(direction .equals("upLeft"))
		{
			xDir = 0;
			yDir = 0;
			nextY = yCord - 1;
			if(xCord != 0 && yCord != 0 && Grid.buttons[xCord - 1][yCord - 1].getBackground() == opponentColor )
			{
				for (int i = xCord-1 ; i >= xDir  ; i--)
				{
					
					if(Grid.buttons[i][nextY].getBackground() == opponentColor)
					{
						cordinates.add(i+","+nextY);
						convert = true;
					}
					if(Grid.buttons[i][nextY].getBackground() == yourColor)
					{
						break;
					}
					if(i == xDir && Grid.buttons[i][nextY].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[i][nextY].getBackground() == Color.GREEN || nextY==0)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					nextY--;
				}
			}
			if(convert)
			{
				if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
				{
					ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
					GreedySearch.diagonalWhite.put(xCord+","+yCord+",DiaupLeft", temp);
					cordinates.clear();
				}
			}
		}
		if(direction .equals("downLeft"))
		{
			xDir = 0;
			yDir = 0;
			nextY = yCord + 1;
			if(xCord != 0 && yCord != 7 && Grid.buttons[xCord - 1][yCord + 1].getBackground() == opponentColor )
			{
				for (int i = xCord-1 ; i >= xDir ; i--)
				{
					
					if(Grid.buttons[i][nextY].getBackground() == opponentColor)
					{
						cordinates.add(i+","+nextY);
						convert = true;
					}
					if(Grid.buttons[i][nextY].getBackground() == yourColor)
					{
						break;
					}
					if(i == xDir && Grid.buttons[i][nextY].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[i][nextY].getBackground() == Color.GREEN  || nextY==7)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					nextY++;
				}
			}
			if(convert)
			{
				if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
				{
					ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
					GreedySearch.diagonalWhite.put(xCord+","+yCord+",DiadownLeft", temp);
					cordinates.clear();
				}
			}
		}
		if(direction .equals("upRight"))
		{
			xDir = 7;
			yDir = 0;
			nextY = yCord - 1;
			if(xCord != 7 && yCord != 0 && Grid.buttons[xCord + 1][yCord - 1].getBackground() == opponentColor)
			{
				for (int i = xCord + 1 ; i <= xDir; i++)
				{
					
					if(Grid.buttons[i][nextY].getBackground() == opponentColor)
					{
						cordinates.add(i+","+nextY);
						convert = true;
					}
					if(Grid.buttons[i][nextY].getBackground() == yourColor)
					{
						break;
					}
					if(i == xDir && Grid.buttons[i][nextY].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[i][nextY].getBackground() == Color.GREEN || nextY==0 )
					{
						convert = false;
						cordinates.clear();
						break;
					}
					nextY--;
				}
			}
			if(convert)
			{
				if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
				{
					ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
					GreedySearch.diagonalWhite.put(xCord+","+yCord+",DiaupRight", temp);
					cordinates.clear();
				}
			}
		}
		if(direction .equals("downRight"))
		{
			xDir = 7;
			yDir = 0;
			nextY = yCord + 1;
			if(xCord != 7 && yCord != 7 && Grid.buttons[xCord + 1][yCord + 1].getBackground() == opponentColor )
			{
				for (int i = xCord + 1 ; i <= xDir; i++)
				{
					
					if(Grid.buttons[i][nextY].getBackground() == opponentColor)
					{
						cordinates.add(i+","+nextY);
						convert = true;
					}
					if(Grid.buttons[i][nextY].getBackground() == yourColor)
					{
						break;
					}
					if(i == xDir && Grid.buttons[i][nextY].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[i][nextY].getBackground() == Color.GREEN || nextY==7 )
					{
						convert = false;
						cordinates.clear();
						break;
					}
					nextY++;
				}
				if(convert)
				{
					if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
					{
						ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
						GreedySearch.diagonalWhite.put(xCord+","+yCord+",DiadownRight", temp);
						cordinates.clear();
					}
				}
			}
		}
			
			if(convert)
			{
				XOButton.convertColor(cordinates);
				if(yourColor == Color.BLACK)
				{
					blackCordinates.addAll(cordinates);
				}
				else if(yourColor == Color.WHITE && Grid.algo.equals("2Player"))
				{
					whiteCordinates.addAll(cordinates);
				}
				validate ++;
				convert = false;
			}
		
		
	}

	public static void detectHoizontal(String direction, int xCord, int yCord) 
	{
		boolean convert = false;
		if(direction .equals("left"))
		{
			xDir = 0;
			if(xCord != 0 && Grid.buttons[xCord - 1][yCord].getBackground() == opponentColor )
			{
				for (int i = xCord-1 ; i >= xDir ; i--)
				{
					
					if(Grid.buttons[i][yCord].getBackground() == opponentColor)
					{
							cordinates.add(i+","+yCord);
							convert = true;
					}
					if(Grid.buttons[i][yCord].getBackground() == yourColor)
					{
						break;
					}
					if(i == xDir && Grid.buttons[i][yCord].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[i][yCord].getBackground() == Color.GREEN)
					{
						convert = false;
						cordinates.clear();
						break;
					}
				}
			}
			if(convert)
			{
				if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
				{
					ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
					GreedySearch.horizontalWhite.put(xCord+","+yCord+",HorLeft", temp);
					cordinates.clear();
				}
			}
		}
		
		if(direction .equals("right"))
		{
				xDir = 7;
				if(xCord != 7 && Grid.buttons[xCord + 1][yCord].getBackground() == opponentColor )
				{
					for (int i = xCord+1 ; i <= xDir ; i++)
					{
						
						if(Grid.buttons[i][yCord].getBackground() == opponentColor)
						{
							cordinates.add(i+","+yCord);
							convert = true;
						}
						if(Grid.buttons[i][yCord].getBackground() == yourColor)
						{
							break;
						}
						if(i == xDir && Grid.buttons[i][yCord].getBackground() != yourColor)
						{
							convert = false;
							cordinates.clear();
							break;
						}
						if(Grid.buttons[i][yCord].getBackground() == Color.GREEN)
						{
							convert = false;
							cordinates.clear();
							break;
						}
						
					}
				}
				if(convert)
				{
					if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
					{
						ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
						
						GreedySearch.horizontalWhite.put(xCord+","+yCord+",HorRight", temp);
						cordinates.clear();
					}
				}
			}
			
			if(convert)
			{
				
				
				if(yourColor == Color.BLACK && Grid.turn.equals("B"))
				{
					blackCordinates.addAll(cordinates);
					XOButton.convertColor(cordinates);
				}
				else if(yourColor == Color.WHITE && Grid.algo.equals("2Player"))
				{
					whiteCordinates.addAll(cordinates);
					XOButton.convertColor(cordinates);
				}
				
				validate ++;
				convert = false;
			}
	}

	public static void detectVertical(String direction, int xCord, int yCord) 
	{

		boolean convert = false;
		if(direction .equals("up"))
		{
			yDir = 0;
			if(yCord != 0 && Grid.buttons[xCord][yCord - 1].getBackground() == opponentColor )
			{
				for (int i = yCord-1 ; i >= yDir ; i--)
				{
					if(Grid.buttons[xCord][i].getBackground() == opponentColor)
					{
						cordinates.add(xCord+","+i);
						convert = true;
					}
					if(Grid.buttons[xCord][i].getBackground() == yourColor)
					{
						break;
					}
					if(i == yDir && Grid.buttons[xCord][i].getBackground() != yourColor)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					if(Grid.buttons[xCord][i].getBackground() == Color.GREEN)
					{
						convert = false;
						cordinates.clear();
						break;
					}
					
				}
			}
			if(convert)
			{
				if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
				{
					
					ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
					GreedySearch.verticalWhite.put(xCord+","+yCord+",VerUp", temp);
					cordinates.clear();
				}
			}
		}
			if(direction .equals("down"))
			{
				yDir = 7;
				if(yCord != 7 && Grid.buttons[xCord][yCord + 1].getBackground() == opponentColor )
				{
					for (int i = yCord+1 ; i <= yDir ; i++)
					{
						
						if(Grid.buttons[xCord][i].getBackground() == opponentColor)
						{
							cordinates.add(xCord+","+i);
							convert = true;
						}
						if(Grid.buttons[xCord][i].getBackground() == yourColor)
						{
							break;
						}
						if(i == yDir && Grid.buttons[xCord][i].getBackground() != yourColor)
						{
							convert = false;
							cordinates.clear();
							break;
						}
						if(Grid.buttons[xCord][i].getBackground() == Color.GREEN)
						{
							convert = false;
							cordinates.clear();
							break;
						}
						
					}
				}
				if(convert)
				{
					if(yourColor == Color.WHITE && Grid.turn.equals("W") && Grid.algo.equals("greedy"))
					{
						ArrayList<String> temp = (ArrayList<String>)cordinates.clone();
						GreedySearch.verticalWhite.put(xCord+","+yCord+",VerDown", temp);
						cordinates.clear();
						
					}
				}
			}
			
			if(convert)
			{
				XOButton.convertColor(cordinates);
				if(yourColor == Color.BLACK)
				{
					blackCordinates.addAll(cordinates);
				}
				else if(yourColor == Color.WHITE && Grid.algo.equals("2Player"))
				{
					whiteCordinates.addAll(cordinates);
				}
				validate ++;
				convert = false;
			}
		
	}


	public static void filterTileList() 
	{
		for (Iterator<String> iterator = whiteCordinates.iterator(); iterator.hasNext(); ) 
		{
		    String cord = iterator.next();
		    String xypos[] = cord.split(",");
			int xCord = Integer.parseInt(xypos[0]);
			int yCord = Integer.parseInt(xypos[1]);
			if(Grid.buttons[xCord][yCord].getBackground() != Color.WHITE)
			{
				iterator.remove();
			}
		}
		
		for (Iterator<String> iterator = blackCordinates.iterator(); iterator.hasNext(); ) 
		{
		    String cord = iterator.next();
		    String xypos[] = cord.split(",");
			int xCord = Integer.parseInt(xypos[0]);
			int yCord = Integer.parseInt(xypos[1]);
			if(Grid.buttons[xCord][yCord].getBackground() != Color.BLACK)
			{
				iterator.remove();
			}
		}
		
	}
}
