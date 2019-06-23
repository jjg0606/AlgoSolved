package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_7208_지도_칠하기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			int ctryNum = Integer.parseInt(br.readLine().trim());
			int[] color = new int[ctryNum];
			stk = new StringTokenizer(br.readLine());
			for(int i=0;i<ctryNum;i++)
			{
				color[i] = Integer.parseInt(stk.nextToken());
			}
			int[][] edge = new int[ctryNum][ctryNum];
			for(int i=0;i<ctryNum;i++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int j=0;j<ctryNum;j++)
				{
					edge[i][j] = Integer.parseInt(stk.nextToken());
				}
			}			
			//insertion end
			LinkedList<Integer> que = new LinkedList<>();
			int[] visited = new int[ctryNum];
			int answer = 0;
			que.add(0);
			int[] colcnt = new int[5];
			while(!que.isEmpty())
			{
				int cur = que.pollFirst();
			
				visited[cur]=1;
				Arrays.fill(colcnt, 0);
				for(int j=0;j<ctryNum;j++)
				{
					if(edge[cur][j]!=1)
					{
						continue;
					}
					
					colcnt[color[j]]=1;
					if(visited[j]==0)
					{
						que.add(j);
					}
				}// 충돌 확인
				if(colcnt[color[cur]]==1) //충돌이 난다는 뜻
				{
					int colprv = color[cur];
					for(int c=1;c<5;c++)
					{
						if(colcnt[c]==0)
						{
							color[cur]=c;
							answer++;
							break;
						}
					}
					if(color[cur]==colprv) // 바꿀수 있는 색이 없소
					{
						for(int j=0;j<ctryNum;j++)
						{
							if(edge[cur][j]==1&&color[j]==colprv)
							{
								que.add(j);
							}
						}
					}
				}// 충돌 처리문			
			}// while 문

			
			System.out.println("#"+tc+" "+answer);			
		}//tc loop
	}
}
