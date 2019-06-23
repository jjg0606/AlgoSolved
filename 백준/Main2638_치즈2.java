package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2638_치즈2 {
	final static int[] dr = {1,-1,0,0};
	final static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// init
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int rows = Integer.parseInt(stk.nextToken());
		int cols = Integer.parseInt(stk.nextToken());
		int[][] map = new int[rows][cols];
		for(int i=0;i<rows;i++)
		{
			stk = new StringTokenizer(br.readLine());
			for(int j=0;j<cols;j++)
			{
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// insertion end
		int curHour = -1;
		Queue<Integer[]> airque = new LinkedList<>();
		Queue<Integer[]> landque = new LinkedList<>();
		landque.add(new Integer[] {0,0});
		map[0][0]=-1;
		do
		{
			Queue<Integer[]> temp = airque;
			airque=landque;
			landque=temp;
			
			while(!airque.isEmpty())
			{
				int curR = airque.peek()[0];
				int curC = airque.poll()[1];
				for(int d=0;d<4;d++)
				{
					int nextR = curR+dr[d];
					int nextC = curC+dc[d];
					if(!isInBound(rows, cols, nextR, nextC)||map[nextR][nextC]<0)
					{
						continue;
					}
					if(map[nextR][nextC]==0)
					{
						map[nextR][nextC]=-1;
						airque.add(new Integer[] {nextR,nextC});
					}
					else if(map[nextR][nextC]==1)
					{
						map[nextR][nextC]++;
						
					}
					else
					{
						map[nextR][nextC]=-2;
						landque.add(new Integer[] {nextR,nextC});
					}
				}
			}
			curHour++;	
		}while(!landque.isEmpty());
		System.out.println(curHour);
	}
	
	static boolean isInBound(int rows,int cols,int nextR,int nextC)
	{
		if(nextR<0||nextC<0||nextR>=rows||nextC>=cols)
		{
			return false;
		}		
		return true;
	}
}
