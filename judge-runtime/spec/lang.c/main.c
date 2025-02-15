#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* swapFirstAndLastWordsSolution(const char *s);
char* swapFirstAndLastWordsValidator(const char *s);

int main() {
    FILE *file = fopen("input.txt", "r");
    if (!file) {
        printf("Error: Could not open input.txt\n");
        return 1;
    }

    char testCase[256];

    while (fgets(testCase, sizeof(testCase), file)) {
        testCase[strcspn(testCase, "\n")] = 0; // Remove newline

        char *studentResult = swapFirstAndLastWordsSolution(testCase);
        char *expectedResult = swapFirstAndLastWordsValidator(testCase);

        if (strcmp(studentResult, expectedResult) != 0) {
            printf("Test case failed: %s\n", testCase);
            printf("Expected: %s\n", expectedResult);
            printf("Actual: %s\n", studentResult);
            free(studentResult);
            free(expectedResult);
            fclose(file);
            return 404;
        }

        free(studentResult);
        free(expectedResult);
    }

    printf("All test cases passed!\n");
    fclose(file);
    return 0;
}
