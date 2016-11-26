package greedy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.spi.DirectoryManager;

import mainGUI.Grid;
import mainGUI.XOButton;
import scanning.*;

public class GreedySearch 
{
	

	public static HashMap<String,ArrayList<String>> verticalWhite = new HashMap<>();
	public static HashMap<String,ArrayList<String>> horizontalWhite = new HashMap<>();
	public static HashMap<String,ArrayList<String>> diagonalWhite = new HashMap<>();
	static String horMax, verMax, diaMax, finalMove;
	
	public static void getGreedyPositions()
	{
		GreedySearch.diagonalWhite.clear();
		GreedySearch.horizontalWhite.clear();
		GreedySearch.verticalWhite.clear();
		
		calculateTiles();

		horMax = getmaxHorizontal();
		verMax = getmaxVertical();
		diaMax = getmaxDiagonal();
			finalMove = getMaxScore(horMax, verMax, diaMax);
			placeTile(finalMove);
	}

	


	private static String getmaxDiagonal() 
	{
		int s = 0;
		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : diagonalWhite.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	key = entry.getKey();
		    }
		}
		System.out.println("Dia MAX " +key+","+s);
		return (key+","+s);
		
		
	}

	private static String getmaxVertical() 
	{
		int s = 0;
		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : verticalWhite.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	key = entry.getKey();
		    }
		}
		System.out.println("Ver MAX " +key+","+s);
		return (key+","+s);
	
		
	}

	private static String getmaxHorizontal() 
	{
		int s = 0;
		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : horizontalWhite.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	key = entry.getKey();
		    }
		}
		System.out.println("Hor MAX " +key+","+s);
		return (key+","+s);
		
	
		
	}

	private static void calculateTiles() 
	{
		for(int j = 0 ; j<=7 ; j++)
		{
			for(int i = 0 ; i<=7 ; i++)
			{	
				if((i==0 && j==1))
				{
				System.out.println("Yes");
				}
				if(Grid.buttons[i][j].getBackground() == Color.GREEN)
				{
					DetectCordinates.detectHoizontal("left", i, j);
					DetectCordinates.detectHoizontal("right", i, j);
					DetectCordinates.detectVertical("up", i, j);
					DetectCordinates.detectVertical("down", i, j);
					
					DetectCordinates.detectDiagonal("upLeft", i, j);
					DetectCordinates.detectDiagonal("downLeft", i, j);
					DetectCordinates.detectDiagonal("upRight", i, j);
					DetectCordinates.detectDiagonal("downRight", i, j);
					
				}
			}
		}
		
	}
	private static String getMaxScore(String horMax, String verMax, String diaMax) 
	{
		int horSize = 0, verSize = 0, diaSize = 0, maxSize = 0;
		String max = "";
		if(horMax != null)
		{
			String size[] = horMax.split(",");
			horSize = Integer.parseInt(size[3]);
		}
		if(horMax != null)
		{
			String size1[] = verMax.split(",");
			verSize = Integer.parseInt(size1[3]);
		}
		if(horMax != null)
		{
			String size2[] = diaMax.split(",");
			diaSize = Integer.parseInt(size2[3]);
		}
		if(maxSize < horSize)
		{
			maxSize = horSize;
			max = horMax;
		}
		if(maxSize < verSize)
		{
			maxSize = verSize;
			max = verMax;
		}
		if(maxSize < diaSize)
		{
			maxSize = diaSize;
			max = diaMax;
		}
			return max;
		
	}

	private static void placeTile(String finalMove) 
	{
		int x;
		int y;
		String size[] = finalMove.split(",");
		System.out.println("Final MOve::"+finalMove);
		ArrayList<String> temp = new ArrayList<>();
		
		x = Integer.parseInt(size[0]);
		y = Integer.parseInt(size[1]);
		 
		if(size[2].contains("Hor"))
		{
			temp = (ArrayList<String>) horizontalWhite.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :verticalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(verticalWhite.get(key));
					
				}
			}
			for(String key :diagonalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(diagonalWhite.get(key));
					
				}
			}
		}
		if(size[2].contains("Ver"))
		{
			temp = (ArrayList<String>) verticalWhite.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :horizontalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(horizontalWhite.get(key));
					
				}
			}
			for(String key :diagonalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(diagonalWhite.get(key));
					
				}
			}
		}
		if(size[2].contains("Dia"))
		{
			temp = (ArrayList<String>) diagonalWhite.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :verticalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(verticalWhite.get(key));
					
				}
			}
			for(String key :horizontalWhite.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(horizontalWhite.get(key));
					
				}
			}
		}
		
		XOButton.convertColor(temp);

		temp.clear();
	}
	
}
