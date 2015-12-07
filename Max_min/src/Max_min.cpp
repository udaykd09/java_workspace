//============================================================================
// Name        : Max_min.cpp
// Author      : cbb
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <climits>
using namespace std;

struct max_min {
	int min,
	int max
} min_max_t;
const int MI = MAX_INTEGER;
const int Mi = MIN_INTEGER;

inline min(int a0, int a1) {return (a0 < a1 ? a0 : a1);}

inline max(int a0, int a1) {return (a1 == MI ? a0: (a0 > a1 ? a0 : a1));}

min_max_t minMax(int a1, int a2 = MI, int a3 = MI, int a4 = MI, int a5 = MI) {
	min_max_t result;
	result.min = min(a1, min(a2, min(a3, min(a4, a5))));
	result.max = max(a1, max(a2, max(a3, max(a4, a5))));
	return result;
}

int main() {
	cout << "Hello World!!!" << endl; // prints Hello World!!!
	return 0;
}
