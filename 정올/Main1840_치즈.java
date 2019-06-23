package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1840 {
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int Rows = Integer.parseInt(stk.nextToken());
		int Cols = Integer.parseInt(stk.nextToken());
		int[][] map = new int[Rows][Cols];
		int[][] melted=new int[Rows][Cols];
		int cnt = 0;
		for(int i=0;i<Rows;i++)
		{
			stk=new StringTokenizer(br.readLine());
			for(int j=0;j<Cols;j++)
			{
				map[i][j] = Integer.parseInt(stk.nextToken()); 
			}
		}//for loop
		//insertion end
		
		
		ArrayList<Integer> que = new ArrayList<>();
		ArrayList<Integer> nextque = new ArrayList<>();		
		int days = 0;
		nextque.add(0);
		melted[0][0]=1;
		do
		{
			cnt = nextque.size();
			ArrayList<Integer> temp = que;
			que=nextque;
			nextque = temp;
			
			days++;			
			while(!que.isEmpty())
			{
				int curR = que.get(0)/Cols;
				int curC = que.remove(0)%Cols;
				for(int d=0;d<4;d++)
				{
					int nextR = curR+dr[d];
					int nextC = curC+dc[d];
					if(nextR<0||nextR>=Rows||nextC<0||nextC>=Cols)
					{
						continue;
					}
					if(map[nextR][nextC]==0&&melted[nextR][nextC]==0)
					{
						que.add(nextR*Cols+nextC);
					}
					else if(map[nextR][nextC]==1&&melted[nextR][nextC]==0)
					{
						nextque.add(nextR*Cols+nextC);
					}
					melted[nextR][nextC]=days;
				}
			}			
		}while(!nextque.isEmpty());
		days--;
		System.out.println(days+"\n"+cnt);		
	}//main
}
