package Solving04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main5373_큐빙 {
	static class Cube
	{
		Cell[] cells;
		public Cube()
		{
			cells = new Cell[9*6];
			int cellindex = 0;
			//앞면, 뒷면
			for(int x=-1;x<=1;x++)
			{
				for(int z=-1;z<=1;z++)
				{
					cells[cellindex++]=new Cell(new Vec3(x,-1,z),new Vec3(0,-1,0),'r');
					cells[cellindex++]=new Cell(new Vec3(x,1,z),new Vec3(0,1,0),'o');
				}
			}
			// 윗면 아랫면
			for(int x=-1;x<=1;x++)
			{
				for(int y=-1;y<=1;y++)
				{
					cells[cellindex++]=new Cell(new Vec3(x,y,1),new Vec3(0,0,1),'w');
					cells[cellindex++]=new Cell(new Vec3(x,y,-1),new Vec3(0,0,-1),'y');
				}
			}
			
			// 오른쪽 왼쪽
			for(int z=-1;z<=1;z++)
			{
				for(int y=-1;y<=1;y++)
				{
					cells[cellindex++]=new Cell(new Vec3(1,y,z),new Vec3(1,0,0),'b');
					cells[cellindex++]=new Cell(new Vec3(-1,y,z),new Vec3(-1,0,0),'g');
				}
			}
		}
	
		/**
		 * 
		 * @param plane 회전 할 평면 0:xy, 1:zx, 2:yz
		 * @param isPlus 증가하는방향
		 * @param iscw 시계방향,반시계 방향 true 시계, false 반시계
		 */
		public void Rotate(int plane,boolean isPlus,boolean iscw)
		{
			int rx=0;
			int ry=0;
			int rz=0; // 축
			switch(plane)
			{
			case 0: // xy 평면
				rz= isPlus ? 1:-1;
				for(int i=0;i<cells.length;i++)
				{
					if(cells[i].pos.same(rx, ry, rz))
					{
						cells[i].pos.RotateXY(!(isPlus^iscw));
						cells[i].metho.RotateXY(!(isPlus^iscw));
					}
				}
				break;
			case 1: // xz 평면
				ry=isPlus ? 1:-1;
				for(int i=0;i<cells.length;i++)
				{
					if(cells[i].pos.same(rx, ry, rz))
					{
						cells[i].pos.RotateZX(!(isPlus^iscw));
						cells[i].metho.RotateZX(!(isPlus^iscw));
					}
				}
				break;
			case 2: // yz 평면
				rx= isPlus ? 1: -1;
				for(int i=0;i<cells.length;i++)
				{
					if(cells[i].pos.same(rx, ry, rz))
					{
						cells[i].pos.RotateYZ(!(isPlus^iscw));
						cells[i].metho.RotateYZ(!(isPlus^iscw));
					}
				}
				break;
			}			
			
		}
	
		public void Print(StringBuilder ansb)
		{
			Cell[] temp = new Cell[9];
			int celldex = 0;
			for(int i=0;i<cells.length;i++)
			{
				if(cells[i].metho.isLookingTop())
				{
					temp[celldex++]=cells[i];
				}
			}
			Arrays.sort(temp,new Comparator<Cell>() {

				@Override
				public int compare(Cell o1, Cell o2) {
					// TODO Auto-generated method stub
					if(o1.pos.y==o2.pos.y)
					{
						return o1.pos.x-o2.pos.x;
					}
					else
					{
						return o2.pos.y-o1.pos.y;
					}
				}				
			});
			
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					ansb.append(temp[3*i+j].color);
				}
				ansb.append('\n');
			}
		}
	}
	
	
	static class Cell
	{
		Vec3 pos;   // 위치 벡터
		Vec3 metho; // 법선 벡터
		char color; // 색깔
		public Cell(Vec3 pos,Vec3 metho,char color)
		{
			this.pos=pos;
			this.metho=metho;
			this.color=color;
		}
	}
	
	static class Vec3
	{
		int x;
		int y;
		int z;
		public Vec3(int x,int y,int z)
		{
			this.x=x;
			this.y=y;
			this.z=z;
		}
		
		public boolean same(int rx,int ry,int rz)
		{
			if(rx!=0)
			{
				if(rx==x)
				{
					return true;
				}
				return false;
			}
			else if(ry!=0)
			{
				if(ry==y)
				{
					return true;
				}
				return false;
			}
			else if(rz!=0)
			{
				if(rz==z)
				{
					return true;
				}
				return false;
			}
			return false;
		}
		
		public boolean isLookingTop()
		{
			if(z==1)
			{
				return true;
			}
			return false;
		}

		
		public void RotateXY(boolean isCW)
		{
			int tempx=0;
			int tempy=0;
			if(isCW) // xy 평면 시계방향회전
			{
				tempy = -x;
				tempx=y;
			}
			else
			{
				tempy=x;
				tempx=-y;
			}
			this.x=tempx;
			this.y=tempy;
		}
		
		public void RotateZX(boolean isCW)
		{
			int tempz=0; // x->z
			int tempx=0; // y->x
			if(isCW) // zx평면
			{
				tempx = -z;
				tempz=x;
			}
			else
			{
				tempx=z;
				tempz=-x;
			}
			this.z=tempz;
			this.x=tempx;
		}
		
		public void RotateXZ(boolean isCW)
		{
			int tempx=0;
			int tempz=0;
			if(isCW) // xz 평면 시계방향회전
			{
				tempz = -x;
				tempx=z;
			}
			else
			{
				tempz=x;
				tempx=-z;
			}
			this.x=tempx;
			this.z=tempz;
		}
		
		public void RotateYZ(boolean isCW)
		{
			int tempy=0;
			int tempz=0;
			if(isCW) // xz 평면 시계방향회전
			{
				tempz = -y;
				tempy=z;
			}
			else
			{
				tempz=y;
				tempy=-z;
			}
			this.y=tempy;
			this.z=tempz;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder ansb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine().trim());
		for(int tc=1;tc<=testCase;tc++)
		{
			int turning = Integer.parseInt(br.readLine().trim());
			Cube cube = new Cube();
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for(int i=0;i<turning;i++)
			{
				String read = stk.nextToken();
				int plane = 0; //  0:xy, 1:xz, 2:yz 
				boolean isPlus=false;
				boolean isCW=false;
				switch (read.charAt(0)) {
				case 'U':
					plane=0;
					isPlus=true;
					break;
				case 'D':
					plane=0;
					isPlus=false;
					break;
				case 'F':
					plane=1;
					isPlus=false;
					break;
				case 'B':
					plane=1;
					isPlus=true;
					break;
				case 'L':
					plane=2;
					isPlus=false;
					break;
				case 'R':
					plane=2;
					isPlus=true;
					break;					
				default:
					break;
				}
				
				switch(read.charAt(1))
				{
				case '+':
					isCW=true;
					break;
				case '-':
					isCW=false;
					break;
				}
				
				cube.Rotate(plane, isPlus, isCW);
			}
			
			cube.Print(ansb);
		}// tc loop
		bw.write(ansb.toString());
		bw.flush();
	}
}
