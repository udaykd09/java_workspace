#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
#include <string>
#include <bitset>
#include <iterator>

using namespace std;

// TW0'S COMPLIMENT
string twoComp(string& arr) {
	int j = arr.length();
	//reverse(arr.begin(),arr.end());
	int arr2[j];

	for (int x = 0; x < j; x++) {
		if (arr[x] == '1')
			arr2[x] = 0;
		else if (arr[x] == '0')
			arr2[x] = 1;
	}

	vector<int> arr1(j);
	vector<int> m2(j);
	int carry = 0;
	for (int x = 0; x < j; x++) {
		m2[x] = 0;
	}
	m2[j - 1] = 1;

	for (int i = j - 1; i >= 0; i--) {
		//cout << arr2[i] << " " << m2[i]<< endl;
		if (arr2[i] + m2[i] + carry == 0) {
			arr1[i] = 0;
			carry = 0;
		} else if (arr2[i] + m2[i] + carry == 1) {
			arr1[i] = 1;
			carry = 0;
		} else if (arr2[i] + m2[i] + carry == 2) {
			arr1[i] = 0;
			carry = 1;
		} else if (arr2[i] + m2[i] + carry > 2) {
			arr1[i] = 1;
			carry = 1;
		}

		//cout << arr1[i] << endl;

	}

	string i1;
	for (int g = 0; g < j; g++) {
		if (arr1[g] == 1)
			i1 += "1";
		else if (arr1[g] == 0)
			i1 += "0";
	}
	return i1;
}

// INTEGER TO STRING
string itos(long long num) {
	string s3;
	while (num > 0) {
		s3.append(1, num % 10 + '0');
		num = num / 10;
		//cout << s3 << "##";
	}
	reverse(s3.begin(), s3.end());
	return s3;
}

// CONVERT DECIMAL TO BINARY
void convertDtoB(string& str, bool nflag, bool& carr, string sval) {
	if (str == "0") {
		return;
	}
	string twosComp = "";
	int num = atoi(str.c_str());
	int arr[1000];
	int j = 0, r;
	while (num != 0) {
		r = num % 2;
		arr[j++] = r;
		if (r != 0)
			twosComp += itos(r);
		else
			twosComp += "0";
		num /= 2;
	}

	num = 0;
	string str1;
	if (nflag == 0) {
		for (int x = j - 1; x >= 0; x--) {
			if (arr[x] == 1)
				str1 += "1";
			if (arr[x] == 0)
				str1 += "0";
		}
		str = "0" + str1;
	}
	if (nflag == 1) {
		reverse(twosComp.begin(), twosComp.end());
		carr = 1;
		str = twoComp(twosComp);
	}
	int sval_check = atoi(sval.c_str());
	int check_s = str.length() + 1;
	if(sval_check > str.length()){
		check_s = sval_check;
	}
	int check_n = check_s - str.length();

	if (check_s > str.length() && nflag == 1) {
		for (int i = 0; i < check_n; i++) {
			//cout << "#";
			str = "1" + str;
		}
	} else if (sval_check > str.length() && nflag == 0) {
		for (int i = 0; i < check_n; i++) {
cout << "#";
			str = "0" + str;
		}
	}
}

// HEX TO BINARY 0-F
string hex_char_to_bin(char c) {
// TODO handle default / error
	switch (toupper(c)) {
	case '0':
		return "0000";
	case '1':
		return "0001";
	case '2':
		return "0010";
	case '3':
		return "0011";
	case '4':
		return "0100";
	case '5':
		return "0101";
	case '6':
		return "0110";
	case '7':
		return "0111";
	case '8':
		return "1000";
	case '9':
		return "1001";
	case 'A':
		return "1010";
	case 'B':
		return "1011";
	case 'C':
		return "1100";
	case 'D':
		return "1101";
	case 'E':
		return "1110";
	case 'F':
		return "1111";
	}
	return "";
}

// CONVERT HEX TO BINARY
void convertHtoB(string& hex, bool nflag, bool& carr) {
	string bin;
	for (int i = 0; i < hex.length(); i++)
		bin += hex_char_to_bin(hex[i]);
	hex = bin;
	if (nflag == 1) {
		carr = 1;
	}
}

