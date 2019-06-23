package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4014_활주로_건설 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int dimension = Integer.parseInt(stk.nextToken());
			int width = Integer.parseInt(stk.nextToken());
			int[][] map = new int[dimension][dimension];
			for(int r=0;r<dimension;r++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int c=0;c<dimension;c++)
				{
					map[r][c] = Integer.parseInt(stk.nextToken());
				}
			}
			//insertion end
			int answer = 0;
			boolean[] used = new boolean[dimension];
			row : for(int r=0;r<dimension;r++) //행 고정 열로만(가로)
			{
				Arrays.fill(used, false);
				int heightbefore = map[r][0];
				for(int c=1;c<dimension;c++)
				{
					//높이가 같으면 패스
					if(map[r][c]==heightbefore)
					{
						continue;
					}					
					else if(heightbefore-1==map[r][c]) // 위에서 아래로 내려옴
					{
						int max = c+width-1;
						if(max >= dimension)
						{
							continue row;
						}
						for(;c<=max;c++)
						{
							if(map[r][c]!=heightbefore-1)
							{
								continue row;
							}
							used[c]=true;
						}
						heightbefore--;
						c--;
					}
					else if(heightbefore+1==map[r][c])// 아래에서 위로 올라옴
					{
						int min = c-width;
						if(min < 0)
						{
							continue row;
						}
						for(int k=c-1;k>=min;k--)
						{
							if(map[r][k]!=heightbefore||used[k])
							{
								continue row;
							}
							used[k]=true;
						}
						heightbefore++;
					}
					else
					{
						continue row;
					}
				}//for-c loop
				answer++;
			}// for-r loop
			
			col : for(int c=0;c<dimension;c++) //열 고정 행으로만(세로)
			{
				Arrays.fill(used, false);
				int heightbefore = map[0][c];
				for(int r=1;r<dimension;r++)
				{
					//높이가 같으면 패스
					if(map[r][c]==heightbefore)
					{
						continue;
					}					
					else if(heightbefore-1==map[r][c]) // 위에서 아래로 내려옴
					{
						int max = r+width-1;
						if(max >= dimension)
						{
							continue col;
						}
						for(;r<=max;r++)
						{
							if(map[r][c]!=heightbefore-1)
							{
								continue col;
							}
							used[r]=true;
						}
						heightbefore--;
						r--;
					}
					else if(heightbefore+1==map[r][c])// 아래에서 위로 올라옴
					{
						int min = r-width;
						if(min < 0)
						{
							continue col;
						}
						for(int k=r-1;k>=min;k--)
						{
							if(map[k][c]!=heightbefore||used[k])
							{
								continue col;
							}
							used[k]=true;
						}
						heightbefore++;
					}
					else
					{
						continue col;
					}
				}//for-c loop
				answer++;
			}// for-r loop
			
			//
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}//tc loop
		bw.write(sb.toString());
		bw.flush();
	}
}
