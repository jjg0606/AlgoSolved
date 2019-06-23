package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution5213 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		long[] acum = new long[1000001];
		for(int i=1;i<=1000000;i+=2)
		{
			for(int j=i;j<=1000000;j+=i)
			{
				acum[j]+=i;
			}
		}	
		for(int i=2;i<=1000000;i++)
		{
			acum[i]+=acum[i-1];
		}
			
		for(int tc=1;tc<=testCase;tc++)
		{
			stk=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			long sum = acum[end]-acum[start-1];
			System.out.println("#"+tc+" "+sum);
		}//tc loop
	}
}
