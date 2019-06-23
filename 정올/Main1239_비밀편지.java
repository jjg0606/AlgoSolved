package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1239_비밀편지 {
	static String[] alpha = {"000000","001111","010011","011100"
			,"100110","101001","110101","111010"};
	// A,B,C,D,E,F,G,H
	
	
	static void append(StringBuilder sb,int charnum)
	{
		char toappedn = (char)('A'+charnum);
		sb.append(toappedn);
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int charnum = Integer.parseInt(br.readLine().trim());
		String read = br.readLine().trim();
		StringBuilder sb = new StringBuilder();
		int[] matching = new int[8];
		iloop : for(int i=0;i<charnum;i++)
		{
			Arrays.fill(matching, 0);
			for(int j=0;j<6;j++)
			{
				for(int k=0;k<8;k++)
				{
					if(read.charAt(i*6+j)==alpha[k].charAt(j))
					{
						matching[k]++;
					}
				}
			}
			
			for(int k=0;k<8;k++)
			{
				if(matching[k]==6)
				{
					append(sb,k);
					continue iloop;
				}
			}
			boolean isDoubled = false;
			int index=0;
			for(int k=0;k<8;k++)
			{
				if(matching[k]!=5)
				{
					continue;
				}
				if(isDoubled)
				{
					System.out.println(i+1);
					return;
				}
				isDoubled=true;
				index=k;
			}
			if(!isDoubled)
			{
				System.out.println(i+1);
				return;
			}
			append(sb,index);
		}// i loop
		System.out.println(sb.toString());
	}
}