//	METHOD TO CHECK IF INPUT IS BINARY
bool check_if_binary(string str) {
	if (str[0] == '-') {
		return false;
	}
	int number = atoi(str.c_str());
	//cout << number << " ##" << endl;
	int dv;
	while (number != 0) {
		dv = number % 10;
		if (dv > 1)
			return 0;
		number = number / 10;
	}
	return true;
}

// CLASS
class shiftregister {

public:

//	METHODS FOR SHIFT REGISTER
	void shiftLeft(string v);
	void shiftRight(string v);
	void print();
	int stringToInt(string str);
	string registerToString();
	shiftregister(string i, string s, string v, bool car);
	string convertBtoD();
	string convertBtoH();
	shiftregister operator+(shiftregister& s);
	friend ostream& operator<<(ostream& os, const shiftregister& s);
	shiftregister operator-(shiftregister& s);
	shiftregister operator*(shiftregister& s);
	vector<bool> get_register() {
		return sr1;
	}
	bool r_carry;
	bool carry_bit;

// 	SHIFT REGISTER DATA MEMBER
private:
	vector<bool> sr1;
};

// - OPERATOR OVERLOAD
shiftregister shiftregister::operator-(shiftregister& s) {

	string s1, s2, s3;
	s1 = this->convertBtoD();
	s2 = s.convertBtoD();
	int i = atoi(s1.c_str()) - atoi(s2.c_str());
	if (i > 0) {
		s3 = itos(i);
	} else if (i < 0) {
		i = -1 * i;
		s3 = itos(i);
		s3.insert(0, "-");
	} else if (i == 0) {
		s3 = "0";
	}
	bool negFlag = 0;
	bool icarr = 0;
	if (s3[0] == '-') {
		s3 = s3.substr(1, s3.length() - 1);
		negFlag = 1;
	}
	convertDtoB(s3, negFlag, icarr, "1");
	//cout << s3;

	int append = s3.length();
	//cout << append << s1.length() << negFlag << s3;
	if(append<=this->sr1.size()){
	if (negFlag == 1) {
		for (int i = 0; i < (this->sr1.size() - append); i++) {
			s3 = "1" + s3;
		}
	}

	else {

		for (int i = 0; i < (this->sr1.size() - append); i++) {
			s3 = "0" + s3;
		}
	}
	}

	shiftregister Sub_output(s3, "0", "0", icarr);
	return Sub_output;
}

// << OPERATOR OVERLOAD
ostream& operator <<(ostream& os, const shiftregister& s) {
	for (int x = 0; x < s.sr1.size(); x++) {
		cout << s.sr1[x];
	}
	return os;
}

// + OPERATOR OVERLOAD
shiftregister shiftregister::operator+(shiftregister& s) {
	string s1, s2, s3;
	//cout << "Here";
	s1 = this->convertBtoD();
	s2 = s.convertBtoD();

	int i = atoi(s1.c_str()) + atoi(s2.c_str());
	//cout << i;
	if (i > 0) {
		s3 = itos(i);
	} else if (i < 0) {
		i = -1 * i;
		s3 = itos(i);
		s3.insert(0, "-");
	} else if (i == 0) {
		s3 = "0";
	}
	bool negFlag = 0;
	bool icarr = 0;
	if (s3[0] == '-') {
		s3 = s3.substr(1, s3.length() - 1);
		negFlag = 1;
	}
	convertDtoB(s3, negFlag, icarr, "1");
	//cout << s3;

	int append = s3.length();
	//cout << append << s1.length() << negFlag << s3;
	if(append<=this->sr1.size()){
	if (negFlag == 1) {
		for (int i = 0; i < (this->sr1.size() - append); i++) {
			s3 = "1" + s3;
		}
	}

	else {

		for (int i = 0; i < (this->sr1.size() - append); i++) {
			s3 = "0" + s3;
		}
	}
	}
	shiftregister Add_output(s3, "0", "0", icarr);
	return Add_output;
}

