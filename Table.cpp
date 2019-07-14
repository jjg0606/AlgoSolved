#include "Table.h"


Table::Node::Node(std::string _key, int _value) : key(_key), value(_value), next(nullptr) {};

Table::Node::~Node() = default;

Table::NodeHead::NodeHead() : count(0), head(nullptr) {};

Table::NodeHead::~NodeHead() {
	Node*& cur = this->head;
	while (cur != nullptr)
	{
		Node* next = cur->next;
		delete cur;
		cur = next;
	}
};

bool Table::NodeHead::Add(std::string _key, int value) {
	
	if (this->head == nullptr)
	{
		this->head = new Node(_key, value);
		this->count++;
		return true;
	}

	Node* cur = this->head;
	while (true)
	{
		// duplicated
		if (cur->key == _key)
		{
			return false;
		}
		// final
		if (cur->next == nullptr)
		{
			break;
		}
		cur = cur->next;
	}
	cur->next = new Node(_key, value);
	this->count++;
	return true;
};

bool Table::NodeHead::Remove(std::string _key)
{
	Node*& before = this->head;
	if (before == nullptr)
	{
		return false;
	}
	if (before->key == _key)
	{
		Node* temp = before->next;
		delete before;
		before = temp;
		this->count--;
		return true;
	}
	
	Node* cur = before->next;

	while (cur != nullptr)
	{
		if (cur->key == _key)
		{
			Node* temp = cur->next;
			delete cur;
			before->next = temp;
			this->count--;
			return true;
		}

		before = cur;
		cur = cur->next;
	}
	return false;
};

int& Table::NodeHead::Get(std::string _key) {
	Node* cur = head;
	while (cur != nullptr)
	{
		if (cur->key == _key)
		{
			return cur->value;
		}
		
		cur = cur->next;
	}
	// return trash value
	int trash = 0;
	return trash;
};

int Table::Indexing(std::string key)
{
	int add = 0;
	for (int i = 0; i < key.length(); i++)
	{
		add += (int)key[i];
	}
	add %= this->maxHead;

	return add > 0 ? add : -add;
};

Table::Table(int _maxhead) : size(0), maxHead(_maxhead)
{
	headArr = new NodeHead[this->maxHead];
};

Table::Table() : Table(100)
{
};

Table::~Table() {
	delete[] headArr;
};

bool Table::Add(std::string key, int value)
{
	int index = Indexing(key);
	if (this->headArr[index].Add(key, value))
	{
		this->size++;
		return true;
	}
	else
	{
		return false;
	}
}

bool Table::Remove(std::string key)
{
	int index = Indexing(key);
	if (this->headArr[index].Remove(key))
	{
		this->size--;
		return true;
	}
	else
	{
		return false;
	}
}

int& Table::operator[](std::string key)
{
	int index = Indexing(key);
	return this->headArr[index].Get(key);
}