# judge-runtime

This project provides a base Docker image designed to build an environment for competitive programming evaluation.
It is based on Debian 12 and bundled with [nsjail](https://github.com/google/nsjail/) to securely execute code
submissions.

## Supported Languages

Below is the list of languages currently supported alongside their respective compilers/runtimes:

| Language   | Compiler / Runtime |
|------------|--------------------|
| C          | Clang 19           |
| C++        | Clang 19           |
| C#         | .NET 9             |
| Dart       | 3.6.2              |
| Elixir     | 1.14.0             |
| Erlang     | 25                 |
| Go         | 1.19.8             |
| Java       | OpenJDK 21         |
| JavaScript | Node.js 22         |
| Kotlin     | 2.1.10             |
| PHP        | 8.2.26             |
| Python     | 3.11               |
| Ruby       | 3.1.2              |
| Rust       | 1.63.0             |
| Swift      | 6.0.3              |
| TypeScript | 5.7.3              |

## Usage

Build base image with [Packer](https://www.packer.io/)

```shell
packer build .
```

Test base image with [RSpec](https://rspec.info/)

```shell
rspec spec/lang_spec.rb
```