// * OPERATOR OVERLOAD
shiftregister shiftregister::operator*(shiftregister& s) {
	string s3 = "";
	string s4 = "";
	string s4_0 = "";
	string s5 = "";
	string s3_1 = "0";
	string s4_1 = "0";

	string checkString = "";
	bool icarr = 0;
	bool icarr1 = 0;
	bool icarr2 = 0;

	// Multiplicand string
	s3 = this->registerToString();

	// Multiplier string
	s4 = s.registerToString();

	for (int x = 0; x < s.sr1.size(); x++) {
		checkString += "0";
	}

	// Product string
	for (int i = 0; i < s.sr1.size(); i++) {
		s5 += "0";
	}

	if (this->r_carry) {
		//s3 = twoComp(s3);
		icarr1 = 1;
		s3_1 = "1";
	}

	if (s.r_carry) {
		//s4_0 = twoComp(s4);
		icarr2 = 1;
		s4_1 = "1";
	}

	if (icarr1 || icarr2)
		icarr = 1;

	if (icarr1 && icarr2)
		icarr = 0;

	string n = itos((this->sr1.size()) * 2);
	shiftregister Multiplicand(s3, n, s3_1, icarr1);
//	cout << Multiplicand << endl;
	shiftregister Multiplier(s4, "0", s4_1, icarr2);
	shiftregister Product(s5, n, "0", icarr);

	int ifzero = Product.sr1.size()-this->sr1.size();
	if ((s3 == checkString) ||(s4 == checkString)) {
		return Product;
	}

	for (int j = Multiplier.sr1.size()-1; j >= 0; j--) {
//		cout << endl;
//		cout << Multiplier << "  ";
//		cout << Multiplier.sr1[Multiplier.sr1.size() - 1] << "    ";
//		cout << Multiplicand << "    ";


		if (Multiplier.sr1[Multiplier.sr1.size() - 1] == 1) {
			int carry = 0;
			for (int k = Product.sr1.size() - 1; k >= 0; k--) {
				//cout << endl;
				//cout << Product.sr1[k] << "   ";
				if (Product.sr1[k] + Multiplicand.sr1[k] + carry == 0) {
					Product.sr1[k] = 0;
					carry = 0;
				} else if (Product.sr1[k] + Multiplicand.sr1[k] + carry == 1) {
					Product.sr1[k] = 1;
					carry = 0;
				} else if (Product.sr1[k] + Multiplicand.sr1[k] + carry == 2) {
					Product.sr1[k] = 0;
					carry = 1;
				} else if (Product.sr1[k] + Multiplicand.sr1[k] + carry > 2) {
					Product.sr1[k] = 1;
					carry = 1;
				}
				//cout << Product.sr1[k] << endl;
			}
		}
//			cout << Product << "    ";
		Multiplicand.shiftLeft("0");
		Multiplier.shiftRight("0");
	}
		return Product;

	}

//	CONSTRUCTOR
	shiftregister::shiftregister(string i, string s, string v, bool car) {

		int s1 = this->stringToInt(s);
		int c = i.length();

		if (s1 > c) {
			for (int x = 0; x < (s1 - c); x++) {
				i = v[0] + i;
			}
		}

		for (int x = 0; x < i.length(); x++) {
			bool y = i[x] - '0';
			sr1.push_back(y);
		}
		r_carry = car;
		if (sr1[0] == 1) {
			r_carry = 1;
		}
		carry_bit = 0;
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
		rotate(this->sr1.begin(), this->sr1.begin() + 1, this->sr1.end());
		bool v1 = v[0] - '0';
		this->sr1[(this->sr1.size() - 1)] = v1;
	}

//	METHOD FOR SHIFT-RIGHT
	void shiftregister::shiftRight(string v) {
		rotate(this->sr1.rbegin(), this->sr1.rbegin() + 1, this->sr1.rend());
		bool v1 = v[0] - '0';
		this->sr1[0] = v1;
	}

//	METHOD TO CONVERT STRING TO INT
	int shiftregister::stringToInt(string str) {
		int temp = atoi(str.c_str());
		return temp;
	}

//	METHOD TO CONVERT REGISTER TO STRING
	string
	shiftregister::registerToString()
	{
		string s3 = "";
		for (int i = 0; i < this->sr1.size(); i++) {
			if (this->sr1[i] == 0) {
				s3 += "0";
			} else if (this->sr1[i] == 1) {
				s3 += "1";
			}
		}
		return s3;
	}

