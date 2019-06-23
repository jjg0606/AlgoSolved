package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1873 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		StringTokenizer stk;
		for(int tc=1;tc<=testCase;tc++)
		{
			stk=new StringTokenizer(br.readLine());
			int height = Integer.parseInt(stk.nextToken());
			int width = Integer.parseInt(stk.nextToken());
			char[][] map = new char[height][width];
			int tankdirection = -1; // 전차가 바라보는 위치 // 위 아래 좌 우
			char[] tankrotation = {'^','v','<','>'};
			int[] Dr = {-1,1,0,0};
			int[] Dc = {0,0,-1,1};
			int tankrow =0; //전차의 위치
			int tankcol = 0;
			for(int i=0;i<height;i++)
			{
				String read = br.readLine().trim();
				for(int j=0;j<width;j++)
				{
					map[i][j] = read.charAt(j);
					if(tankdirection!=-1)
					{
						continue;
					}
					for(int k=0;k<4;k++)
					{
						if(map[i][j]==tankrotation[k])
						{
							map[i][j]='.';
							tankdirection=k;
							tankrow=i;
							tankcol=j;
							break;
						}
					}
				}
			}
			
			int insertionNum = Integer.parseInt(br.readLine());
			char[] command = br.readLine().toCharArray();
			//insertion end
			int insertionIndex = 0;
			
			while(insertionIndex<insertionNum)
			{
				switch (command[insertionIndex]) {
				case 'U': // 위로 이동
					tankdirection=0;
					if(tankrow-1>=0&&map[tankrow-1][tankcol]=='.')
					{
						tankrow--;						
					}
					break;
				case 'D': // 아래로 이동
					tankdirection=1;
					if(tankrow+1<height&&map[tankrow+1][tankcol]=='.')
					{
						tankrow++;						
					}
					break;
				case 'L':	// 왼쪽으로 이동
					tankdirection=2;
					if(tankcol-1>=0&&map[tankrow][tankcol-1]=='.')
					{
						tankcol--;						
					}
					break;
				case 'R':	// 오른쪽으로 이동
					tankdirection=3;
					if(tankcol+1<width&&map[tankrow][tankcol+1]=='.')
					{
						tankcol++;						
					}
					break;
				case 'S':	// 발사
					int range = 1;
					outer : while(true)
					{
						int bulletrow=tankrow+range*Dr[tankdirection];
						int bulletcol=tankcol+range*Dc[tankdirection];
						if(bulletrow<0||bulletrow>=height||bulletcol<0||bulletcol>=width)
						{
							break;
						}
						switch(map[bulletrow][bulletcol])
						{
							case '.':
								break;
							case '*':
								map[bulletrow][bulletcol]='.';
								break outer;
							case '#':
								break outer;
							case '-':
								break;
						}
						range++;
					}
					break;

				}//슈팅때
				insertionIndex++;
			}// 명령 분기문
			map[tankrow][tankcol] = tankrotation[tankdirection];
			StringBuilder sb = new StringBuilder();			
			for(int i=0;i<height;i++)
			{
				if(i!=0)
				{
					sb.append('\n');
				}
				for(int j=0;j<width;j++)
				{
					sb.append(map[i][j]);
				}				
			}
			System.out.println("#"+tc+" "+sb.toString());
		}//tc loop
	}
}
