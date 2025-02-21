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
        testCase[strcspn(testCase, "\n")] = 0; // Remove newline

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
