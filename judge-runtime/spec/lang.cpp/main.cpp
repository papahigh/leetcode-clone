#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include <string>

using namespace std;

string swapFirstAndLastWords(string s);
string evaluate(string s);

int main() {

    ifstream inputFile("input.txt");
    string testCase;

    while (getline(inputFile, testCase)) {
        string actual = swapFirstAndLastWords(testCase);
        string expected = evaluate(testCase);

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
