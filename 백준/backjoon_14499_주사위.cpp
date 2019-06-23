#include <iostream>
#include <vector>
using namespace std;

class Dice
{
public:
	int top;
	int bottom;
	int right;
	int left;
	int forward;
	int backward;
	void(Dice::*rollptr[4])();

	Dice() : top(0), bottom(0), right(0), left(0), forward(0), backward(0)
	{
		rollptr[0] = &Dice::rollright;
		rollptr[1] = &Dice::rollleft;
		rollptr[2] = &Dice::rollforward;
		rollptr[3] = &Dice::rollbackward;
	}

	int doCommand(int rollrotation,int mapnum)
	{
		(this->*rollptr[rollrotation - 1])();

		if (mapnum == 0)
		{
			return bottom;
		}
		else
		{
			bottom = mapnum;
			return 0;
		}
	}

	void rollright() // 오른쪽으로 구름
	{
		int temp = right;
		right = top;
		top = left;
		left = bottom;
		bottom = temp;
	}
	void rollleft() // 왼쪽
	{
		int temp = left;
		left = top;
		top = right;
		right = bottom;
		bottom = temp;
	}
	void rollforward() // 위
	{
		int temp = forward;
		forward = top;
		top = backward;
		backward = bottom;
		bottom = temp;
	}
	void rollbackward() // 아래
	{
		int temp = backward;
		backward = top;
		top = forward;
		forward = bottom;
		bottom = temp;
	}
};

int dr[5] = {0,0,0,-1,1};
int dc[5] = {0,1,-1,0,0};

int main()
{
	int map[20][20];
	
	int rows, cols, posr, posc, comnum;
	cin >> rows >> cols >> posr >> posc >> comnum;
	int* command = new int[comnum];
	for (int i = 0; i < comnum; i++)
	{
		command[i] = 0;
	}
	
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
		{
			cin >> map[i][j];
		}
	}
	for (int i = 0; i < comnum; i++)
	{
		cin >> command[i];
	}
	//insertion end;
	Dice dice;
	for (int i = 0; i < comnum; i++)
	{
		int nextrow = posr + dr[command[i]];
		int nextcol = posc + dc[command[i]];
		if (nextrow < 0 || nextrow >= rows || nextcol < 0 || nextcol >= cols)
		{
			continue;
		}
		int bottomnum = map[nextrow][nextcol];
		map[nextrow][nextcol]=dice.doCommand(command[i], bottomnum);
		cout<<dice.top<<'\n';
		posr = nextrow;
		posc = nextcol;
	}
	cout.flush();
	delete[] command;
	return 0;
}