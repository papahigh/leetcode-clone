package problem.stub.problem1

import problem.Problem


val PROBLEM = Problem(
    id = 1,
    name = "Swap First and Last Words",
    slug = "swap-first-and-last-words",
    body =
        // language=markdown
        """
        You are given a string `s` consisting of words separated by spaces. 
        Your task is to return a new string where the first and last words are swapped, 
        while keeping the other words in their original positions.
        
        - If the string contains only one word, return it unchanged.
        - The string will not contain extra spaces (only a single space between words).
        - It is guaranteed that the string will not be empty.
        
        ---
        ### Input and Output Format
        Input:
        - A string s (1 ≤ length of s ≤ 100)
        
        Output:
        - A string with swapped words.
        
        ---
        ### Example 1
        - Input: "hello world"  
        - Output: "world hello"  
        
        ### Example 2
        - Input: "code your dreams"  
        - Output: "dreams your code"  
        
        ### Example 3
        - Input: Input: "a b"  
        - Output: "b a"  
        
        ### Example 4
        - Input: "word"  
        - Output: "word"
        
        """.trimIndent(),

    input = Problem.ProjectFile(
        name = "input.txt",
        content =
            """
            hello world
            this is easy
            onlyword
            first second
            swap these words please
            one two three four
            a b
            multiple words test case
            practice makes perfect
            keep coding consistently
            """.trimIndent()
    ),

    projects = listOf(
        C_PROJECT,
        CPP_PROJECT,
        CS_PROJECT,
        DART_PROJECT,
        ELIXIR_PROJECT,
        ERLANG_PROJECT,
        GO_PROJECT,
        JAVA_PROJECT,
        JS_PROJECT,
        KOTLIN_PROJECT,
        PHP_PROJECT,
        PYTHON3_PROJECT,
        RUBY_PROJECT,
        RUST_PROJECT,
        SWIFT_PROJECT,
        TS_PROJECT,
    )
)