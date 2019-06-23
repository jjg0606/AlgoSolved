#include <iostream>
#include <vector>
#include <string>
using namespace std;
//11723 С§Че

int S;
int fulfilled;
void add(int x)
{
	S |= 1 << (x - 1);
}

void remove(int x)
{
	int removing = fulfilled ^ (1 << (x - 1));
	S &= removing;
}

void check(int x)
{
	int checking = S & (1 << (x - 1));
	if (checking==0)
	{
		cout << 0 << '\n';
	}
	else
	{
		cout << 1 << '\n';
	}
}

void toggle(int x)
{
	S ^= 1 << (x - 1);
}

void all()
{
	S = fulfilled;
}

void empty()
{
	S = 0;
}

int main()
{
	ios::sync_with_stdio(false); 
	cin.tie(NULL); 
	cout.tie(NULL);
	S = 0;
	fulfilled = 0XFFFFF;
	int N;
	cin >> N;
	int M;
	for (int i = 0; i < N; i++)
	{
		string read;
		cin >> read;
		switch (read[1])
		{
		case 'd':
			cin >> M;
			add(M);
			break;
		case 'e':
			cin >> M;
			remove(M);
			break;
		case 'h':
			cin >> M;
			check(M);
			break;
		case 'o':
			cin >> M;
			toggle(M);
			break;
		case 'l':
			all();
			break;
		case 'm':
			empty();
			break;
		default:
			break;
		}
	}
	cout.flush();
	return 0;
}

