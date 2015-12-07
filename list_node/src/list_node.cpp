//============================================================================
// Name        : dynamic_char_array.cpp
// Author      : cbb
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
using namespace std;

const int NodeValues = 10;

struct tree_node {
int values[NodeValues];
struct tree_node * left;
struct tree_node * right;
};

tree_node* getnewnode(){
	tree_node t;
	t.left = NULL;
	t.right = NULL;
	t.values= {0};
	return t;
}

void getnewnode(tree_node& n){
	n.left = NULL;
	n.right = NULL;
	n.values= {0};
}

int main() {
	tree_node n,x;
	getnewnode(n);
	x = getnewnode();
	cout << "Hello World!!!" << endl; // prints Hello World!!!
	return 0;
}
