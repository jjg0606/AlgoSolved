package Solving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2247_도서관 {
	static class Student
	{
		int in;
		int out;
		public Student(int in,int out)
		{
			this.in=in;
			this.out= out;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		Student[] stuarr = new Student[N];
		StringTokenizer stk;
		for(int i=0;i<N;i++)
		{
			stk = new StringTokenizer(br.readLine());
			stuarr[i] = new Student(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
		}
		Arrays.sort(stuarr, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				if(o1.in==o2.in)
				{
					return o2.out-o1.out;
				}
				return o1.in-o2.in;
			}			
		});
		int prvin;
		int prvout;
		int leftmax=0;
		int nomax=0;
		prvin = stuarr[0].in;
		prvout = stuarr[0].out;
		for(int i=1;i<N;i++)
		{
			int curin = stuarr[i].in;
			int curout = stuarr[i].out;
			if(curin<=prvout&&curout>prvout)
			{
				prvout=curout;
				continue;
			}
			else if(curin>prvout)
			{
				leftmax = Math.max(prvout-prvin, leftmax);
				nomax = Math.max(curin-prvout, nomax);
				prvin = curin;
				prvout = curout;
			}
		}
		leftmax=Math.max(leftmax, prvout-prvin);
		System.out.println(leftmax+" "+nomax);
	}// main
}
