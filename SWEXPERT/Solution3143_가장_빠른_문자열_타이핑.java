package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3143_가장_빠른_문자열_타이핑 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk=new StringTokenizer(br.readLine());
			String origin = stk.nextToken();
			String shortcut = stk.nextToken();
			int sl = shortcut.length();
			int[] bt = new int[128];
			for(int i=0;i<sl-1;i++)
			{
				bt[shortcut.charAt(i)]=sl-i-1;
			}
//			bt[shortcut.charAt(shortcut.length()-1)]=1;
			int index = sl-1;
			char last = shortcut.charAt(sl-1);
			int cnt = 0;
			outer : while(index<origin.length())
			{
				char cur = origin.charAt(index);
				if(cur==last)
				{
					//탐색시작
					for(int i=1;i<sl;i++)
					{
						if(origin.charAt(index-i)!=shortcut.charAt(sl-1-i))
						{
							//안맞음
							if(bt[last]==0)
							{
								index+=sl;
							}
							else
							{
								index+=bt[last];
							}
							continue outer;
						}
					}
					cnt++;
					index+=sl;
				}
				else if(bt[cur]==0)
				{
					index+=sl;
				}
				else
				{
					index+=bt[cur];
				}				
			}
			
			int answer = origin.length()-sl*cnt+cnt;
			System.out.println("#"+tc+" "+answer);
		}//tc loop
	}
}
