# leetcode-clone

## Work in Progress (WIP)

This project is currently under development.


## Local Development Setup

This guide provides instructions for setting up a local development environment.

### Prerequisites

Before you begin, ensure you have the following tools installed:

- **Helm:**  A package manager for Kubernetes.  [Install Helm](https://helm.sh/docs/intro/install/)
- **Skaffold:** A command-line tool that facilitates continuous development for Kubernetes applications. [Install Skaffold](https://skaffold.dev/docs/install/)
- **kubectl:** Kubernetes command-line tool. [Install kubectl](https://kubernetes.io/docs/tasks/tools/)


### 1. Set up the Kubernetes Environment

Use Helm to deploy the necessary Kubernetes resources:

```shell
helm install environment ./deploy/ -f ./deploy/values.skaffold.yaml --namespace leetcode-clone --create-namespace
```

This command creates a Kubernetes namespace `leetcode-clone` (if it doesn't already exist) and deploys the environment defined in `./deploy/values.skaffold.yaml`.

### 2. Start Development Mode

Use Skaffold to build and deploy the application in development mode:

```shell
skaffold dev -p backend
```

This command builds the backend service, deploys it to the Kubernetes cluster, and watches for code changes.  
Skaffold will automatically rebuild and redeploy the application whenever you save changes to your source code.
