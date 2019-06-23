package Solving;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
public class Main2606 {
	
	static int M;
	static int N;
	static int H;
	
	static int dm[]= {1,-1,0,0,0,0};
	static int dn[]= {0,0,1,-1,0,0};
	static int dh[]= {0,0,0,0,1,-1};
	
	static int[] valtohnm(int value)
	{
		int[] hnm = new int[3];
		hnm[0] = value/(N*M);
		value%=(N*M);
		hnm[1] = value/N;
		hnm[2] = value%N;		
		return hnm;
	}
	
	static int hnmtoval(int h,int n,int m)
	{
		int reval = 0;
		reval+=h*N*M;
		reval+=N*n;
		reval+=m;
		
		return reval;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		M = Integer.parseInt(stk.nextToken()); // 열
		N = Integer.parseInt(stk.nextToken()); // 행
		H= Integer.parseInt(stk.nextToken()); // 높이
		
		int[][][] map = new int[H][N][M];
		
		ArrayList<Integer> ripe = new ArrayList<>();
		HashSet<Integer> vacum = new HashSet<>();
		int left =0;
		for(int i=0;i<H;i++)
		{
			for(int j=0;j<N;j++)
			{
				stk = new StringTokenizer(br.readLine());
				for(int k=0;k<M;k++)
				{
					int read = Integer.parseInt(stk.nextToken());
					map[i][j][k] = read;
					if(read==1) 
					{
						ripe.add(hnmtoval(i, j, k));
					}
					else if(read==-1)
					{
						vacum.add(hnmtoval(i,j,k));
					}
					else
					{
						left++;
					}
				}
			}
		}
		// insertion end
		TreeSet<Integer> insert = new TreeSet<>();
		
		int dayrequired = 0;
		while(left>0)
		{
			dayrequired++;
			for (Integer val : ripe) {
				int[] hnm = valtohnm(val);
				for(int i=0;i<6;i++)
				{
					int nexth = hnm[0]+dh[i];
					int nextn = hnm[1]+dn[i];
					int nextm = hnm[2]+dm[i];
					if(nexth<0||nexth>=H||nextn<0||nextn>=N||nextm<0||nextm>=M) // 인덱스 밖
					{
						continue;
					}
					if(vacum.contains((Integer)hnmtoval(nexth, nextn, nextm))) //빈공간일때
					{
						continue;
					}
					insert.add(hnmtoval(nexth, nextn, nextm));
				}
			}
			if(insert.isEmpty())
			{
				dayrequired=-1;
				break;
			}
			while(!insert.isEmpty())
			{
				ripe.add(insert.pollFirst());
				left--;
			}
		}
		for (Integer integer : ripe) {
			System.out.println(integer);
		}
		System.out.println(dayrequired);
	}
}
