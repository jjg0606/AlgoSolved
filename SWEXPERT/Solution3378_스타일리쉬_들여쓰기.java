package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution3378_스타일리쉬_들여쓰기 {
	static boolean[][][] table;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=testCase;tc++)
		{
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int master = Integer.parseInt(stk.nextToken());
			int mine = Integer.parseInt(stk.nextToken());
			int[][] calcul = new int[master][4];
			for(int i=0;i<master;i++)
			{
				tableInit();
				
				String read = br.readLine().trim();
				boolean isSpaceEnd = false;
				for(int j=0;j<read.length();j++)
				{
					switch(read.charAt(j))
					{
					case '(' :
						calcul[i][0] ++;
						isSpaceEnd=true;
						break;
					case ')':
						calcul[i][0]--;
						isSpaceEnd=true;
						break;
					case '{':
						calcul[i][1]++;
						isSpaceEnd=true;
						break;
					case '}':
						calcul[i][1]--;
						isSpaceEnd=true;
						break;
					case '[':
						calcul[i][2]++;
						isSpaceEnd=true;
						break;
					case ']':
						calcul[i][2]--;
						isSpaceEnd=true;
						break;
					case '.':
						if(!isSpaceEnd)
						{
							calcul[i-1][3]++;
						}
						break;
					default:
						isSpaceEnd=true;
						break;
					}
				}
				if(i+1<master)
				{
					calcul[i+1][0] = calcul[i][0];
					calcul[i+1][1] = calcul[i][1];
					calcul[i+1][2] = calcul[i][2];
				}
			}// master reading
			for(int i=0;i<master-1;i++)
			{
				setTable(calcul[i][0], calcul[i][1], calcul[i][2], calcul[i][3]);
			}
			// setTable
			
			
			
			// collect answer
			int[][] mycul = new int[mine][3];
			for(int i=0;i<mine;i++)
			{
				String read = br.readLine().trim();
				for(int j=0;j<read.length();j++)
				{
					switch(read.charAt(j))
					{
					case '(' :
						mycul[i][0] ++;
						break;
					case ')':
						mycul[i][0]--;
						break;
					case '{':
						mycul[i][1]++;
						break;
					case '}':
						mycul[i][1]--;
						break;
					case '[':
						mycul[i][2]++;
						break;
					case ']':
						mycul[i][2]--;
						break;
					default:
						break;
					}
				}
				if(i+1<mine)
				{
					mycul[i+1][0] = mycul[i][0];
					mycul[i+1][1] = mycul[i][1];
					mycul[i+1][2] = mycul[i][2];
				}
			}//mine reading
			sb.append('#').append(tc).append(' ').append(0).append(' ');
			for(int i=0;i<mine-1;i++)
			{
				sb.append(getValue(mycul[i][0], mycul[i][1], mycul[i][2]));
				sb.append(' ');
			}
			sb.append('\n');

		}// tc loop
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void tableInit()
	{
		table = new boolean[21][21][21];
		for(int s=1;s<=20;s++)
		{
			for(int m=1;m<=20;m++)
			{
				for(int l=1;l<=20;l++)
				{
					table[s][m][l]=true;
				}
			}
		}
	}
	
	static void setTable(int small,int medium,int large,int value)
	{
		for(int s=1;s<=20;s++)
		{
			for(int m=1;m<=20;m++)
			{
				for(int l=1;l<=20;l++)
				{
					if(!table[s][m][l])
					{
						continue;
					}
					if(s*small+m*medium+l*large!=value)
					{
						table[s][m][l]=false;
					}
				}
			}
		}
	}
	
	static int getValue(int small,int medium,int large)
	{
		int getVal = -1;
		for(int s=1;s<=20;s++)
		{
			for(int m=1;m<=20;m++)
			{
				for(int l=1;l<=20;l++)
				{
					if(!table[s][m][l])
					{
						continue;
					}
					int tempval = s*small+m*medium+l*large;
					if(getVal==-1)
					{
						getVal=tempval;
					}
					else if(getVal!=tempval)
					{
						return -1;
					}
				}
			}
		}
		return getVal;
	}

}

