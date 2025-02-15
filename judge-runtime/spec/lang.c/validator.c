#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* evaluate(const char *s) {
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
