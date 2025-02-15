package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val C_PROJECT = Project(
    lang = Language.C,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.c",
            content =
                // language=c++
                """
                #include <stdio.h>
                #include <string.h>
                #include <stdlib.h>
                
                char* swapFirstAndLastWordsSolution(const char *s) {
                
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.c",
            content =
                // language=c++
                """
                #include <stdio.h>
                #include <string.h>
                #include <stdlib.h>
                
                char* swapFirstAndLastWordsValidator(const char *s) {
                    char words[100][100];
                    int wordCount = 0;
                
                    char str[256];
                    strcpy(str, s);
                
                    char *token = strtok(str, " ");
                    while (token) {
                        strcpy(words[wordCount++], token);
                        token = strtok(NULL, " ");
                    }
                
                    if (wordCount <= 1) {
                        return strdup(s);
                    }
                
                    char temp[100];
                    strcpy(temp, words[0]);
                    strcpy(words[0], words[wordCount - 1]);
                    strcpy(words[wordCount - 1], temp);
                
                    char *result = malloc(256);
                    result[0] = '\\0';
                    for (int i = 0; i < wordCount; i++) {
                        if (i > 0) strcat(result, " ");
                        strcat(result, words[i]);
                    }
                
                    return result;
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.c",
            content =
                // language=c++
                """
                #include <stdio.h>
                #include <stdlib.h>
                #include <string.h>
                
                char* swapFirstAndLastWordsSolution(const char *s);
                char* swapFirstAndLastWordsValidator(const char *s);
                
                int main() {
                    FILE *file = fopen("input.txt", "r");
                    if (!file) {
                        printf("Error: Could not open input.txt\\n");
                        return 1;
                    }
                
                    char testCase[256];
                
                    while (fgets(testCase, sizeof(testCase), file)) {
                        testCase[strcspn(testCase, "\\n")] = 0; // Remove newline
                
                        char *studentResult = swapFirstAndLastWordsSolution(testCase);
                        char *expectedResult = swapFirstAndLastWordsValidator(testCase);
                
                        if (strcmp(studentResult, expectedResult) != 0) {
                            printf("Test case failed: %s\\n", testCase);
                            printf("Expected: %s\\n", expectedResult);
                            printf("Actual: %s\\n", studentResult);
                            free(studentResult);
                            free(expectedResult);
                            fclose(file);
                            return 404;
                        }
                
                        free(studentResult);
                        free(expectedResult);
                    }
                
                    printf("All test cases passed!\\n");
                    fclose(file);
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
                
                clang -std=c23 -o main.out main.c solution.c validator.c
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
