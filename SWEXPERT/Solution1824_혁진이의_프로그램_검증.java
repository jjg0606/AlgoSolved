package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1824_혁진이의_프로그램_검증 {
	
	//0 오른쪽, 1 왼쪽, 2위, 3 아래
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int rows=0;
	static int cols=0;
	static char[][] map;
	static HashSet<Integer>[][] visitedarr;
	static LinkedList<Pragma> proarr;
	
	static class Pragma
	{
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			
			return currow*100000+curcol*1000+memory*10+movedirection;
		}

		int currow;
		int curcol;
		int memory;
		int movedirection = 0;
		HashSet<Integer> visited=null;
		
		public Pragma()
		{
			this.currow=0;
			this.curcol=0;
			this.memory=0;
			this.movedirection=0;
			visited=new HashSet<>();
		}
		
		public Pragma(Pragma origin)
		{
			this.currow=origin.currow;
			this.curcol=origin.curcol;
			this.memory=origin.memory;
			this.movedirection=origin.movedirection;
		}
		
		public void Run()
		{			
			if(isvisited())
			{
				memory=16;
				return;
			}
			char command = map[currow][curcol];
			switch(command)
			{
			case '<':
				this.movedirection=1;
				break;
			case '>':
				this.movedirection=0;
				break;
			case '^':
				this.movedirection=2;
				break;
			case 'v':
				this.movedirection=3;
				break;
			case '_':
				if(memory==0)
				{
					this.movedirection=0;
				}
				else
				{
					this.movedirection=1;
				}
				break;
			case '|':
				if(memory==0)
				{
					this.movedirection=3;
				}
				else
				{
					this.movedirection=2;
				}
				break;
			case '?':
				split();
				break;
			case '@':
				this.memory=-1;
				return;
			case '+':
				this.memory=(this.memory+1)&15;
				break;
			case '-':
				this.memory=(this.memory+15)&15;
				break;
			default :
				if(Character.isDigit(command))
				{
					this.memory=command-'0';
				}
				break;
			}
			
			this.curcol=(this.curcol+dc[this.movedirection]+cols)%cols;
			this.currow=(this.currow+dr[this.movedirection]+rows)%rows;
		}// run method
		
		private boolean isvisited()
		{
			if(visitedarr[this.currow][this.curcol].contains(this.hashCode()))
			{
				return true;
			}
			visitedarr[this.currow][this.curcol].add(this.hashCode());
			return false;
		}
		
		private void split()
		{
			Pragma first = new Pragma(this);
			first.movedirection=(this.movedirection+1)&3;
			Pragma second = new Pragma(this);
			second.movedirection=(this.movedirection+2)&3;
			Pragma third = new Pragma(this);
			third.movedirection=(this.movedirection+3)&3;
			proarr.add(first);
			proarr.add(second);
			proarr.add(third);
		}

	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk = new StringTokenizer(br.readLine());
			rows = Integer.parseInt(stk.nextToken());
			cols = Integer.parseInt(stk.nextToken());
			map = new char[rows][cols];
			visitedarr=new HashSet[rows][cols];
			for(int r=0;r<rows;r++)
			{
				String cur = br.readLine().trim();
				for(int c=0;c<cols;c++)
				{
					map[r][c]=cur.charAt(c);
					visitedarr[r][c]=new HashSet<Integer>();
				}
			}
			proarr = new LinkedList<>();			
			//insertion end
			
			proarr.add(new Pragma());
			boolean isout = false;
			outer : while(!proarr.isEmpty())
			{
				switch(proarr.peekFirst().memory)
				{
					case -1:
						isout = true;
						break outer;
					case 16:
						proarr.removeFirst();
						break;
					default :
						proarr.peekFirst().Run();
						break;
				}
			}
			String answer = "NO";
			if(isout)
			{
				answer="YES";
			}
			System.out.println("#"+tc+" "+answer);
		}// tc loop
	}
}