// CONVERT BINARY TO DECIMAL
	string
	shiftregister::convertBtoD()
	{
		string twosComp = this->registerToString();
		string checkString = "";

		for (int x = 0; x < twosComp.length(); x++) {
			checkString += "0";
		}
		if (twosComp == checkString) {
			return "0";
		}
		int j = this->sr1.size();
		vector<bool> b(j);
		//cout << j;
		//cout << "#"<<endl;
		for (int x = 0; x < j; x++) {
			b[x] = this->sr1[x];
		}
		if (this->r_carry == 1) {
			twosComp = twoComp(twosComp);
			for (int k = 0; k < twosComp.length(); k++) {
				if (twosComp[k] == '0')
					b[k] = 0;
				else if (twosComp[k] == '1')
					b[k] = 1;
			}
		}

		long long num = 0;
		for (int z = 0; z < b.size(); z++) {
			num = 10 * num + b[z];
		}
		long long dec = 0, rem, base = 1;

		string s3;
		while (num > 0) {
			rem = num % 10;
			dec = dec + rem * base;
			base = base * 2;
			num = num / 10;
		}

		s3 = itos(dec);

		if (this->r_carry == 1) {
			s3.insert(0, "-");
		}
		return s3;
	}

// CONVERT BINARY TO HEX
	string
	shiftregister::convertBtoH()
	{
		string sBinary;
		for (int x = 0; x < this->sr1.size(); x++) {
			if (this->sr1[x] == 1)
				sBinary += "1";
			else if (this->sr1[x] == 0)
				sBinary += "0";
		}
//cout << sBinary.size();
		string rest;
		if (this->r_carry == 1) {
			rest = "Fx";
		}
		if (this->r_carry == 0) {
			rest = "0x";
		}
		string tmp, chr = "0000";
		if (this->r_carry == 0) {
			if (sBinary.size() < 4) {
				for (int i = sBinary.size(); i < 4; i++) {
					sBinary = "0" + sBinary;
				}
			}
			if (sBinary.size() % 4 != 0) {
				for (int i = sBinary.size() % 4; i < 4; i++) {
					sBinary = "0" + sBinary;
				}
			}
		}
		if (this->r_carry == 1) {
			if (sBinary.size() < 4) {
				for (int i = sBinary.size(); i < 4; i++) {
					sBinary = "1" + sBinary;
				}
			}
			if (sBinary.size() % 4 != 0) {
				for (int i = sBinary.size() % 4; i < 4; i++) {
					sBinary = "1" + sBinary;
				}
			}
		}
		int len = sBinary.length() / 4;
		chr = chr.substr(0, len);
		//cout << "%" << sBinary<< "%" << chr << "%" << len;
		//sBinary = chr+sBinary;
		for (int i = 0; i < sBinary.length(); i += 4) {
			tmp = sBinary.substr(i, 4);
			//cout << "%" << sBinary<< "%" << tmp;
			if (!tmp.compare("0000")) {
				rest = rest + "0";
			} else if (!tmp.compare("0001")) {
				rest = rest + "1";
			} else if (!tmp.compare("0010")) {
				rest = rest + "2";
			} else if (!tmp.compare("0011")) {
				rest = rest + "3";
			} else if (!tmp.compare("0100")) {
				rest = rest + "4";
			} else if (!tmp.compare("0101")) {
				rest = rest + "5";
			} else if (!tmp.compare("0110")) {
				rest = rest + "6";
			} else if (!tmp.compare("0111")) {
				rest = rest + "7";
			} else if (!tmp.compare("1000")) {
				rest = rest + "8";
			} else if (!tmp.compare("1001")) {
				rest = rest + "9";
			} else if (!tmp.compare("1010")) {
				rest = rest + "A";
			} else if (!tmp.compare("1011")) {
				rest = rest + "B";
			} else if (!tmp.compare("1100")) {
				rest = rest + "C";
			} else if (!tmp.compare("1101")) {
				rest = rest + "D";
			} else if (!tmp.compare("1110")) {
				rest = rest + "E";
			} else if (!tmp.compare("1111")) {
				rest = rest + "F";
			} else {
				continue;
			}
			//cout << "*" << sBinary<< "*" << tmp<<endl;
		}
		//cout << rest;
		return rest;
	}

	/*---------------------------------------MAIN-FUNCTION-------------------------------------*/

