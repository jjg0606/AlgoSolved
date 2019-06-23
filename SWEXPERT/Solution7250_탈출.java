package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution7250_탈출 {
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	
	static class Move
	{
		int row;
		int col;
		int kleft;
		public Move(int row,int col,int kleft)
		{
			this.row=row;
			this.col=col;
			this.kleft=kleft;
		}
	}
	
	static void initmap(int[][] map,int init)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=init;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk = new StringTokenizer(br.readLine());
			int rows = Integer.parseInt(stk.nextToken());
			int cols = Integer.parseInt(stk.nextToken());
			int times = Integer.parseInt(stk.nextToken());
			

			char[][] map = new char[rows][cols];
			LinkedList<Move> moveque = new LinkedList<>(); // 스캇의 이동
			LinkedList<Integer> firelist = new LinkedList<>(); // 불의 이동
			LinkedList<Integer> villque = new LinkedList<>(); //빌런의 이동
			
			int[][] firemap=new int[rows][cols];
			initmap(firemap,200000000); // 불이번진걸 확인
			
			int exitr = 0;
			int exitc = 0;
			for(int r=0;r<rows;r++)
			{
				String read = br.readLine();
				for(int c=0;c<cols;c++)
				{
					char cur = read.charAt(c);
					switch(cur)
					{
					case 'S'://스캇
						cur = 'A';
						moveque.add(new Move(r, c, times));
						break;
					case 'E'://탈출구
						exitr=r;
						exitc=c;
						break;
					case 'F'://불
						firelist.add(r*cols+c);
						firemap[r][c]=0;
						break;
					case 'V'://악당
						cur = 'A';
						villque.add(r*cols+c);
						break;
					default:
						break;
					}
					map[r][c] = cur;
				}
			}
			//insertion end
			int[][] visit=new int[rows][cols];
			initmap(visit,200000000);
			if(!villque.isEmpty())
			{
				visit[villque.peekFirst()/cols][villque.peekFirst()%cols]=0;
			}			
			while(!villque.isEmpty())
			{
				int currow = villque.peekFirst()/cols;
				int curcol = villque.pollFirst()%cols;
				if(map[currow][curcol]=='E')
				{
					continue;
				}
				int curlength = visit[currow][curcol];
				
				for(int d=0;d<4;d++)
				{
					int nexrow = currow+dr[d];
					int nexcol = curcol+dc[d];
					if(nexrow<0||nexrow>=rows||nexcol<0||nexcol>=cols)
					{
						continue;
					}
					if(map[nexrow][nexcol]=='X'||map[nexrow][nexcol]=='W')
					{
						continue;
					}
					if(visit[nexrow][nexcol]>curlength+1)
					{
						visit[nexrow][nexcol] = curlength+1;
						villque.add(nexrow*cols+nexcol);
					}
				}
			}// 악당 bfs
			int vilroute = visit[exitr][exitc]; // 악당이 가는 최단 거리

			// 불지르기 시작
			while(!firelist.isEmpty())
			{
				int currow = firelist.peekFirst()/cols;
				int curcol = firelist.pollFirst()%cols;
				int curfire = firemap[currow][curcol];
				if(curfire>vilroute)
				{
					continue;
				}
				for(int d=0;d<4;d++)
				{
					int nexrow = currow+dr[d];
					int nexcol = curcol+dc[d];
					if(nexrow<0||nexrow>=rows||nexcol<0||nexcol>=cols)
					{
						continue;
					}
					if(map[nexrow][nexcol]=='X'||map[nexrow][nexcol]=='W'||map[nexrow][nexcol]=='E'||firemap[nexrow][nexcol]<curfire+1)
					{
						continue;
					}
					firemap[nexrow][nexcol] = curfire+1;
					firelist.add(nexrow*cols+nexcol);
				}
			}
			//불지르기 끝
			initmap(visit,200000000); // visit 배열
			visit[moveque.peekFirst().row][moveque.peekFirst().col] = 0;
			while(!moveque.isEmpty())
			{
				Move cur = moveque.pollFirst();
				int curlength = visit[cur.row][cur.col];
				if(curlength>vilroute||map[cur.row][cur.col]=='E')
				{
					continue;
				}
				if(map[cur.row][cur.col]=='A')
				{
					cur.kleft=times;
				}
				
				if(cur.kleft==0)
				{
					boolean isAaround=false;
					for(int d=0;d<4;d++)
					{
						int nexrow = cur.row+dr[d];
						int nexcol = cur.col+dc[d];
						if(nexrow<0||nexrow>=rows||nexcol<0||nexcol>=cols)
						{
							continue;
						}
						if(map[nexrow][nexcol]=='A'||firemap[nexrow][nexcol]>=curlength) //X이거나 이미 불이 번진 곳
						{
							isAaround=true;
							break;
						}
					}
					if(!isAaround)
					{
						continue;
					}
					for(int d=0;d<4;d++)
					{
						int nexrow = cur.row+dr[d];
						int nexcol = cur.col+dc[d];
						if(nexrow<0||nexrow>=rows||nexcol<0||nexcol>=cols)
						{
							continue;
						}
						if(firemap[nexrow][nexcol]<curlength)//X이거나 이미 불이 번진 곳
						{
							continue;
						}						
						if((map[nexrow][nexcol]=='A'||map[nexrow][nexcol]=='E')&&visit[nexrow][nexcol]>curlength+1) 
						{
							visit[nexrow][nexcol] = curlength+1;
							moveque.add(new Move(nexrow, nexcol, cur.kleft-1));
						}
						else if(map[nexrow][nexcol]=='W'&&visit[nexrow][nexcol]>curlength+3) 
						{
							visit[nexrow][nexcol] = curlength+3;
							moveque.add(new Move(nexrow, nexcol, times-2));
						}
					}
				}
				else
				{
					for(int d=0;d<4;d++)
					{
						int nexrow = cur.row+dr[d];
						int nexcol = cur.col+dc[d];
						if(nexrow<0||nexrow>=rows||nexcol<0||nexcol>=cols)
						{
							continue;
						}
						if(map[nexrow][nexcol]=='X'||firemap[nexrow][nexcol]<curlength) //X이거나 이미 불이 번진 곳
						{
							continue;
						}
						if(visit[nexrow][nexcol]<curlength+1)
						{
							continue;
						}
						visit[nexrow][nexcol] = curlength+1;
						moveque.add(new Move(nexrow, nexcol, cur.kleft-1));
					}
				}

				
			}//스콧의bfs
			int scotroute = visit[exitr][exitc];
			int answer = scotroute<vilroute? scotroute : -1;
			System.out.println("#"+tc+" "+answer);
		}//tc end
	}
}
