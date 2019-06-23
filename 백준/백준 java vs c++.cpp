#include<iostream>
#include<string>
using namespace std;

int main()
{
	bool isJavaType = false;
	bool isCppType = false;
	int diff = 'a' - 'A';
	string insert;
	string output;
	cin >> insert;
	if (insert[0] == '_' || ('A'<= insert[0] &&insert[0] <= 'Z')||insert[insert.length()-1]=='_')
	{
		cout << "Error!" << endl;
		return 0;
	}
	

	for (int i = 0; i < insert.length(); i++)
	{
		if (isJavaType&&isCppType)
		{
			cout << "Error!" << endl;
			return 0;
		}
		const char cur = insert.at(i);

		if ('a' <= cur && cur <= 'z')
		{
			output.push_back(cur);
		}
		else if (cur == '_')
		{
			isCppType = true;
			if (i + 1 < insert.length())
			{
				if (insert[i + 1] == '_' || insert[i + 1] <= 'Z')
				{
					cout << "Error!" << endl;
					return 0;
				}
				output.push_back(insert.at(i + 1) - diff);
				i++;
			}			
		}
		else if ('A'<=cur&&cur <= 'Z')
		{
			isJavaType = true;
			output.push_back('_');
			output.push_back(cur + diff);
		}
	}

	if (isJavaType&&isCppType)
	{
		cout << "Error!" << endl;
		return 0;
	}
	cout << output << endl;
	



	return 0;
}