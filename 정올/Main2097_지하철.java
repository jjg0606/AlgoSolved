package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2097_지하철 {	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int Dest = Integer.parseInt(stk.nextToken());
		int[][] map = new int[N][N];
		for(int r=0;r<N;r++)
		{
			stk = new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++)
			{
				map[r][c] = Integer.parseInt(stk.nextToken());
			}
		}
		// insertion end
		int[] length = new int[N];
		LinkedList<Integer>[] way = new LinkedList[N];
		for(int i=1;i<N;i++)
		{
			way[i] = new LinkedList<Integer>();
			way[i].add(i+1);
		}
		int[] visited = new int[N];
		visited[0]=1;
		for(int i=1;i<N;i++)
		{
			length[i]=map[0][i];
		}
		for(int i=0;i<N-1;i++)
		{
			int min = Integer.MAX_VALUE;
			int mindex = 0;
			for(int j=1;j<N;j++)
			{
				if(visited[j]==1)
				{
					continue;
				}
				if(length[j]<min)
				{
					min=length[j];
					mindex=j;
				}
			}// 최소를 찾음
			
			visited[mindex]=1;
			for(int j=1;j<N;j++)
			{
				if(j==mindex)
				{
					continue;
				}
				if(min+map[mindex][j]<length[j])
				{
					length[j] = min+map[mindex][j];
					way[j].clear();
					for (int path : way[mindex]) {
						way[j].addLast(path);
					}
					way[j].addLast(j+1);
				}
			}			
		}
		
		System.out.println(length[Dest-1]);
		StringBuilder sb = new StringBuilder();
		sb.append("1 ");
		while(!way[Dest-1].isEmpty())
		{
			sb.append(way[Dest-1].pollFirst());
			sb.append(" ");
		}
		System.out.println(sb.toString().trim());
	}//main
}
