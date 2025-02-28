# leetcode-clone

This project showcases the implementation of sandboxing in Kubernetes through a LeetCode-style application.

## Supported Languages

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


## Getting Started

This guide will assist you in setting up the **LeetCodeClone** project in your local development environment.

### Prerequisites

Before beginning the setup, ensure you have the following tools installed on your system:

1. [**Helm**](https://helm.sh/docs/intro/install/): A package manager for Kubernetes.
2. [**Skaffold**](https://skaffold.dev/docs/install/): Streamlines continuous development and deployment for Kubernetes applications.
3. [**kubectl**](https://kubernetes.io/docs/tasks/tools/): A powerful Kubernetes CLI tool to manage cluster resources.


### 1. Kubernetes Environment Setup

Deploy the application's Kubernetes resources with **Helm** as follows:

```shell
helm install environment ./deploy/ -f ./deploy/values.skaffold.yaml --namespace leetcode-clone --create-namespace
```

This command performs the following steps:
- Deploys the application's configurations as defined in `values.skaffold.yaml`.
- Creates a new Kubernetes namespace named `leetcode-clone` (if it doesn’t exist already).

### 2. Start the Application

To launch the application locally, use the **Skaffold** CLI:

```bash
skaffold dev -p all
```
This command starts the application in development mode, deploying the necessary Kubernetes configurations and Docker containers.

### 3. Open Your Browser

Once all services are up and running, navigate to the following URL in your browser: [http://localhost:3000](http://localhost:3000).
You'll see a demo problem ready to be solved!


![LeetCodeClone: a new way to learn](./assets/screenshot.png?raw=true "LeetCodeClone")
