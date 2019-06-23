package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution4012_요리사 {
	static int maxnum;
	static int[][] table;
	static int[] picked;
	static int min;
	
	public static void checkdiff()
	{
		int picknum=0;
		int unpicknum=0;
		for(int r=0;r<maxnum;r++)
		{
			if(picked[r]==1)
			{
				for(int c=0;c<maxnum;c++)
				{
					if(picked[c]==1)
					{
						picknum+=table[r][c];
					}
				}
			}
			else
			{
				for(int c=0;c<maxnum;c++)
				{
					if(picked[c]==0)
					{
						unpicknum+=table[r][c];
					}
				}
			}			
		}
		min=Math.min(min, Math.abs(unpicknum-picknum));
	}
	// cur : 몇번째 픽인지
	// last : 현재 단계에서 시작할 수 있는 숫자
	public static void combination(int cur,int last)
	{
		if(cur>=maxnum/2)
		{
			checkdiff();
			return;
		}
		for(int i=last;i<maxnum/2+cur+1;i++)
		{
			picked[i]=1;
			combination(cur+1,i+1);
			picked[i]=0;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			maxnum =Integer.parseInt(br.readLine().trim());
			table = new int[maxnum][maxnum];
			for(int r=0;r<maxnum;r++)
			{
				stk=new StringTokenizer(br.readLine());
				for(int c=0;c<maxnum;c++)
				{
					table[r][c]=Integer.parseInt(stk.nextToken());
				}
			}
			picked= new int[maxnum];
			picked[0]=1;
			min = Integer.MAX_VALUE;
			combination(1,1);
			System.out.println("#"+tc+" "+min);
		}//tc loop
	}
}
