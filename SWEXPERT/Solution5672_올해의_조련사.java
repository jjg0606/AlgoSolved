package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Solution5672_올해의_조련사 {
	
	static LinkedList<Character> answer = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int BirdNum = Integer.parseInt(br.readLine().trim());
			ArrayList<Character> list = new ArrayList<>();
			
			for(int i=0;i<BirdNum;i++)
			{
				list.add(br.readLine().trim().charAt(0));
			}
			// insertion end
			int first = 0;
			int last = list.size()-1;
			while(answer.size()!=BirdNum)
			{
				if(list.get(first)>list.get(last))
				{
					answer.add(list.get(last));
					last--;
				}
				else if(list.get(first)<list.get(last))
				{
					answer.add(list.get(first));
					first++;
				}
				else
				{
					int first2 = first+1;
					int last2 = last-1;
					boolean isPopfirst=true;
					while(first2<last2)
					{
						if(list.get(first2)>list.get(last2))
						{
							isPopfirst=false;
							break;
						}
						else if(list.get(first2)<list.get(last2))
						{
							isPopfirst=true;
							break;
						}
						else
						{
							first2++;
							last2--;
						}
					}
					
					if(isPopfirst)
					{
						answer.add(list.get(first));
						first++;
					}
					else
					{
						answer.add(list.get(last));
						last--;
					}
				}
			}// queing loop
			
			sb.append('#').append(tc).append(' ');
			while(!answer.isEmpty())
			{
				sb.append(answer.pollFirst());
			}
			sb.append('\n');
		}// tc loop
		bw.write(sb.toString());
		bw.flush();
	}
}
