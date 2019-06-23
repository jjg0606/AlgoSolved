package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3074_입국심사 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk=new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int M = Integer.parseInt(stk.nextToken());
			long max = 0;
			int[] times=new int[N];
			
			for(int i=0;i<N;i++)
			{
				times[i]=(Integer.parseInt(br.readLine().trim()));
				if(times[i]>max)
				{
					max=times[i];
				}
			}
			
			long right = max*(long)M;
			long left = 0;
			long middle=right/2;
			while(right-left>1)
			{
				long count = 0;
				for(int i=0;i<N;i++)
				{
					count+=middle/(long)times[i];
					if(count>=M)
					{
						break;
					}
				}
				if(count>=M)
				{
					right=middle;
					middle = (right+left)/2;
				}
				else
				{
					left=middle;
					middle = (right+left)/2;
				}
			}
			System.out.println("#"+tc+" "+right);
			// insertion end
		}//tc loop
	}
}
