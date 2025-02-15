package problem.stub.problem1

import problem.Problem.*
import problem.Resources


val CPP_PROJECT = Project(
    lang = Language.CPP,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.cpp",
            content =
                // language=c++
                """
                #include <iostream>
                #include <sstream>
                #include <vector>
                
                using namespace std;
                
                string swapFirstAndLastWordsSolution(string s) {
                
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.cpp",
            content =
                // language=c++
                """
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
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.cpp",
            content =
                // language=c++
                """
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
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                clang++ -std=c++23 -o main.out main.cpp solution.cpp validator.cpp
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                ./main.out
                """.trimIndent()
        ),
    ),
    limits = Resources(time = 10, memory = 262144)
)
