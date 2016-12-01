package greedy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import mainGUI.Grid;
import mainGUI.XOButton;
import scanning.DetectCordinates;

public class GreedySearchKaran 
{
	static String MOVE, defalutValue = "0,0,0";;
	
	public static HashMap<String,ArrayList<String>> storeWhiteHORIZONTAL = new HashMap<>();
	public static HashMap<String,ArrayList<String>> storeWhiteDIAGONAL = new HashMap<>();
	public static HashMap<String,ArrayList<String>> storeWhiteVERTICAL = new HashMap<>();
	
	
	public static void getGreedyPositions()
	{
		storeWhiteHORIZONTAL.clear();
		storeWhiteDIAGONAL.clear();
		storeWhiteVERTICAL.clear();
		
		calculateTiles();

			MOVE = getFinalMOVE(getmaxHorMOVE(), getmaxVerMOVE(), getmaxDiaMOVE());
			placeTile(MOVE);
	}

	
	private static String getFinalMOVE(String horMOVE, String verMOVE, String diaMOVE) 
	{
		int horSize = 0, verSize = 0, diaSize = 0, maxSize = 0;
		String max = "";
		if(horMOVE != null)
		{
			String size[] = horMOVE.split(",");
			horSize = Integer.parseInt(size[3]);
		}
		if(horMOVE != null)
		{
			String size1[] = verMOVE.split(",");
			verSize = Integer.parseInt(size1[3]);
		}
		if(horMOVE != null)
		{
			String size2[] = diaMOVE.split(",");
			diaSize = Integer.parseInt(size2[3]);
		}
		if(maxSize < horSize)
		{
			maxSize = horSize;
			max = horMOVE;
		}
		if(maxSize < verSize)
		{
			maxSize = verSize;
			max = verMOVE;
		}
		if(maxSize < diaSize)
		{
			maxSize = diaSize;
			max = diaMOVE;
		}
			return max;
		
	}


	private static String getmaxDiaMOVE() 
	{
		int s = 0;
//		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : storeWhiteDIAGONAL.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	defalutValue = entry.getKey();
		    }
		}
		System.out.println("Dia MAX " +defalutValue+","+s);
		return (defalutValue+","+s);
		
		
	}

	private static String getmaxVerMOVE() 
	{
		int s = 0;
//		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : storeWhiteVERTICAL.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	defalutValue = entry.getKey();
		    }
		}
		System.out.println("Ver MAX " +defalutValue+","+s);
		return (defalutValue+","+s);
	
		
	}

	private static String getmaxHorMOVE() 
	{
		int s = 0;
		String key = "0,0,0";

		for ( Entry<String, ArrayList<String>> entry : storeWhiteHORIZONTAL.entrySet()) 
		{
		    int size = entry.getValue().size();
		    if(size > s)
		    {
		    	s = size;
		    	defalutValue = entry.getKey();
		    }
		}
		System.out.println("Hor MAX " +defalutValue+","+s);
		return (defalutValue+","+s);
		
	
		
	}

	private static void calculateTiles() 
	{
		for(int y = 0 ; y<=7 ; y++)
		{
			for(int x = 0 ; x<=7 ; x++)
			{	
				if(Grid.buttons[x][y].getBackground() == Color.GREEN)
				{
					DetectCordinates.detectHoizontal("left", x, y);
					DetectCordinates.detectHoizontal("right", x, y);
					DetectCordinates.detectVertical("up", x, y);
					DetectCordinates.detectVertical("down", x, y);
					
					DetectCordinates.detectDiagonal("upLeft", x, y);
					DetectCordinates.detectDiagonal("downLeft", x, y);
					DetectCordinates.detectDiagonal("upRight", x, y);
					DetectCordinates.detectDiagonal("downRight", x, y);
					
				}
			}
		}
		
	}
	
	private static void placeTile(String finalMove) 
	{
		int x;
		int y;
		String size[] = finalMove.split(",");
		System.out.println("Final MOVE::"+finalMove);
		ArrayList<String> temp = new ArrayList<>();
		
		x = Integer.parseInt(size[0]);
		y = Integer.parseInt(size[1]);
		 
		
		if(size[2].contains("Ver"))
		{
			temp = (ArrayList<String>) storeWhiteVERTICAL.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :storeWhiteHORIZONTAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(storeWhiteHORIZONTAL.get(key));
					
				}
			}
			for(String key :storeWhiteDIAGONAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(storeWhiteDIAGONAL.get(key));
					
				}
			}
		}
		if(size[2].contains("Hor"))
		{
			temp = (ArrayList<String>) storeWhiteHORIZONTAL.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :storeWhiteVERTICAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(storeWhiteVERTICAL.get(key));
					
				}
			}
			for(String key :storeWhiteDIAGONAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(storeWhiteDIAGONAL.get(key));
					
				}
			}
		}
		if(size[2].contains("Dia"))
		{
			temp = (ArrayList<String>) storeWhiteDIAGONAL.get(x+","+y+","+size[2]).clone();
			temp.add(x+","+y);
			for(String key :storeWhiteVERTICAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					temp.addAll(storeWhiteVERTICAL.get(key));
					
				}
			}
			for(String key :storeWhiteHORIZONTAL.keySet() )
			{
				if(key.contains(x+","+y))
				{
					System.out.println(key);
					temp.addAll(storeWhiteHORIZONTAL.get(key));
					
				}
			}
		}
		
		XOButton.convertColor(temp);

		temp.clear();
	}
	


}
