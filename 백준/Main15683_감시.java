package Solving04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15683_감시 {
	static int[] dr = {0,-1,0,1};
	static int[] dc = {1,0,-1,0};
	static int[][] map;
	static int cctvnum = 1;
	static int left = 0; // 감시 못하는 공간 
	static int answer = 1000000000;
	static ArrayList<cctv> tvarr;
	
	static class cctv
	{
		//0 = 오른쪽, 1 = 위, 2 = 왼쪽, 3 = 아래
		boolean[] watching; // 기본 방향일때 볼 수 있는 방향
		int row;
		int col;
		int num; //cctv 일련번호
		public cctv(int row,int col,int kind)
		{
			this.row=row;
			this.col=col;
			this.num=cctvnum++;
			
			switch(kind)
			{
			case 1:
				watching=new boolean[] {true,false,false,false};
				break;
			case 2:
				watching=new boolean[] {true,false,true,false};
				break;
			case 3:
				watching=new boolean[] {true,true,false,false};
				break;
			case 4:
				watching=new boolean[] {true,true,true,false};
				break;
			case 5:
				watching=new boolean[] {true,true,true,true};
				break;
			}
		}
		
		public void paint(int direction)
		{
			for(int d=0;d<4;d++)
			{
				if(watching[d]==false)
				{
					continue;
				}
				int direc = (d+direction)%4;
				int length = 1;	
				while(true)
				{
					int nextR = this.row+dr[direc]*length;
					int nextC = this.col+dc[direc]*length;
					if(!isInBoundary(nextR, nextC)||map[nextR][nextC]==-2)
					{
						break;
					}
					if(map[nextR][nextC]==0)
					{
						map[nextR][nextC] = this.num;
						left--;
					}
					length++;
				}
			}
		}
		
		public void unpaint(int direction)
		{
			for(int d=0;d<4;d++)
			{
				if(watching[d]==false)
				{
					continue;
				}
				int direc = (d+direction)%4;
				int length = 1;	
				while(true)
				{
					int nextR = this.row+dr[direc]*length;
					int nextC = this.col+dc[direc]*length;
					if(!isInBoundary(nextR, nextC)||map[nextR][nextC]==-2)
					{
						break;
					}
					if(map[nextR][nextC]==this.num)
					{
						map[nextR][nextC] = 0;
						left++;
					}
					length++;
				}
			}
		}
	}
	
	static void getRecursive(int cur)
	{
		if(cur==tvarr.size())
		{
			answer = Math.min(answer, left);
			return;
		}
		
		for(int d=0;d<4;d++)
		{
			tvarr.get(cur).paint(d);
			getRecursive(cur+1);
			tvarr.get(cur).unpaint(d);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int rows = Integer.parseInt(stk.nextToken());
		int cols = Integer.parseInt(stk.nextToken());
		map = new int[rows][cols];
		tvarr = new ArrayList<>();
		for(int r=0;r<rows;r++)
		{
			stk = new StringTokenizer(br.readLine());
			for(int c=0;c<cols;c++)
			{
				int read = Integer.parseInt(stk.nextToken());
				if(1<=read&&read<=5)
				{
					map[r][c]=-1; // cctv
					tvarr.add(new cctv(r, c, read));
				}
				else if(read == 6)
				{
					map[r][c]=-2; //벽
				}
				else if(read == 0)
				{
					left++;
				}
			}
		}
		//map making
		getRecursive(0);
		
		System.out.println(answer);
	}
	
	public static boolean isInBoundary(int row,int col)
	{
		if(row<0||col<0||row>=map.length||col>=map[0].length)
		{
			return false;
		}
		return true;
	}
}
