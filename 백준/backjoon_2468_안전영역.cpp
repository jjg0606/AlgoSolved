#include <iostream>
#include <set>
#include <stack>


using namespace std;

int map[101][101];
int visited[101][101];
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

void init()
{
	for (int i = 0; i < 101; i++)
	{
		for (int j = 0; j < 101; j++)
		{
			map[i][j] = 0;
			visited[i][j] = 0;
		}
	}
} // 배열 초기화

void initv()
{
	for (int i = 0; i < 101; i++)
	{
		for (int j = 0; j < 101; j++)
		{
			visited[i][j] = 0;
		}
	}
} // visited 배열 초기화


int main()
{
	int N;
	cin >> N;

	init();

	set<int> set;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			int num;
			cin >> num;
			map[i][j] = num;
			set.insert(num);
		}
	}
	// insertion
	auto it = set.begin();
	auto end = set.end();
	end--; // 마지막은 어차피 물에 다 잠기니까 한칸 앞으로
	stack<pair<int, int>> stack;
	int max = 1;

	while (it != end)
	{
		int curwater = *it;
		
		int landcount = 0;
		// water 
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (map[i][j] <= curwater)
				{
					visited[i][j] = 1;
				}
			}
		}
		//
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (visited[i][j] != 0)
				{
					continue;
				}
				// dfs 탐색 시작
				landcount++;
				stack.push(make_pair(i, j));
				//cout << "start search " << i << ", " << j << '\n';
				while (!stack.empty())
				{
					pair<int, int> p = stack.top();					
					stack.pop();
					visited[p.first][p.second] = 1;
					for (int d = 0; d < 4; d++)
					{
						int nextx = p.first + dx[d];
						int nexty = p.second + dy[d];
						if (nextx < 0 || nextx >= N || nexty < 0 || nexty >= N || visited[nextx][nexty] == 1)
						{
							continue;
						}
						stack.push(make_pair(nextx, nexty));
					}					
				}
			}
		}
		//cout<<"ended height : "<< curwater << ", land :"<<landcount<<endl;
		if (landcount > max)
		{
			max = landcount;
		}
		initv();
		it++;
	}

	cout << max;
	cout.flush();
	return 0;
}