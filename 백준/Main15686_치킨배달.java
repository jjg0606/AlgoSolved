package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15686_치킨배달 {
	static ArrayList<Point> home;
	static ArrayList<Point> chick;
	static int answer = 2100000000;
	static int[][] map;
	static boolean ispickleft=true;
	static boolean[] picked;
	static int max;
	
	static class Point{
		int row;
		int col;
		public Point(int row,int col)
		{
			this.row=row;
			this.col=col;
		}
	}
	
	public static int getDist(Point home,Point chick)
	{		
		return Math.abs(home.row-chick.row)+Math.abs(home.col-chick.col);
	}
	
	public static void pick(int cur,int prv)
	{
		if(cur==max)
		{
			getTotal();
			return;
		}
		
		for(int i=prv;i<chick.size()-max+cur+1;i++)
		{
			picked[i]=true;
			pick(cur+1,i+1);
			picked[i]=false;
		}
	}
	
	public static void getTotal()
	{
		int total = 0;
		for(int i=0;i<home.size();i++)
		{
			int min = 200000000;
			for(int j=0;j<chick.size();j++)
			{
				if(picked[j]^ispickleft)
				{
					continue;
				}
				if(map[i][j]<min)
				{
					min = map[i][j];
				}
			}
			total+=min;
		}
		answer=Math.min(total,answer);
	}

	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int dimension = Integer.parseInt(stk.nextToken());
		int preserve = Integer.parseInt(stk.nextToken());
		
		home = new ArrayList<>();
		chick = new ArrayList<>();
		for(int r =0;r<dimension;r++)
		{
			stk = new StringTokenizer(br.readLine());
			for(int c=0;c<dimension;c++)
			{
				int read = Integer.parseInt(stk.nextToken());
				if(read==1)
				{
					home.add(new Point(r, c));
				}
				else if(read == 2)
				{
					chick.add(new Point(r, c));
				}
			}
		}
		// insertion end
		map=new int[home.size()][chick.size()];
		for(int i=0;i<home.size();i++)
		{
			for(int j=0;j<chick.size();j++)
			{
				map[i][j] = getDist(home.get(i), chick.get(j));
			}
		}
		// map making
		if(preserve>chick.size()/2)
		{
			ispickleft=false;
		}
		max = ispickleft ? preserve : chick.size()-preserve;
		picked=new boolean[chick.size()];
		// end
		pick(0,0);
		
		
		System.out.println(answer);
		
	}
}
