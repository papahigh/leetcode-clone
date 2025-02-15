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
            cout << "Test case failed: " << testCase << endl;
            cout << "Expected: " << expected << endl;
            cout << "Actual: " << actual << endl;
            return 404;
        }
    }

    cout << "All test cases passed!" << endl;
    return 0;
}
