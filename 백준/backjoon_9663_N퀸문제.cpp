#include <iostream>
#include <vector>
using namespace std;

class Queen
{
public:
	float row;
	float col;

	Queen(int _row,int _col) : row(_row), col(_col)
	{}

	bool isCollide(float oR,float oC)
	{
		float divide = (col - oC) / (row - oR);
		if (divide == -1.0f || divide == 0.0f || divide == 1.0f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
};


// Nqueen 문제 풀다 말음
int main()
{
	int N;
	cin >> N;
	int cnt = 0;
	int firstcol = 0;
	vector<Queen> stack;
	while (firstcol < N) // firstrow
	{

		stack.push_back(Queen(0, firstcol++));
		int currow = 0;
		int curcol = 0;
	
		while (!stack.empty())
		{
			currow = stack.back().row + 1;
			if (currow >= N)
			{
				cnt++;
				curcol = N;
			}
			//insert
			while (curcol < N)
			{
				for (auto it = stack.begin(); it != stack.end(); it++)
				{
					if (it->isCollide(currow, curcol)) // 충돌 검사
					{
						curcol++;
						goto cont; // 충돌이 발생하면 다시 시작
					}
				}
				// 충돌 없이 루프를 탈출한 경우
				{
					stack.push_back(Queen(currow,curcol));
					curcol = 0;
					goto end;
				}				
				cont: // 루프의 시작으로 돌아감
					continue;
			};

				// 스택에서 원소를 제거

			do
			{
				curcol = stack.back().col+1;
				stack.pop_back();
			} while (curcol >= N&&!stack.empty());
		
		end: 
			continue;
		}//big while
	}// first row

	cout << cnt << endl;
	return 0;
}
