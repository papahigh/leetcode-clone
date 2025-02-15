#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

string swapFirstAndLastWordsValidator(string s) {
    vector<string> words;
    stringstream ss(s);
    string word;

    while (ss >> word) {
        words.push_back(word);
    }

    if (words.size() <= 1) {
        return s;
    }

    swap(words.front(), words.back());

    string result;
    for (size_t i = 0; i < words.size(); i++) {
        if (i > 0) result += " ";
        result += words[i];
    }

    return result;
}
