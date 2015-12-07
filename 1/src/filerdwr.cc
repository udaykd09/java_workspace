#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fstream>
#include <iostream>
using namespace std;

//Function to Write / Append in a file.

void write(string fvalue, string pvalue, string nvalue, int aflag) {

	int n = atoi(nvalue.c_str());
	string a = pvalue;
	for (int i = 0; i < n - 1; i++) {
		pvalue += a;
	}

	ofstream outfile;
	if (aflag == 1) {

		// open a file in append mode.
		outfile.open(fvalue.c_str(), fstream::app);
	} else
		outfile.open(fvalue.c_str());

	if (outfile.is_open()) {

		// write inputed data into the file.
		outfile << pvalue << endl;

		// close the opened file.
		outfile.close();
	}
}

//Function to read a file.

void read(string fvalue) {

	// open a file in read mode.
	ifstream infile(fvalue.c_str());
	string line;

	if (infile.is_open()) {

		while (getline(infile, line)) {
			cout << line << endl;
		}

		// Close the opened file.
		infile.close();
	} else
		cerr << "File does not exist" << endl;
}

int main(int argc, char **argv) {
	int rflag = 0;		//Read flag
	int wflag = 0;		//Write flag
	int aflag = 0;		//Append flag
	char *fvalue = NULL;	//Filename
	char *pvalue = NULL;	//String to be written into file
	char *nvalue = NULL;	//No. of times the string to be written into file
	int c;

	opterr = 0;

	// Parsing arguments using Getopt library and switch case

	while ((c = getopt(argc, argv, "rwaf:p:n:")) != -1)
		switch (c) {
		case 'r':
			rflag = 1;
			break;
		case 'w':
			wflag = 1;
			break;
		case 'a':
			aflag = 1;
			break;
		case 'f':
			fvalue = optarg;
			break;
		case 'p':
			pvalue = optarg;
			break;
		case 'n':
			nvalue = optarg;
			break;
		case '?':
			if (optopt == 'f' || optopt == 'p' || optopt == 'n')
				cerr << char(optopt) << " : requires an argument.\n";
			else if (isprint(optopt))
				cerr << "Unknown option : " << char(optopt) << endl;
			else
				cerr << "Unknown option character : " << char(optopt) << endl;
			return 1;
		default:
			abort();
		}

	// Error when no flag is set

	if ((rflag == 0 && wflag == 0) && aflag == 0) {
		cerr << "Please provide whether to read/write/append( -r / -w / -a)"
				<< endl;
		return 1;
	}

	// Case when a read/write/append flag is set

	if (fvalue != NULL) {

		if (wflag == 1 || aflag == 1) {
			if (pvalue != NULL) {
				if (nvalue == NULL) {
					nvalue = new char;
					*nvalue = '1';
				}
				write(fvalue, pvalue, nvalue, aflag);
			}

			// Error if string not provided to write/append
			else
				cerr
						<< "Please provide a string to write( -w -p 'String' -n timesToAddString )"
						<< endl;
		}

		if (rflag == 1) {
			read(fvalue);
		}
	}

	// Error if filename is not provided

	else
		cerr << "Please provide a filename( -f filename )" << endl;

	return 0;
}
