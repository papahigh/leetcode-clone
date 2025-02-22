package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.C
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val C_PROJECT = Project(
    lang = C,
    input = INPUT,
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
                    result[0] = '\0';
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
                        fprintf(stderr, "[JUDGE_ERROR]\n");
                        return 1;
                    }
                
                    char testCase[256];
                
                    while (fgets(testCase, sizeof(testCase), file)) {
                        testCase[strcspn(testCase, "\n")] = 0;
                
                        char *actualResult = swapFirstAndLastWordsSolution(testCase);
                        char *expectedResult = swapFirstAndLastWordsValidator(testCase);
                
                        if (strcmp(actualResult, expectedResult) != 0) {
                            fprintf(stderr, "[JUDGE_FEEDBACK]\n");
                            fprintf(stderr, "WRONG_ANSWER\n");
                            fprintf(stderr, "Input: %s\n", testCase);
                            fprintf(stderr, "Output: %s\n", actualResult);
                            fprintf(stderr, "Expected: %s\n", expectedResult);
                            fprintf(stderr, "[JUDGE_FEEDBACK]\n");
                            free(actualResult);
                            free(expectedResult);
                            fclose(file);
                            return 65;
                        }
                
                        free(actualResult);
                        free(expectedResult);
                    }
                
                    fprintf(stderr, "[JUDGE_FEEDBACK]\n");
                    fprintf(stderr, "ACCEPTED\n");
                    fprintf(stderr, "[JUDGE_FEEDBACK]\n");
                    fclose(file);
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
                
                clang -std=c23 -o main.out main.c solution.c validator.c
                
                EXIT_CODE="${'$'}?"
                if [[ "${'$'}EXIT_CODE" -eq 0 ]]
                then stderr "ACCEPTED"
                fi
                stderr "[JUDGE_FEEDBACK]"
                exit "${'$'}EXIT_CODE"
                """.trimIndent()
        ),

        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
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
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)

