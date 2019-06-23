package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution4366_정식이의_은행업무 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			String binary = br.readLine().trim();
			String trinary = br.readLine().trim();
			long Bto10 = Long.parseLong(binary, 2);
			long Tto10 = Long.parseLong(trinary, 3);
			HashSet<Long> set = new HashSet<>();
			for(int i=0;i<binary.length();i++)
			{
				if(binary.charAt(binary.length()-1-i)=='1')
				{
					set.add(Bto10-(1<<i));
				}
				else
				{
					set.add(Bto10+(1<<i));
				}
			}
			long answer = 0l;
			long pow = 1l;
			for(int i=0;i<trinary.length();i++)
			{
				long temp = Tto10;
				
				long read = trinary.charAt(trinary.length()-1-i)-'0';
				temp-=pow*read;
				long next1 = (read+1)%3;
				long next2 = (read+2)%3;
				if(set.contains(temp+pow*next1))
				{
					answer=temp+pow*next1;
					break;
				}
				else if(set.contains(temp+pow*next2))
				{
					answer=temp+pow*next2;
					break;
				}
				
				
				
				pow*=3;
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}
