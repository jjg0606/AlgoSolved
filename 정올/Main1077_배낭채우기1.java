package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1077_배낭채우기1 {
	static class Jewel
	{
		int value;
		int weight;
		public Jewel(int weight,int value)
		{
			this.value=value;
			this.weight=weight;
		}
	}

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int kind = Integer.parseInt(stk.nextToken());
		int W = Integer.parseInt(stk.nextToken());
		Jewel[] jearr = new Jewel[kind];
		for(int i=0;i<kind;i++)
		{
			stk = new StringTokenizer(br.readLine());
			jearr[i] = new Jewel(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		}
		int[] dp = new int[W+1];

		for(int i=0;i<kind;i++)
		{
			int weight = jearr[i].weight;
			int value = jearr[i].value;
			for(int j=weight;j<=W;j++)
			{
				dp[j] = Math.max(dp[j-weight]+value, dp[j]);
			}
		}
		
		System.out.println(dp[W]);
	}
}
