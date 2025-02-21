#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include <string>

using namespace std;

string swapFirstAndLastWordsSolution(string s);
string swapFirstAndLastWordsValidator(string s);

int main() {

    ifstream inputFile("input.txt");
    string testCase;

    while (getline(inputFile, testCase)) {
        string actual = swapFirstAndLastWordsSolution(testCase);
        string expected = swapFirstAndLastWordsValidator(testCase);

        if (actual != expected) {
            cerr << "[JUDGE_FEEDBACK]" << endl;
            cerr << "WRONG_ANSWER" << endl;
            cerr << "Input: " << testCase << endl;
            cerr << "Output: " << actual << endl;
            cerr << "Expected: " << expected << endl;
            cerr << "[JUDGE_FEEDBACK]" << endl;
            return 65;
        }
    }

    cerr << "[JUDGE_FEEDBACK]" << endl;
    cerr << "ACCEPTED" << endl;
    cerr << "[JUDGE_FEEDBACK]" << endl;
    return 0;
}
