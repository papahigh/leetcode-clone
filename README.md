# leetcode-clone

This project showcases the implementation of scalable sandboxing through a LeetCode-style application.

![LeetCodeClone: a new way to learn](./assets/screenshot.png?raw=true "LeetCodeClone")

Please see the [list](/judge-runtime) of supported programming languages.

## Getting Started

This guide will assist you in setting up the **LeetCodeClone** project in your local development environment.

### Prerequisites

Before beginning the setup, ensure you have the following tools installed on your system:

1. [**Helm**](https://helm.sh/docs/intro/install/): A package manager for Kubernetes.
2. [**Skaffold**](https://skaffold.dev/docs/install/): Streamlines continuous development and deployment for Kubernetes applications.
3. [**kubectl**](https://kubernetes.io/docs/tasks/tools/): A powerful Kubernetes CLI tool to manage cluster resources.


### 1. Environment Setup

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

