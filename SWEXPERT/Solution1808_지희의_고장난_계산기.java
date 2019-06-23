package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1808_지희의_고장난_계산기 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			ArrayList<Integer> available = new ArrayList<>();
			stk= new StringTokenizer(br.readLine());
			for(int i=0;i<10;i++)
			{
				int read = Integer.parseInt(stk.nextToken());
				if(read==1)
				{
					available.add(i);
				}
			}
			int toMake = Integer.parseInt(br.readLine());
			// insertion end
			
			int[] dp = new int[toMake+1];
			
			for(int i=0;i<available.size();i++)
			{
				if(available.get(i)<=toMake)
				{
					dp[available.get(i)]=1;
				}
				
			}
			continuous : for(int i=0;i<=toMake;i++)
			{
				if(dp[i]==0)
				{
					continue;
				}
				
				for(int j=0;j<available.size();j++)
				{
					int temp = i*10+available.get(j);
					if(temp>toMake)
					{
						break continuous;
					}

					if(dp[temp]==0||dp[temp]>dp[i]+1)
					{
						dp[temp] = dp[i]+1;
					}
				}
			}
			available.remove((Integer)0);
			available.remove((Integer)1);
			// 단순 숫자 입력으로 나올 수 있는 수
			int threshold = (int)Math.sqrt(toMake);
			for(int i=2;i<=toMake;i++)
			{
				if(dp[i]==0)
				{
					continue;
				}
				if(i>threshold)
				{
					break;
				}
				
				for(int j=i;j<=toMake;j++)
				{
					if(dp[j]==0)
					{
						continue;
					}
					
					int temp = i*j;
					
					if(temp>toMake||temp==0)
					{
						break;
					}
					
					if(dp[temp]==0||dp[temp]>dp[i]+dp[j]+1)
					{
						dp[temp]=dp[i]+dp[j]+1;
					}
				}
			}
			// 곱해서 나올 수 있는 수
			
			if(dp[toMake]==0)
			{
				dp[toMake]=-1;
			}
			else
			{
				dp[toMake]++;
			}
			System.out.println("#"+tc+" "+dp[toMake]);
		}// tc loop
	}
}
