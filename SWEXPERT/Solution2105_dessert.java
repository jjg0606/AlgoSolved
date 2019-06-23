package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2105_dessert {
	static int[][] map;
	static boolean[] visited=new boolean[101];
	static int dimension;
	static int kind=-1;
	
	
	static void check(int row,int col)
	{
		Arrays.fill(visited, false);
		visited[map[row][col]]=true;
		int rightdownmax=0;
		while(true)
		{
			int nextR=row+rightdownmax+1;
			int nextC=col+rightdownmax+1;
			if(nextR>=dimension||nextC>=dimension)
			{
				break;
			}
			if(visited[map[nextR][nextC]]==true)
			{
				break;
			}
			visited[map[nextR][nextC]]=true;
			rightdownmax++;
		}
		if(rightdownmax==0)
		{
			return;
		}
		
		Arrays.fill(visited, false);
		visited[map[row][col]]=true;
		int leftdownmax=0;
		while(true)
		{
			int nextR=row+leftdownmax+1;
			int nextC=col-leftdownmax-1;
			if(nextR>=dimension||nextC<0)
			{
				break;
			}
			if(visited[map[nextR][nextC]]==true)
			{
				break;
			}
			visited[map[nextR][nextC]]=true;
			leftdownmax++;
		}
		if(leftdownmax==0)
		{
			return;
		}
		if(2*rightdownmax+2*leftdownmax<=kind)
		{
			return;
		}
		for(int i=1;i<=rightdownmax;i++)
		{
			for(int j=1;j<=leftdownmax;j++)
			{
				int p4row = row+i+j;
				if(row+i+j>=dimension)
				{
					break;
				}
				if(2*i+2*j<=kind)
				{
					continue;
				}
				if(!sizecheck(row, col, i, j))
				{
					break;
				}
			}
		}
		
		
	}
	
	static boolean sizecheck(int row,int col,int rd,int ld)
	{
		int answer = 1;
		Arrays.fill(visited, false);
		visited[map[row][col]]=true;
		for(int i=1;i<=rd;i++) // 오른쪽아래
		{
			visited[map[row+i][col+i]]=true;
			answer++;
		}
		
		for(int j=1;j<=ld;j++) //  왼쪽아래
		{
			if(visited[map[row+j][col-j]]==true)
			{
				return false;
			}
			visited[map[row+j][col-j]]=true;
			answer++;
		}
		
		for(int i=1;i<=ld;i++) // 오른쪽 아래 점에서 왼쪽 아래로
		{
			if(visited[map[row+rd+i][col+rd-i]]==true)
			{
				return false;
			}
			visited[map[row+rd+i][col+rd-i]]=true;
			answer++;
		}
		
		for(int i=1;i<rd;i++) // 왼쪽아래점에서 오른쪽아래로
		{
			if(visited[map[row+ld+i][col-ld+i]]==true)
			{
				return true;
			}
			visited[map[row+ld+i][col-ld+i]]=true;
			answer++;
		}
		kind= Math.max(kind, answer);
		return true;
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			dimension = Integer.parseInt(br.readLine().trim());
			map = new int[dimension][dimension];
			for(int i=0;i<dimension;i++)
			{
				stk=new StringTokenizer(br.readLine());
				for(int j=0;j<dimension;j++)
				{
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			//insertion end
			kind = -1;
			Arrays.fill(visited, false);
			// static initialize
			for(int i=0;i<dimension-2;i++)
			{
				for(int j=1;j<dimension-1;j++)
				{
					check(i,j);
				}
			}
			// find
			
			System.out.println("#"+tc+" "+kind);
		}
	}
}
