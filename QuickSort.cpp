#include "QuickSort.h"

void QuickSort::InnerSort(int* arr, int begin, int end)
{
	if (begin >= end)
	{
		return;
	}
	int pivot = arr[begin];
	// left point
	int lp = begin+1;
	// right point
	int rp = end;

	while (true)
	{
		while (arr[lp] <= arr[rp] && arr[lp] <pivot)
		{
			lp++;
		}
		while (pivot <= arr[rp] && begin+1 <= rp)
		{
			rp--;
		}
		if (lp <= rp)
		{
			Swap(arr, lp, rp);
		}
		else // 교차
		{
			Swap(arr, begin, rp);
			break;
		}
	}
	InnerSort(arr, begin, rp - 1);
	InnerSort(arr, rp + 1, end);

}

void QuickSort::Swap(int* arr, int index1, int index2)
{
	int temp = arr[index1];
	arr[index1] = arr[index2];
	arr[index2] = temp;
}

void QuickSort::Sort(int* arr, int length)
{
	InnerSort(arr, 0, length - 1);
}

//이진탐색
bool QuickSort::Find(int* arr, int length, int value)
{
	int lp = 0;
	int rp = length - 1;
	while (rp-lp > 1)
	{
		int middle = (lp + rp) / 2;
		if (arr[middle] == value)
		{
			return true;
		}
		else if (arr[middle] > value)
		{
			rp = middle;
		}
		else
		{
			lp = middle;
		}
	}

	if (arr[lp] == value || arr[rp] == value)
	{
		return true;
	}
	else
	{
		return false;
	}
}