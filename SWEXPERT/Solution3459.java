package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution3459 {
	static String[] winner = {"Alice","Bob"};	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			 long number = Long.parseLong(br.readLine().trim());			 
			 int winnerindex = 1;
			 long index = 1;
			 long width = 1;
			 while(true)
			 {
				 if(index>=number)
				 {
					 break;
				 }
				 if(winnerindex==1)
				 {
					 width*=4;
				 }
				 index+=width;
				 winnerindex=(winnerindex+1)%2;
			 }
			 System.out.println("#"+tc+" "+winner[winnerindex]);
		}//tc loop
	}
}
