package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution4672_수진이의_펠린드롬 {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ansb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			String read = br.readLine().trim();
			int length = read.length();
			int answer = 0;
			char[] count = new char['z'-'a'+1];
			for(int i=0;i<length;i++)
			{
				count[read.charAt(i)-'a']++;
			}
			for(int i=0;i<count.length;i++)
			{
				if(count[i]==0)
				{
					continue;
				}
				for(int j=1;j<=count[i];j++)
				{
					answer += count[i]-j+1;
				}
			}		
			
			ansb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		bw.write(ansb.toString());
		bw.flush();
	}
}
