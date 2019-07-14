#include <iostream>
#include <string>
#pragma once

class Table
{
	//node
	class Node {
	public:
		std::string key;
		int value;
		Node* next;
		Node(std::string _key, int _value);
		~Node();
	};
	//~node

	//nodehead
	class NodeHead {
	private :
		int count;
		Node* head;
	public:		
		NodeHead();
		~NodeHead();
		bool Add(std::string _key, int value);
		bool Remove(std::string _key);
		int& Get(std::string _key);
	};
	//~nodehead

private :
	NodeHead* headArr;
	// inside element size
	int size;
	// size of headArr
	int maxHead;
	// indexing
	int Indexing(std::string _key);
public :
	Table(int _maxHead);

	Table();

	~Table();

	bool Add(std::string key, int value);

	bool Remove(std::string key);

	int& operator[](std::string key);
};

