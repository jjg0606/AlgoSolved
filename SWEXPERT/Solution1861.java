package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1861 {
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			int N = Integer.parseInt(br.readLine().trim());
			int[][] map = new int[N][N];
			for(int i=0;i<N;i++)
			{
				stk = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++)
				{
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			// insertion end
			int maxcount = 0;
			int maxNum = 0;
			int[][] visited = new int[N][N];
			ArrayList<Integer> que = new ArrayList<>(); 
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
				{
					if(visited[i][j]!=0) // 이미 탐색 된 방
					{
						continue;
					}
					
					int curNum = map[i][j];
					int count = 0;
					que.add(i*N+j);
					while(!que.isEmpty())
					{
						int curx = que.get(0)/N;
						int cury = que.get(0)%N;
						que.remove(0);
						count++;
						visited[curx][cury] = curNum;
						
						for(int d=0;d<4;d++)
						{
							int nextx = curx+dx[d];
							int nexty = cury+dy[d];
							if(nextx<0||nextx>=N||nexty<0||nexty>=N||visited[nextx][nexty]==curNum) //  배열 밖이거나 방문됨
							{
								continue;
							}
							if(map[nextx][nexty]==map[curx][cury]+1) // 1이 정확히 큰 수
							{
								que.add(nextx*N+nexty);
							}
						}
						
					}
					
					//// 탐색
					if(count>maxcount)
					{
						maxcount=count;
						maxNum = curNum;
					}
					else if(count==maxcount&&curNum<maxNum)
					{
						maxNum=curNum;
					}
				}
			}
			
			System.out.println("#"+tc+" "+maxNum+" "+maxcount);
		}//tc loop
	}
}
