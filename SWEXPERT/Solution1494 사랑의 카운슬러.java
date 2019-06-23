package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1494 {
	static int N;
	static int[] visited;
	static Worm[] wormarr;
	static long minsum;
	
	static class Worm
	{
		int x;
		int y;
		
		public Worm(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	static void reChoice(int cur,int last)
	{
		if(cur==N/2)
		{
			long xsum=0;
			long ysum=0;
			for(int i=0;i<N;i++)
			{
				if(visited[i]==1)
				{
					xsum+=wormarr[i].x;
					ysum+=wormarr[i].y;
				}
				else
				{
					xsum-=wormarr[i].x;
					ysum-=wormarr[i].y;					
				}
			}
			long distance = xsum*xsum+ysum*ysum;
			minsum=Math.min(minsum, distance);
			return;
		}
		
		for(int i=last+1;i<N/2+cur;i++)
		{
			visited[i] = 1;
			reChoice(cur+1, i);
			visited[i] = 0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			N = Integer.parseInt(br.readLine().trim());
			wormarr = new Worm[N];
			minsum = Long.MAX_VALUE;
			visited = new int[N];
			for(int i=0;i<N;i++)
			{
				stk = new StringTokenizer(br.readLine().trim());
				wormarr[i] = new Worm(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
			}
			//insertion & static initialize end
			reChoice(0, -1);
			
			
			
			System.out.println("#"+tc+" "+minsum);
		}//tc loop
	}
}
