package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution5656_벽돌_깨기 {
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int N;
	static int cols;
	static int rows;
	static int answer;
	
	/**
	 * 
	 * @param map 지형을 나타내는 맵
	 * @param left 몇개의 블록이 남았는지 보여주는 인수
	 * @param cur 몇번째 부순것인지 보여줌
	 */
	static void recursiveBreak(int[][] map,int left,int cur)
	{
		answer = Math.min(answer, left);
		if(cur==N||answer==0)
		{		
			return;
		}
		
		Stack<Integer> toBreak = new Stack<>();
		int[][] copyMap = new int[rows][cols];
		for(int i=0;i<cols;i++) // 구슬을 떨어뜨릴 행을 선정
		{
			CopyMap(map, copyMap);
			int copyLeft=left;
			for(int r=0;r<rows;r++) // 구슬 맞는 지점 찾는중
			{
				if(copyMap[r][i]!=0)
				{
					toBreak.add(r*cols+i);
					copyMap[r][i]*=-1;
					break;
				}
			}
			if(toBreak.isEmpty()) // 해당 열에 부술 게 없음
			{
				continue;
			}
			
			while(!toBreak.isEmpty())
			{
				int curR = toBreak.peek()/cols;
				int curC = toBreak.pop()%cols;
				int dist = -1*copyMap[curR][curC];
				copyLeft--;
				for(int d=0;d<4;d++)
				{
					for(int n=1;n<dist;n++)
					{
						int nextR = curR+dr[d]*n;
						int nextC = curC+dc[d]*n;
						if(!isInBound(nextR, nextC))
						{
							break;
						}
						if(copyMap[nextR][nextC]>0)
						{
							toBreak.add(nextR*cols+nextC);
							copyMap[nextR][nextC]*=-1;
						}
					}
				}
				copyMap[curR][curC]=0; // 터진것은 0으로 바꿔줌
			}//dfs loop	
			// 다 부쉈음
			for(int c=0;c<cols;c++)
			{
				int downnth = 0;
				for(int r=rows-1;r>=0;r--)
				{
					if(copyMap[r][c]==0)
					{
						downnth++;
					}
					else if(downnth>0)
					{
						copyMap[r+downnth][c] = copyMap[r][c];
						copyMap[r][c]=0;
					}
				}
			}
			//내려오는거 처리
			recursiveBreak(copyMap, copyLeft, cur+1);
		}
	}	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			cols=Integer.parseInt(stk.nextToken());
			rows=Integer.parseInt(stk.nextToken());
			int[][] originalmap = new int[rows][cols];
			int left = 0;
			for(int i=0;i<rows;i++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int j=0;j<cols;j++)
				{
					originalmap[i][j] = Integer.parseInt(stk.nextToken());
					if(originalmap[i][j]!=0)
					{
						left++;
					}
				}
			}
			//insertion end;
			answer = left;
			//static initialize
			recursiveBreak(originalmap, left, 0);
			
			//System.out.println("#"+tc+" "+answer);
			sb.append('#').append(tc).append(' ').append(answer).append('\n');			
		}// tc loop
		bw.write(sb.toString());
		bw.flush();
	}
	
	static boolean isInBound(int nextR,int nextC)
	{
		if(nextR<0||nextR>=rows||nextC<0||nextC>=cols)
		{
			return false;
		}
		return true;
	}
	
	static void CopyMap(int[][] original,int[][] dest)
	{
		for(int r=0;r<original.length;r++)
		{
			for(int c=0;c<original[0].length;c++)
			{
				dest[r][c] = original[r][c];
			}
		}
	}
}
