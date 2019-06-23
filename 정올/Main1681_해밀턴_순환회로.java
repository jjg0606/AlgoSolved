package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1681_해밀턴_순환회로 {
	static int[][] map;
	static int NodeNum;
	static boolean[] visited;
	static int minvalue = Integer.MAX_VALUE;
	
	static void findMin(int cur, int length, int value)
	{
		if(value>=minvalue)
		{
			return;
		}
		if(length==NodeNum-1)
		{
			if(map[cur][0]==0)
			{
				return;
			}
			minvalue=Math.min(minvalue, value+map[cur][0]);
			return;
		}
		for(int i=1;i<NodeNum;i++)
		{
			if(visited[i]==true||map[cur][i]==0)
			{
				continue;
			}
			visited[i]=true;
			findMin(i, length+1, value+map[cur][i]);
			visited[i]=false;			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		NodeNum = Integer.parseInt(br.readLine().trim());
		map = new int[NodeNum][NodeNum];
		
		StringTokenizer stk;
		for(int r=0;r<NodeNum;r++)
		{
			stk=new StringTokenizer(br.readLine());
			for(int c=0;c<NodeNum;c++)
			{
				map[r][c] = Integer.parseInt(stk.nextToken());
			}
		}
		// insertion end
		visited = new boolean[NodeNum];
		// static initialize
		visited[0]=true;
		findMin(0, 0, 0);
		System.out.println(minvalue);
		
	}
}
