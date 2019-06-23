package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1733_오목 {
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		map=new int[19][19];
		for(int r=0;r<19;r++)
		{
			stk = new StringTokenizer(br.readLine());
			for(int c=0;c<19;c++)
			{
				map[r][c] = Integer.parseInt(stk.nextToken());
			}
		}
		// map making
		for(int r=0;r<19;r++)
		{
			for(int c=0;c<19;c++)
			{
				if(map[r][c]==0)
				{
					continue;
				}
				if(c+4<19&&checkHorizen(r, c))
				{
					WinnerIs(map[r][c], r, c);
					return;
				}
				if(r+4<19&&checkVertical(r, c))
				{
					WinnerIs(map[r][c], r, c);
					return;
				}
				if(r+4<19&&c+4<19&&checkVerDRight(r, c))
				{
					WinnerIs(map[r][c], r, c);
					return;
				}
				if(r-4>=0&&c+4<19&&checkVerURight(r, c))
				{
					WinnerIs(map[r][c], r, c);
					return;
				}			
			}
		}
		
		
		System.out.println(0);
	}// main
	
	
	static boolean checkHorizen(int row,int col)
	{
		int curColor = map[row][col];
		
		if(isInRange(row, col-1)&&map[row][col-1]==curColor)
		{
			return false;
		}
		
		int length = 1;
		while(isInRange(row, col+length)&&map[row][col+length]==curColor)
		{
			length++;
			if(length>5)
			{
				return false;
			}
		}
		
		if(length==5)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	static boolean checkVertical(int row,int col)
	{
		int curColor = map[row][col];
		
		if(isInRange(row-1, col)&&map[row-1][col]==curColor)
		{
			return false;
		}
		
		int length = 1;
		while(isInRange(row+length, col)&&map[row+length][col]==curColor)
		{
			length++;
			if(length>5)
			{
				return false;
			}
		}
		
		if(length==5)
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
		
	static boolean checkVerDRight(int row,int col)
	{
		int curColor = map[row][col];
		if(isInRange(row-1, col-1)&&map[row-1][col-1]==curColor)
		{
			return false;
		}
		
		int length = 1;
		while(isInRange(row+length, col+length)&&map[row+length][col+length]==curColor)
		{
			length++;
			if(length>5)
			{
				return false;
			}
		}
		
		if(length==5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	static boolean checkVerURight(int row,int col)
	{
		int curColor = map[row][col];
		if(isInRange(row+1, col-1)&&map[row+1][col-1]==curColor)
		{
			return false;
		}
		
		int length = 1;
		while(isInRange(row-length, col+length)&&map[row-length][col+length]==curColor)
		{
			length++;
			if(length>5)
			{
				return false;
			}
		}
		
		if(length==5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	static boolean isInRange(int row,int col)
	{
		if(row<0||col<0||row>=19||col>=19)
		{
			return false;			
		}
		return true;
	}
	
	static void WinnerIs(int color,int row,int col)
	{
		System.out.println(color);
		System.out.println((row+1)+" "+(col+1));
	}
}
