package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution1865_동철이의_일_분배 {
	static double[][] map;
	static int N;
	static double proba;
	static int[] Picked;
	
	static void find(int cur,double prob)
	{
		if(cur>=N)
		{
			proba = Math.max(proba, prob);
			return;
		}
		
		for(int i=0;i<N;i++)
		{
			if(Picked[i]==1||map[cur][i]*prob<=proba)
			{
				continue;
			}
			
			Picked[i]=1;
			find(cur+1,prob*map[cur][i]);
			Picked[i]=0;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			N = Integer.parseInt(br.readLine().trim());
			map = new double[N][N];
			for(int r=0;r<N;r++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int c=0;c<N;c++)
				{
					map[r][c] = Double.parseDouble(stk.nextToken())*0.01;
				}
			}
			proba=0.0d;
			Picked=new int[N];
			// insertion end
			find(0,1);
			proba*=100.0d;
			System.out.println("#"+tc+" "+String.format("%6f", proba));
		}//tc loop
	}
}
