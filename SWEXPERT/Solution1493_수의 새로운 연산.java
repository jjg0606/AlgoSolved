package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1493 {
	static int[] arr;
	
	static void init()
	{
		arr = new int[142];
		arr[1]=1;
		for(int i=2;i<142;i++)
		{
			arr[i]=arr[i-1]+i-1;
		}
	}
	
	static class Point
	{
		int x;
		int y;
		public Point(int x,int y)
		{
			this.x=x;
			this.y=y;
		}
	}
	
	static int sharpOper(Point p)
	{
		return (p.x+p.y-1)*(p.x+p.y-2)/2 + p.x;
	}
	
	static Point emperOper(int val)
	{
		if (val==1)
		{
			return new Point(1,1); 
		}
		int n=2;
		int x=1;
		int y=1;
		int nlast=2;
		while(true)
		{
			int nlinestart = nlast+n;
			if(val<nlinestart)
			{
				x=val-nlast+1;
				y=n+1-x;
				return new Point(x,y);
			}
			n++;
			nlast = nlinestart;
		}		
	}
	
	static Point neemper(int val)
	{
		
		int leftend = 1;
		int rightend = arr.length-1;
		int cur = (leftend+rightend)/2;
		int n;
		while(true)
		{
			if(rightend-leftend<=2)
			{
				if(val>arr[cur+1])
				{
					n=cur+1;
					break;
				}
				if(val<arr[cur])
				{
					n=cur-1;
					break;
				}
				n=cur;
				break;
			}
			
			if(val>arr[cur])
			{
				leftend = cur;
				
			}
			else if(val<arr[cur])
			{
				rightend = cur;
			}
			else
			{
				n=cur;
				break;
			}
			cur = (leftend+rightend)/2;			
		}
		
		int x=val-arr[n]+1;
		int y=n+1-x;
		return new Point(x,y);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int q = Integer.parseInt(stk.nextToken());
			Point pp = neemper(p);
			Point qp = neemper(q);
			Point fp = new Point(pp.x+qp.x,pp.y+qp.y);
			System.out.println("#"+tc+" "+sharpOper(fp));
		}//tc loop
	}
}
