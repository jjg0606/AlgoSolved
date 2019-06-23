package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main1082_화염에서탈출 {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static class Move{
		int row;
		int col;
		int time;
		public Move(int row, int col, int time) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
		}		
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int rows = Integer.parseInt(stk.nextToken());
		int cols = Integer.parseInt(stk.nextToken());
		LinkedList<Integer> fireque = new LinkedList<>();
		int startR = 0;
		int startC = 0;
		int[][] map = new int[rows][cols];
		for(int r=0;r<rows;r++)
		{
			String read = br.readLine().trim();
			for(int c=0;c<cols;c++)
			{
				switch (read.charAt(c)) {
				case '.':					
					break;
				case 'S':
					startR=r;
					startC=c;
					break;
				case 'D':
					map[r][c]=-1;
					break;
				case '*':
					map[r][c] = 1;
					fireque.add(r*cols+c);
					break;
				case 'X':
					map[r][c]=1;
					break;
				default:
					break;
				}
			}
		}
		// insertion end;
		while(!fireque.isEmpty())
		{
			int curR = fireque.getFirst()/cols;
			int curC = fireque.pollFirst()%cols;
			int curT = map[curR][curC];
			for(int d=0;d<4;d++)
			{
				int nextR = curR+dr[d];
				int nextC = curC+dc[d];
				if(!isInBound(rows, cols, nextR, nextC))
				{
					continue;
				}
				if(map[nextR][nextC]<=curT+1&&map[nextR][nextC]!=0)
				{
					continue;
				}
				map[nextR][nextC]=curT+1;
				fireque.add(nextR*cols+nextC);				
			}
		}
		// fire map
		boolean[][] visited = new boolean[rows][cols];
		LinkedList<Move> moveque = new LinkedList<>();
		moveque.add(new Move(startR, startC, 1));
		visited[startR][startC]=true;
		int answer = -1;
		while(!moveque.isEmpty())
		{
			Move cur = moveque.pollFirst();
			if(map[cur.row][cur.col]==-1)
			{
				answer = cur.time-1;
				break;
			}
			for(int d=0;d<4;d++)
			{
				int nextR = cur.row+dr[d];
				int nextC = cur.col+dc[d];
				if(!isInBound(rows, cols, nextR, nextC)||visited[nextR][nextC]) 
				{
					continue;
				}
				if(0<map[nextR][nextC]&&map[nextR][nextC]<=cur.time+1)
				{
					continue;
				}
				visited[nextR][nextC]=true;
				moveque.add(new Move(nextR, nextC, cur.time+1));
			}
		}// move
		if(answer==-1)
		{
			System.out.println("KAKTUS");
		}
		else
		{
			System.out.println(answer);
		}
		
	}
	
	static boolean isInBound(int rows,int cols,int nextR,int nextC)
	{
		if(nextR<0||nextR>=rows||nextC<0||nextC>=cols)
		{
			return false;
		}
		return true;
	}
}