int main(int argc, char **argv) {

	bool iflag = 0;		//initiate value flag
	bool sflag = 0;//number of bits flag
	bool rflag = 0;//Right flag
	bool lflag = 0;//left flag

	bool Iflag = 0;//initiate value flag
	bool Sflag = 0;//number of bits flag
	bool Rflag = 0;//Right flag
	bool Lflag = 0;//left flag

	bool oflag = 0;//operator flag
	bool dflag = 0;//decimal flag
	bool hflag = 0;//Hex flag
	bool vflag = 0;//value to inject flag
	bool pflag = 0;//print flag

	char *ivalue = NULL;
	char *svalue = NULL;
	char *rvalue = NULL;
	char *lvalue = NULL;
	char *Ivalue = NULL;
	char *Svalue = NULL;
	char *Rvalue = NULL;
	char *Lvalue = NULL;
	char *vvalue = NULL;
	char *ovalue = NULL;
	int c;
	opterr = 0;
	string s, s2;

	queue<bool> iOrI;// To maintain sequence of registers.
	queue<bool> operation;// To maintain sequence and number of R/L shift operations of 1st register.
	queue<bool> operation1;// To maintain sequence and number of R/L shift operations of 2st register.

// 	Parsing arguments using Getopt library and switch case
	while ((c = getopt(argc, argv, "i:s:r:l:I:S:R:L:o:v:phd")) != -1)
	switch (c) {
		{
			case 'i':
			iflag = 1;
			ivalue = optarg;
			iOrI.push(0);
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
				q2 = -(q2);
				for (int i = 0; i < q2; i++) {
					operation.push(0);
				}
			} else {
				for (int i = 0; i < q2; i++) {
					operation.push(1);
				}
			}
			break;
		}
		{
			case 'r':
			rflag = 1;
			rvalue = optarg;
			string q1 = string(rvalue);
			int q2 = atoi(q1.c_str());
			if (q2 < 0) {
				q2 = -(q2);
				for (int i = 0; i < q2; i++) {
					operation.push(1);
				}
			} else {
				for (int i = 0; i < q2; i++) {
					operation.push(0);
				}
			}
			break;
		}
		{
			case 'I':
			Iflag = 1;
			Ivalue = optarg;
			iOrI.push(1);
			break;
		}
		{
			case 'S':
			Sflag = 1;
			Svalue = optarg;
			break;
		}
		{
			case 'L':
			Lflag = 1;
			Lvalue = optarg;
			string q1 = string(Lvalue);
			int q2 = atoi(q1.c_str());
			if (q2 < 0) {
				q2 = -(q2);
				for (int i = 0; i < q2; i++) {
					operation.push(0);
				}
			} else {
				for (int i = 0; i < q2; i++) {
					operation1.push(1);
				}
			}
			break;
		}
		{
			case 'R':
			Rflag = 1;
			Rvalue = optarg;
			string q1 = string(Rvalue);
			int q2 = atoi(q1.c_str());
			if (q2 < 0) {
				q2 = -(q2);
				for (int i = 0; i < q2; i++) {
					operation.push(1);
				}
			} else {
				for (int i = 0; i < q2; i++) {
					operation1.push(0);
				}
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
			case 'o':
			oflag = 1;
			ovalue = optarg;
			break;
		}
		{
			case 'd':
			dflag = 1;
			break;
		}
		{
			case 'h':
			hflag = 1;
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
					|| optopt == 'v' || optopt == 'I' || optopt == 'S'
					|| optopt == 'R' || optopt == 'L' || optopt == 'o')
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
	if ((iflag == 0 && sflag == 0) && (Iflag == 0 && Sflag == 0)) {
		cerr
		<< "Please provide a initial_value(-i) or number_of_bits(-s) for of the shift register"
		<< endl;
		return -1;
	}

	if (dflag == 1 && hflag == 1) {
		cerr << "Please provide single type for result -d or -h" << endl;
		return -1;
	}

	//	SET ALL PARAMETERS FROM NULL TO A VALID VALUE
	if (svalue == NULL) {
		svalue = new char;
		*svalue = '0';
	}
	if (Svalue == NULL) {
		Svalue = new char;
		*Svalue = '0';
	}

	if (vvalue == NULL) {
		vvalue = new char;
		*vvalue = '0';
	}

	//	PUSH TO QUEUE IF s,S = 1
	if (sflag == 1 && iflag == 0) {
		s = "0";
		iOrI.push(0);
	}

	if (Sflag == 1 && Iflag == 0) {
		s2 = "0";
		iOrI.push(1);
	}

	if ((iflag || sflag) == 1 && Iflag == 0) {
		s2 = "0";
	}

	if ((Iflag || Sflag) == 1 && iflag == 0) {
		s = "0";
	}

	if (oflag == 1 && sflag == 1) {
		if (ovalue[0] == 'x') {
			Svalue = svalue;
		}
	}

// Convert DtoB i
	bool icarr = 0;
	if (iflag) {
		s = ivalue;
		bool negFlagi = 0;
		if (s.at(s.length() - 1) == 'D' || s.at(s.length() - 1) == 'd') {
			//cout << "#D";
			s.erase(s.length() - 1, 1);
			if (s.at(0) == '-') {
				s = s.substr(1, s.length() - 1);
				if (s == "0") {
					cerr << "-0 is invalid" << endl;
					return -1;
				}
				negFlagi = 1;
			}
			if (atoi(s.c_str()) > 524287) {
				cerr << "OVERFLOW VALUE >524287d" << endl;
				return -1;
			}
			convertDtoB(s, negFlagi, icarr, svalue);
			//cout << icarr << "@";
// Convert HtoB i
		} else if (s.at(s.length() - 1) == 'H' || s.at(s.length() - 1) == 'h') {
			s.erase(s.length() - 1, 1);
			if (s.at(0) == '-') {
				s = s.substr(1, s.length() - 1);
				if (s == "0") {
					cerr << "-0 is invalid" << endl;
					return -1;
				}
				negFlagi = 1;
			}
			if (s.substr(0, 1) == "0x" || s.substr(0, 1) == "0X") {
				s = s.substr(2, s.length() - 1);
			}
			convertHtoB(s, negFlagi, icarr);
		}
// Check if binary i
		else if (!check_if_binary(s)) {
			cerr
			<< "Value not binary, Please enter a binary value( ex. -i 101001 )"
			<< endl;
			return -1;
		}
	}

// Convert DtoB I
	bool Icarr = 0;
	if (Iflag) {
		s2 = Ivalue;
		bool negFlagI = 0;
		if (s2.at(s2.length() - 1) == 'D' || s2.at(s2.length() - 1) == 'd') {
			//cout << "#D";
			s2.erase(s2.length() - 1);
			if (s2.at(0) == '-') {
				s2 = s2.substr(1, s2.length() - 1);
				if (s2 == "0") {
					cerr << "-0 is invalid" << endl;
					return -1;
				}
				negFlagI = 1;
			}
			if (atoi(s2.c_str()) > 524287) {
				cerr << "OVERFLOW VALUE >524287d" << endl;
				return -1;
			}
			convertDtoB(s2, negFlagI, Icarr, Svalue);
// Convert HtoB I
		} else if (s2.at(s2.length() - 1) == 'H'
				|| s2.at(s2.length() - 1) == 'h') {
			s2.erase(s2.length() - 1, 1);
			if (s2.at(0) == '-') {
				s2 = s2.substr(1, s2.length() - 1);
				if (s2 == "0") {
					cerr << "-0 is invalid" << endl;
					return -1;
				}
				negFlagI = 1;
			}
			if (s2.substr(0, 1) == "0x" || s2.substr(0, 1) == "0X") {
				s2 = s2.substr(2, s2.length() - 1);
			}
			convertHtoB(s2, negFlagI, Icarr);
		}
//	Check if binary I
		else if (!check_if_binary(s2)) {
			cerr
			<< "Value not binary, Please enter a binary value( ex. -I 101001 )"
			<< endl;
			return -1;
		}
	}

//	Check if the entered V value is null
	if (vflag == 1) {
		if (!check_if_binary(vvalue)) {
			cerr << "Value not binary, Please enter a binary value( ex. -v 1 )"
			<< endl;
			return -1;
		}
	}

// 	Check if ovalue is + or -
	if (oflag == 1) {
		if (!((ovalue[0] == '+') || (ovalue[0] == '-') || ovalue[0] == 'x')) {
			cerr << "Operator not valid (ie -o +, -, x)" << endl;
			return -1;
		}
	}

//		CREATE THE SHIFT REGISTER - CALL THE CONSTRUCTOR
	shiftregister sr(s, svalue, vvalue, icarr);

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

//		CREATE THE SHIFT REGISTER 2 - CALL THE CONSTRUCTOR
	shiftregister sr2(s2, Svalue, vvalue, Icarr);

//		SHIFT OPERATIONS RIGHT/LEFT - USING FIFO IN QUEUE
	if (Rflag == 1 || Lflag == 1) {
		while (operation1.size() > 0) {
			if (operation1.front() == 0) {
				sr2.shiftRight(vvalue);
				operation1.pop();
			}
			if (operation1.front() == 1) {
				sr2.shiftLeft(vvalue);
				operation1.pop();
			}
		}
	}

	int p = sr.get_register().size();
	int q = sr2.get_register().size();
	int r;
	p < q ? r = q : r = p;
	unsigned int n = p - q;
	queue<bool> iOrI_op;
	//if(ovalue[0] == '+'){
	while (iOrI.size() > 0) {

		if (iOrI.front() == 0) {
//		PRINT i IF FLAG IS SET
			if (pflag == 1 && (iflag == 1 || sflag == 1) && (ovalue[0] != 'x')) {
				if (sr.carry_bit == 1) {
					cout << "(1)" << sr;
				} else {
					cout << sr;
				}

				if (dflag == 1) {
					string ds1 = sr.convertBtoD();
					cout << "(" << ds1 << ")";
				} else if (hflag == 1) {
					string ds1 = sr.convertBtoH();
					cout << "(" << ds1 << ")";
				}
				cout << endl;
			}
			iOrI.pop();
			iOrI_op.push(0);
		}

		if (iOrI.front() == 1) {
//		PRINT I IF FLAG IS SET
			if (pflag == 1 && (Iflag == 1 || Sflag == 1) && (ovalue[0] != 'x')) {
				if (sr2.carry_bit == 1) {
					cout << "(1)" << sr2;
				} else {
					cout << sr2;
				}

				if (dflag == 1) {
					string ds1 = sr2.convertBtoD();
					cout << "(" << ds1 << ")";
				} else if (hflag == 1) {
					string ds1 = sr2.convertBtoH();
					cout << "(" << ds1 << ")";
				}
				cout << endl;
			}
			iOrI.pop();
			iOrI_op.push(1);
		}
	//}
	}
	if (oflag == 1) {
		if (ovalue[0] == 'x') {
			if (sr.get_register().size() != sr2.get_register().size()) {
				//cerr << "-------------------------------------------------"
				//<< endl;
				cerr << "Multiplicand size is not equal to multiplier size"
				<< endl;
				return -1;
			}
		}
	}

	shiftregister sr3("0", "0", "0", icarr);
	if (oflag == 1 && (Sflag == 1 || Iflag == 1)
			&& (sflag == 1 || iflag == 1)) {

		if (ovalue[0] == '+') {
			sr3 = sr + sr2;
		} else if (ovalue[0] == '-') {
			if (iOrI_op.front() == 0) {
				sr3 = sr - sr2;
			} else if (iOrI_op.front() == 1) {
				sr3 = sr2 - sr;
			}
		} else if (ovalue[0] == 'x') {
			if (iOrI_op.front() == 0) {
				sr3 = sr * sr2;
			} else if (iOrI_op.front() == 1) {
				sr3 = sr2 * sr;
			}
		}

		if (pflag == 1) {
			if(ovalue[0] != 'x'){
			for (int i = 0; i < r + 7; i++) {
				cout << "-";
			}
			cout << endl;
			}
			if (sr3.carry_bit == 1) {
				cout << "(1)" << sr3;
			} else {
				cout << sr3;
			}

			if (dflag == 1) {
				if (sr3.get_register().size() > 19) {
					cerr << "(VALUE >524287d)" << endl;
					return -1;
				}
				string ds1 = sr3.convertBtoD();
				cout << "(" << ds1 << ")";
			} else if (hflag == 1) {
				string ds1 = sr3.convertBtoH();
				cout << "(" << ds1 << ")";
			}
			cout << endl;
		}
	}

	if (hflag == 1) {

	}

	return 0;
}
