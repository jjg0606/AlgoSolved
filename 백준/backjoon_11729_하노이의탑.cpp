#include <iostream>
#include <vector>
using namespace std;

int count = 0;
vector<int> vec;

void hanoi(int disk, int from, int to,int extra)
{
	if (disk == 0)
	{
		return;
	}

	hanoi(disk - 1, from, extra,to);
	vec.push_back(from);
	vec.push_back(to);
	hanoi(disk - 1, extra, to, from);
	::count++;
}


int main()
{
	int N;
	cin >> N;
	hanoi(N, 1, 3,2);
	cout << ::count << '\n';
	auto it = vec.begin();
	while (it!=vec.end())
	{
		cout << *it << ' ' ;
		it++;		
		cout << *it << '\n';
		it++;
	}
	return 0;
}