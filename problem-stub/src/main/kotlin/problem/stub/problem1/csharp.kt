package problem.stub.problem1

import problem.Problem.*
import problem.Problem.Language.CSHARP
import problem.Resources
import problem.resources.Memory.Companion.kilobytes
import problem.resources.Time.Companion.seconds


val CS_PROJECT = Project(
    lang = CSHARP,
    input = INPUT,
    files = ProjectFiles(
        solution = ProjectFile(
            name = "solution.cs",
            content =
                // language=csh
                """
                using System;
                
                public class Solution {
                    public string SwapFirstAndLastWords(string s) {
                
                    }
                }
                """.trimIndent()
        ),
        validator = ProjectFile(
            name = "validator.cs",
            content =
                // language=csh
                """
                using System;
                
                public class Validator {
                    public string SwapFirstAndLastWords(string s) {
                        var words = s.Split(' ');
                
                        if (words.Length <= 1) {
                            return s;
                        }
                
                        (words[0], words[^1]) = (words[^1], words[0]);
                
                        return string.Join(" ", words);
                    }
                }
                """.trimIndent()
        ),
        evaluator = ProjectFile(
            name = "main.cs",
            content =
                // language=csh
                """
                using System;
                using System.IO;
                
                class Program {
                    static void Main() {
                        var solution = new Solution();
                        var validator = new Validator();
                
                        foreach (var testCase in File.ReadAllLines("input.txt")) {
                            var actual = solution.SwapFirstAndLastWords(testCase);
                            var expected = validator.SwapFirstAndLastWords(testCase);
                
                            if (actual != expected) {
                                Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                                Console.Error.WriteLine("WRONG_ANSWER");
                                Console.Error.WriteLine(${'$'}"Input: {testCase}");
                                Console.Error.WriteLine(${'$'}"Output: {actual}");
                                Console.Error.WriteLine(${'$'}"Expected: {expected}");
                                Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                                Environment.Exit(65);
                            }
                        }
                
                        Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                        Console.Error.WriteLine("ACCEPTED");
                        Console.Error.WriteLine("[JUDGE_FEEDBACK]");
                    }
                }
                """.trimIndent()
        ),
        resources = listOf(
            ProjectFile(
                name = "main.csproj",
                content =
                    // language=xml
                    """
                    <Project Sdk="Microsoft.NET.Sdk">
                      <PropertyGroup>
                        <OutputType>Exe</OutputType>
                        <TargetFramework>net9.0</TargetFramework>
                        <ImplicitUsings>enable</ImplicitUsings>
                        <Nullable>enable</Nullable>
                      </PropertyGroup>
                    </Project>
                    """.trimIndent()
            )
        )
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
                
                dotnet build --configuration Release -o ./out .
                
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
                
                ./out/main
                """.trimIndent()
        ),
        resources = Resources(time = 3.seconds, memory = 75000.kilobytes)
    ),
)
