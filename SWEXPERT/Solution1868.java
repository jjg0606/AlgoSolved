package Solving;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution1868 {
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1,};
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for(int tc=1;tc<=testCase;tc++)
		{
			int dimension = sc.nextInt();
			int[][] map = new int[dimension][dimension];
			ArrayList<Integer> mine = new ArrayList<>();
			for(int r=0;r<dimension;r++)
			{
				String read=sc.next();
				for(int c=0;c<dimension;c++)
				{
					if(read.charAt(c)=='*')
					{
						map[r][c]=-1;
						for(int d=0;d<8;d++)
						{
							int nextr = r+dr[d];
							int nextc = c+dc[d];
							if(nextr<0||nextr>=dimension||nextc<0||nextc>=dimension||map[nextr][nextc]==-1)
							{
								continue;
							}
							map[nextr][nextc] = 1;
						}
					}
				}
			}//insertion for loop
			// insertion and map setting end
			
			int clicked = 0;
			ArrayList<Integer> que = new ArrayList<>();
			for(int r=0;r<dimension;r++)
			{
				for(int c=0;c<dimension;c++)
				{
					if(map[r][c]!=0)
					{
						continue;
					}
					clicked++;
					que.add(r*dimension+c);
					map[r][c]=-1;
					while(!que.isEmpty())
					{
						int curr = que.get(0)/dimension;
						int curc = que.remove(0)%dimension;
						for(int d=0;d<8;d++)
						{
							int nexr = curr+dr[d];
							int nexc = curc+dc[d];
							if(nexr<0||nexr>=dimension||nexc<0||nexc>=dimension)
							{
								continue;
							}
							if(map[nexr][nexc]==0)
							{
								que.add(nexr*dimension+nexc);
							}
							map[nexr][nexc]=-1;
						}
					}
				}				
			}// 0 check
			for(int r=0;r<dimension;r++)
			{
				for(int c=0;c<dimension;c++)
				{
					if(map[r][c]!=-1)
					{
						clicked++;
					}
				}
			}
			// left check
			System.out.println("#"+tc+" "+clicked);
		}
	}
}
