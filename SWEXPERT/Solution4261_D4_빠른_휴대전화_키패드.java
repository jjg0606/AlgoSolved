package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution4261_D4_빠른_휴대전화_키패드 {
	static String[] keys = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"}; 	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ansb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			StringTokenizer stk = new StringTokenizer(br.readLine());
			String Pressed=stk.nextToken();
			int[] KeyAt= new int[Pressed.length()];
			for(int i=0;i<KeyAt.length;i++)
			{
				KeyAt[i] = Pressed.charAt(i)-'0';
			}
			
			int DicNum = Integer.parseInt(stk.nextToken());			
			stk = new StringTokenizer(br.readLine());
			int answer = 0;			
			// insertion end
			outer : for(int i=0;i<DicNum;i++)
			{
				String read = stk.nextToken();
				if(read.length()!=Pressed.length())
				{
					continue;
				}
				for(int c=0;c<Pressed.length();c++)
				{
					boolean isInSide = false;
					char readat = read.charAt(c);
					for(int k=0;k<keys[KeyAt[c]].length();k++)
					{
						if(keys[KeyAt[c]].charAt(k)==readat)
						{
							isInSide=true;
							break;
						}
					}
					if(!isInSide)
					{
						continue outer;
					}
				}
				answer++;
			}
			
			// find			
			ansb.append('#').append(tc).append(' ').append(answer).append('\n');
		}// tc loop
		bw.write(ansb.toString());
		bw.flush();
	}
}
