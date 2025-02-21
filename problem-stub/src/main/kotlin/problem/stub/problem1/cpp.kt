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
                            cerr << "[JUDGE_FEEDBACK]" << endl;
                            cerr << "WRONG_ANSWER" << endl;
                            cerr << "Input: " << testCase << endl;
                            cerr << "Output: " << actual << endl;
                            cerr << "Expected: " << expected << endl;
                            cerr << "[JUDGE_FEEDBACK]" << endl;
                            return 405;
                        }
                    }
                
                    cerr << "[JUDGE_FEEDBACK]" << endl;
                    cerr << "ACCEPTED" << endl;
                    cerr << "[JUDGE_FEEDBACK]" << endl;
                    return 0;
                }
                """.trimIndent()
        ),
    ),
    compile = ProjectAction(
        script = ProjectFile(
            name = "compile.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                stderr() { echo "${'$'}@" 1>&2; }
                stderr "[JUDGE_FEEDBACK]"
                
                clang++ -std=c++23 -o main.out main.cpp solution.cpp validator.cpp
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),
        resources = Resources(time = 3, memory = 75000)
    ),
    execute = ProjectAction(
        script = ProjectFile(
            name = "execute.sh",
            content =
                // language=bash
                """
                #!/usr/bin/env bash
                
                ./main.out
                """.trimIndent()
        ),
        resources = Resources(time = 3, memory = 75000)
    ),
)
