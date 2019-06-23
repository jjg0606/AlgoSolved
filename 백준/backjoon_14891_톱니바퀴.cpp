#include <iostream>
#include <vector>
using namespace std;

class Gear
{
public :
	int top;
	int pole[8]; // 1�̸� S��
	void setPole(int polearr) // ���� �Է¹���
	{
		for (int i = 7; i >= 0; i--)
		{
			if (polearr&(1) > 0)
			{
				pole[i] = 1;
			}
			else
			{
				pole[i] = 0;
			}
			polearr /= 10;
		}
	}
	void rotateCW() // �ð����ȸ��
	{
		top = (top + 7) % 8;
		
	}
	void rotateCCW() // �ݽð���� ȸ��
	{
		top = (top + 1) % 8;
	}

	int getRight() // �������̶� �´��� ��
	{
		return pole[(top + 2) % 8];
	}
	int getLeft() // �����̶� �´��� ��
	{
		return pole[(top + 6) % 8];
	}
	int getTop()
	{
		return pole[top];
	}
	Gear() : top(0){}
};



int command[4];

void initcommand()
{
	for (int i = 0; i < 4; i++)
	{
		command[i] = 0;
	}
}

int main()
{
	Gear geararr[4];
	for (int i = 0; i < 4; i++)
	{
		int insertion;
		cin >> insertion;
		geararr[i].setPole(insertion);
	}
	int rotationNum = 0;
	cin >> rotationNum;
	vector<pair<int, int>> rotationvec;
	for (int i = 0; i < rotationNum; i++)
	{
		int gearnum;
		int rot;
		cin >> gearnum >> rot;
		rotationvec.push_back(make_pair(gearnum-1,rot));
	}
	// insertion end
	
	for (int i = 0; i < rotationNum; i++)
	{
		pair<int, int>& comnow = rotationvec[i];
		command[comnow.first] = comnow.second;

		for (int j = comnow.first; j < 3; j++)
		{
			if (geararr[j].getRight()+geararr[j + 1].getLeft() == 1)
			{
				command[j + 1] = -command[j];
			}
			else
			{
				break;
			}
		}
		for (int j = comnow.first; j > 0; j--)
		{
			if (geararr[j].getLeft()+geararr[j - 1].getRight() == 1)
			{
				command[j - 1] = -command[j];
			}
			else
			{
				break;
			}
		}
		for (int j = 0; j < 4; j++)
		{
			if (command[j] == 1)
			{
				geararr[j].rotateCW();
			}
			else if (command[j] == -1)
			{
				geararr[j].rotateCCW();
			}
		}
		initcommand();
	}
	//ȸ�� ó�� ����
	//�������
	int score = 0;
	for (int i = 0; i < 4; i++)
	{
		score+=(1 << i)*geararr[i].getTop();
	}
	cout << score << endl;
	return 0;
}