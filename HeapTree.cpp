#include "HeapTree.h"

void HeapTree::swap(int index1, int index2)
{
	int temp = this->innerArr[index1];
	this->innerArr[index1] = this->innerArr[index2];
	this->innerArr[index2] = temp;
}

int HeapTree::getSmallerChild(int parent)
{
	// child must be smaller than parent
	// if there are no child return parent
	// block out of index
	if (getLeftChild(parent) >= this->maxIndex)
	{
		return parent;
	}

	int leftChild = innerArr[getLeftChild(parent)];
	leftChild = 
		leftChild == Empty || leftChild >= innerArr[parent] ?
		0 : leftChild;

	int rightChild = innerArr[getRightChild(parent)];
	rightChild = 
		rightChild == Empty || rightChild >= innerArr[parent]?
		0 : rightChild;

	if (leftChild <= rightChild)
	{
		rightChild = 0;
	}
	else
	{
		leftChild = 0;
	}
	
	if (leftChild != 0)
	{
		return getLeftChild(parent);
	}
	else if (rightChild != 0)
	{
		return getRightChild(parent);
	}
	else
	{
		return parent;
	}
};

int HeapTree::getParent(int index)
{
	return (index - 1) / 2;
};

int HeapTree::getRightChild(int index)
{
	return (index + 1) * 2;
};

int HeapTree::getLeftChild(int index)
{
	return (index + 1) * 2 - 1;
};

HeapTree::HeapTree(int depth) : maxIndex((int)1 <<depth), innerSize(0)
{
	this->innerArr = new int[maxIndex-1];
	for (int i = 0; i < this->maxIndex; i++)
	{
		this->innerArr[i] = HeapTree::Empty;
	}
	std::cout << "HeapTree maded maxindex = " << this->maxIndex - 1 << '\n';
};

HeapTree::HeapTree() : HeapTree(10)
{
};

HeapTree::~HeapTree()
{
	delete[] this->innerArr;
};

void HeapTree::Print()
{
	for (int i = 0; i < this->innerSize; i++)
	{
		std::cout << this->innerArr[i] << ' ';
	}
	std::cout << '\n' << "print end" << '\n';
};

int HeapTree::getSize()
{
	return this->innerSize;
};

int HeapTree::Peek()
{
	return this->innerArr[0];
};

bool HeapTree::push(int value)
{
	// full
	if (this->innerSize == this->maxIndex-1|| value <=0)
	{
		return false;
	}
	// insert to back
	int curindex = this->innerSize;
	innerArr[curindex] = value;
	this->innerSize++;
	// changing algorithm
	while (curindex > 0)
	{
		int parentIndex = getParent(curindex);
		if (innerArr[curindex] >= innerArr[parentIndex])
		{
			return true;
			
		}

		swap(curindex, parentIndex);
		curindex = parentIndex;
	}

	return true;
}

int HeapTree::pop()
{
	if (this->innerSize <= 0)
	{
		return Empty;
	}

	int revalue = innerArr[0];
	innerArr[0] = Empty;
	swap(0, this->innerSize - 1);
	this->innerSize--;

	int curindex = 0;

	while (true)
	{
		int nextindex = getSmallerChild(curindex);
		if (curindex == nextindex)
		{
			break;
		}

		swap(curindex, nextindex);
		curindex = nextindex;
	}


	return revalue;
}