package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution5986 {
	static ArrayList<Integer> primearr;
	static void getPrimeNum()
	{
		int[] chae = new int[1001];
		primearr=new ArrayList<Integer>();
		for(int i=2;i<=1000;i++)
		{
			if(chae[i]==0)
			{				
				primearr.add(i);
				for(int j=i;j<=1000;j+=i)
				{
					chae[j]=1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		getPrimeNum();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int toMake= Integer.parseInt(br.readLine().trim());
			int answer = 0;
			
			
			for(int i=0;i<primearr.size();i++)
			{
				int get1 = primearr.get(i);
				if(get1>=toMake)
				{
					break;
				}
				
				for(int j=i;j<primearr.size();j++)
				{
					int get2 = primearr.get(j);
					if(get1+get2>toMake)
					{
						break;
					}
					int toget = toMake-get1-get2;
					if(toget<get2)
					{
						continue;
					}
					if(primearr.contains(toget))
					{
						answer++;
					}					
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}//tc loop
	}
}
