#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

class shiftregister {

public:

//	METHODS FOR SHIFT REGISTER
	void shiftLeft(string v);
	void shiftRight(string v);
	void print();
	int stringToInt(string str);
	shiftregister(string i, string s, string v);

protected:

// 	SHIFT REGISTER DATA MEMBER
	vector<bool> sr1;
};

class circularshiftregister: public shiftregister {
public:
	circularshiftregister(string i, string s, string v) :
			shiftregister(i, s, v) {
	}
	void shiftRight();
};

void circularshiftregister::shiftRight() {
	rotate(sr1.begin(), sr1.begin() + 1, sr1.end());
}

//	CONSTRUCTOR
shiftregister::shiftregister(string i, string s, string v) {
	int s1 = this->stringToInt(s);
	int c = i.length();
	bool flag = false;
	if (i[0] == '9') {
		flag = true;
		c = 0;
		i = '0';
	}

	if (s1 > c) {
		for (int x = 0; x < (s1 - c); x++) {
			i += v[0];
		}
	}
	if (i[0] == '0' && flag == true) {
		for (int x = 1; x < i.length(); x++) {
			bool y = i[x] - '0';
			sr1.push_back(y);
		}
	} else {
		for (int x = 0; x < i.length(); x++) {
			bool y = i[x] - '0';
			sr1.push_back(y);
		}
	}
}

//	METHOD FOR PRINT
void shiftregister::print() {
	for (int x = 0; x < sr1.size(); x++) {
		cout << sr1[x];
	}
	cout << endl;
}

//	METHOD FOR SHIFT-LEFT
void shiftregister::shiftLeft(string v) {
	rotate(sr1.begin(), sr1.begin() + 1, sr1.end());
	bool v1 = v[0] - '0';
	sr1[(sr1.size() - 1)] = v1;
}

//	METHOD FOR SHIFT-RIGHT
void shiftregister::shiftRight(string v) {
	rotate(sr1.rbegin(), sr1.rbegin() + 1, sr1.rend());
	bool v1 = v[0] - '0';
	sr1[0] = v1;
}

//	METHOD TO CONVERT STRING TO INT
int shiftregister::stringToInt(string str) {
	int temp = atoi(str.c_str());
	return temp;
}

//	METHOD TO CHECK IF INPUT IS BINARY
bool check_if_binary(string i) {
	int number = atoi(i.c_str());
	int dv;
	while (number != 0) {
		dv = number % 10;
		if (dv > 1)
			return 0;
		number = number / 10;
	}
	return true;
}

/*---------------------------------------MAIN-FUNCTION-------------------------------------*/

int main(int argc, char **argv) {
	bool iflag = 0;		//initiate value flag
	bool sflag = 0;		//number of bits flag
	bool rflag = 0;		//Right flag
	bool lflag = 0;		//left flag
	bool vflag = 0;		//value to inject flag
	bool pflag = 0;		//print flag
	char *ivalue = NULL;
	char *svalue = NULL;
	char *rvalue = NULL;
	char *lvalue = NULL;
	char *vvalue = NULL;
	int c;
	opterr = 0;

	queue<bool> operation; // To maintain sequence and number of R/L shift operations.

// 	Parsing arguments using Getopt library and switch case
	while ((c = getopt(argc, argv, "i:s:r:l:v:p")) != -1)
		switch (c) {
		{
			case 'i':
			iflag = 1;
			ivalue = optarg;
			break;
		}
		{
			case 's':
			sflag = 1;
			svalue = optarg;
			break;
		}
		{
			case 'l':
			lflag = 1;
			lvalue = optarg;
			string q1 = string(lvalue);
			int q2 = atoi(q1.c_str());
			if (q2 < 0) {

				for (int i = 0; i < q2; i++) {
					operation.push(0);
				}
			}
			for (int i = 0; i < q2; i++) {
				operation.push(1);
			}
			break;
		}
		{
			case 'r':
			rflag = 1;
			rvalue = optarg;
			string q1 = string(rvalue);
			int q2 = atoi(q1.c_str());
			for (int i = 0; i < q2; i++) {
				operation.push(0);
			}
			break;
		}
		{
			case 'v':
			vflag = 1;
			vvalue = optarg;
			break;
		}
		{
			case 'p':
			pflag = 1;
			break;
		}
		{
			case '?':
			if (optopt == 'i' || optopt == 's' || optopt == 'r' || optopt == 'l'
					|| optopt == 'v')
				cerr << char(optopt) << " : requires an argument.\n";
			else if (isprint(optopt))
				cerr << "Unknown option : " << char(optopt) << endl;
			else
				cerr << "Unknown option character : " << char(optopt) << endl;
			return -1;
		}
		{
			default:
			abort();
		}
		}

//  Error when no flag is set
	if (iflag == 0 && sflag == 0) {
		cerr
				<< "Please provide a initial_value(-i) or number_of_bits(-s) for of the shift register"
				<< endl;
		return -1;
	}

// 	CASE WHEN IVALUE = "0" / NULL, NEED TO DIFFERENTIATE
	if (ivalue == NULL) {
		ivalue = new char;
		*ivalue = '9';
	}

//	Check if the entered I value is null
	if (iflag == 1 && !check_if_binary(ivalue)) {
		cerr << "Value not binary, Please enter a binary value( ex. -i 101001 )"
				<< endl;
		;
		return -1;
	}

//	Check if the entered V value is null
	if (vflag == 1) {
		if (!check_if_binary(vvalue)) {
			cerr << "Value not binary, Please enter a binary value( ex. -v 1 )"
					<< endl;
			;
			return -1;
		}
	}

//	SET ALL PARAMETERS FROM NULL TO A VALID VALUE
	if (svalue == NULL) {
		svalue = new char;
		*svalue = '0';
	}

	if (rflag == 1 && rvalue == NULL) {
		rvalue = new char;
		*rvalue = '1';
	}

	if (lflag == 1 && lvalue == NULL) {
		lvalue = new char;
		*lvalue = '1';
	}

	if (vvalue == NULL) {
		vvalue = new char;
		*vvalue = '0';
	}

	if (iflag == 1 || sflag == 1) {

//		CREATE THE SHIFT REGISTER - CALL THE CONSTRUCTOR
		shiftregister sr(ivalue, svalue, vvalue);

//		SHIFT OPERATIONS RIGHT/LEFT - USING FIFO IN QUEUE
		if (rflag == 1 || lflag == 1) {
			while (operation.size() > 0) {
				if (operation.front() == 0) {
					sr.shiftRight(vvalue);
					operation.pop();
				}
				if (operation.front() == 1) {
					sr.shiftLeft(vvalue);
					operation.pop();
				}
			}
		}
		//shiftregister sr1 = sr;
		//sr1.print();
//		PRINT IF FLAG IS SET
		if (pflag == 1) {
			sr.print();
		}
	}

	circularshiftregister csr(ivalue, svalue, vvalue);
	csr.shiftRight();
	csr.print();

	return 0;
}
