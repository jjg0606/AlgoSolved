package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1949_D4_등산로 {
	static int[] dr= {0,0,1,-1};
	static int[] dc= {1,-1,0,0};
	
	static int answer=0;
	static int dimension=0;
	static int drilling = 0;
	static int[][] map;
	
	static void find(int R)
	{
		int row = R/dimension;
		int col = R%dimension;
		findhaved(row, col, 1);
		
	}
	
	static void findhaved(int row,int col,int length)
	{
		int curH = map[row][col];
		map[row][col]=999;
		for(int d=0;d<4;d++)
		{
			int nextR = row + dr[d];
			int nextC = col + dc[d];
			
			if(nextR<0||nextR>=dimension||nextC<0||nextC>=dimension)
			{
				continue;
			}
			if(map[nextR][nextC]>=curH)
			{
				int before = map[nextR][nextC];
				if(before-drilling<curH)
				{
					map[nextR][nextC] = curH-1;
					findnope(nextR,nextC,length+1);
					map[nextR][nextC]=before;
				}
			}
			else
			{
				findhaved(nextR,nextC,length+1);
			}
		}
		map[row][col]=curH;
		answer = Math.max(answer, length);
	}
	
	static void findnope(int row,int col,int length)
	{
		int curH = map[row][col];
		map[row][col]=999;
		for(int d=0;d<4;d++)
		{
			int nextR = row + dr[d];
			int nextC = col + dc[d];
			
			if(nextR<0||nextR>=dimension||nextC<0||nextC>=dimension)
			{
				continue;
			}
			if(map[nextR][nextC]<curH)
			{
				findnope(nextR,nextC,length+1);
			}
		}
		map[row][col]=curH;
		answer = Math.max(answer, length);
	}
	
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk=new StringTokenizer(br.readLine());
			dimension=Integer.parseInt(stk.nextToken());
			drilling = Integer.parseInt(stk.nextToken());
			map = new int[dimension][dimension];
			ArrayList<Integer> peeks = new ArrayList<>();
			int peekh = 0;
			for(int i=0;i<dimension;i++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int j=0;j<dimension;j++)
				{
					int read = Integer.parseInt(stk.nextToken());
					map[i][j]=read;
					if(read>peekh)
					{
						peekh=read;
						peeks.clear();
						peeks.add(i*dimension+j);
					}
					else if(read==peekh)
					{
						peeks.add(i*dimension+j);
					}					
				}
			}
			//insertion end
			answer = 0;
			//
			for(int i=0;i<peeks.size();i++)
			{
				find(peeks.get(i));
			}//for loop
			System.out.println("#"+tc+" "+answer);
		}// tc loop
	}
}

