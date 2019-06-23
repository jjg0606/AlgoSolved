package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution5644_무선_충전 {
	final static int[] dr = {0,-1,0,1,0};
	final static int[] dc = {0,0,1,0,-1};
	
	static int[][] map = new int[10][10];	
	static Person[] personarr;
	static Charge[] chargearr;
	static int answer=0;
	
	static class Person
	{
		int row;
		int col;
		LinkedList<Integer> toMove;
		
		public Person(int row,int col)
		{
			this.row=row;
			this.col=col;
			toMove = new LinkedList<>();
		}
		
		public void Move()
		{		
			int d = toMove.pollFirst();
			row+=dr[d];
			col+=dc[d];
		}
		
		public int Pick()
		{
			for(int i=0;i<chargearr.length;i++)
			{
				if(((map[row][col])&(1<<i)) ==0)
				{
					continue;
				}
				else
				{
					return i;
				}
			}
			return -1;
		}
		
		public int PickAfter(int af)
		{
			for(int i=af+1;i<chargearr.length;i++)
			{
				if(((map[row][col])&(1<<i)) ==0)
				{
					continue;
				}
				else
				{
					return i;
				}
			}
			return -1;
		}
	}
	
	static class Charge
	{
		int row;
		int col;
		int range;
		int power;
		public Charge(int col,int row, int range, int power) {
			super();
			this.row = row-1;
			this.col = col-1;
			this.range = range;
			this.power = power;
		}
		
	}
	
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk;
		
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			stk = new StringTokenizer(br.readLine());
			int moving = Integer.parseInt(stk.nextToken());
			int bcnum = Integer.parseInt(stk.nextToken());
			personarr = new Person[2];
			personarr[0] = new Person(0, 0);
			personarr[1] = new Person(9,9);
			for(int i=0;i<2;i++)
			{
				stk= new StringTokenizer(br.readLine());
				for(int j=0;j<moving;j++)
				{
					personarr[i].toMove.add(Integer.parseInt(stk.nextToken()));
				}
			}
			// human
			chargearr = new Charge[bcnum];
			for(int i=0;i<bcnum;i++)
			{
				stk = new StringTokenizer(br.readLine());
				chargearr[i] = new Charge(Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()));
			}
			Arrays.sort(chargearr, new Comparator<Charge>() {
				@Override
				public int compare(Charge o1, Charge o2) {
					// TODO Auto-generated method stub
					return o2.power-o1.power;
				}				
			});
			//charge
			init();
			paintMap();
			//
			int p1 = personarr[0].Pick();
			int p2 = personarr[1].Pick();
			answer += p1 >= 0 ?  chargearr[p1].power : 0;
			answer += p2 >= 0 ?  chargearr[p2].power : 0;	
			// 처음 자리에서 충전
			
			while(!personarr[0].toMove.isEmpty())
			{
				personarr[0].Move();
				personarr[1].Move();
				
				p1 = personarr[0].Pick();
				p2 = personarr[1].Pick();
				
				if(p1==p2&&p1>=0)// 서로 겹치는 구역에 있을 때
				{
					int firstcase = chargearr[p1].power;
					int secondcase = firstcase;
					
					p1 = personarr[0].PickAfter(p1);
					p2 = personarr[1].PickAfter(p2);
					firstcase += p1 >= 0 ?  chargearr[p1].power : 0;
					secondcase+= p2 >= 0 ?  chargearr[p2].power : 0;
					answer+=firstcase > secondcase ? firstcase : secondcase;
				}
				else // 둘이 다른곳에 있거나, 둘 다 -1
				{
					answer += p1 >= 0 ?  chargearr[p1].power : 0;
					answer += p2 >= 0 ?  chargearr[p2].power : 0;					
				}
			}

			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}// tc loop
		bw.write(sb.toString());
		bw.flush();
	}
	
	public static void init()
	{
		answer = 0;
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				map[i][j]=0;
			}
		}			
	}
	
	public static int getDist(Charge c,int row,int col)
	{
		return Math.abs(c.row-row)+Math.abs(c.col-col);
	}
	
	public static void paintMap()
	{
		for(int r=0;r<10;r++)
		{
			for(int c=0;c<10;c++)
			{
				for(int i=0;i<chargearr.length;i++)
				{
					if(getDist(chargearr[i], r, c)<=chargearr[i].range)
					{
						map[r][c]|=1<<i;
					}
				}
			}
		}		
	}
}
