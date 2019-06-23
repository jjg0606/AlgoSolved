#include <iostream>
#include <vector>
using namespace std;
//15652 N°ú M(4)

int N, M;
int* order;

void combination(int prv,int ord)
{
	if (ord == M)
	{
		for (int o = 0; o < M; o++)
		{
			cout << order[o] << ' ';
		}
		cout << '\n';
		return;
	}

	for (int i = prv; i <= N; i++)
	{
		order[ord] = i;
		combination(i, ord + 1);
	}
}

int main()
{
	cin >> N >> M;
	order = new int[M];
	combination(1, 0);
	cout.flush();
	delete[] order;
	return 0;
}

