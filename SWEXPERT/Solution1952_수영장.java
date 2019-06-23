package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952_수영장 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			int[] money = new int[4];
			stk = new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++)
			{
				money[i]=Integer.parseInt(stk.nextToken());
			}
			
			int[] monthly=new int[13];
			stk=new StringTokenizer(br.readLine());
			for(int i=1;i<13;i++)
			{
				monthly[i]=Integer.parseInt(stk.nextToken());
			}
			int[] dp = new int[13];
			
			for(int i=1;i<=12;i++)
			{
				int minmoney = money[0]*monthly[i]<money[1]?money[0]*monthly[i]:money[1];
				minmoney = minmoney+dp[i-1];
			    if(i>2)
				{
					minmoney=dp[i-3]+money[2]<minmoney?dp[i-3]+money[2]:minmoney;
				}
				dp[i]=minmoney;
			}
			
			if(dp[12]>dp[11]+money[2])
			{
				dp[12]=dp[11]+money[2];
			}
			if(dp[12]>dp[10]+money[2])
			{
				dp[12]=dp[10]+money[2];
			}
			if(dp[12]>money[3])
			{
				dp[12]=money[3];
			}
			System.out.println("#"+tc+" "+dp[12]);
		}//tc loop
	}
}
