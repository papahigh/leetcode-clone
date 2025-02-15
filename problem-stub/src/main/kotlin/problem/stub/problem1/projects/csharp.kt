package problem.stub.problem1.projects

import problem.Problem.*
import problem.Resources


val CS_PROJECT = Project(
    lang = Language.CSHARP,
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
                                Console.WriteLine($"Test case failed: {testCase}");
                                Console.WriteLine($"Expected: {expected}");
                                Console.WriteLine($"Actual: {actual}");
                                Environment.Exit(404);
                            }
                        }
                
                        Console.WriteLine("All test cases passed!");
                    }
                }
                """.trimIndent()
        ),
        compile = ProjectFile(
            name = "compile.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                dotnet build --configuration Release -o ./out .
                """.trimIndent()
        ),
        execute = ProjectFile(
            name = "execute.sh",
            content =
                // language=sh
                """
                #!/usr/bin/env sh
                
                ./out/main
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
    limits = Resources(time = 10, memory = 262144)
)
