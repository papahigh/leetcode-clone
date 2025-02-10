packer {
  required_plugins {
    ansible = {
      version = ">= 1.1.2"
      source  = "github.com/hashicorp/ansible"
    }
    docker = {
      version = ">= 1.1.1"
      source  = "github.com/hashicorp/docker"
    }
  }
}

source "docker" "judge" {
  image  = var.judge_docker_base_image
  commit = true
  changes = [
    "USER ${var.judge_user}"
  ]
  run_command = [
    "-d",
    "-i",
    "-t",
    "--name",
    var.ansible_host,
    "{{.Image}}",
    "/bin/bash"
  ]
}

build {
  name = "judge-runtime-build"

  sources = [
    "source.docker.judge"
  ]

  provisioner "shell" {
    inline = [
      "apt-get update",
      "apt-get install -y --no-install-recommends git curl ca-certificates python3 unzip"
    ]
  }

  provisioner "shell" {
    inline = [
      "groupadd --gid 1000 ${var.judge_user}",
      "useradd -M --uid 1000 --gid 1000 --home-dir /usr/share/${var.judge_user} --shell /bin/bash ${var.judge_user}"
    ]
  }

  provisioner "shell" {
    inline = [
      "mkdir /usr/share/${var.judge_user}",
      "chown -R ${var.judge_user}:${var.judge_user} /usr/share/${var.judge_user}",
    ]
  }

  provisioner "ansible" {
    user          = "root"
    playbook_file = "ansible/playbook.yml"
    extra_arguments = [
      "--extra-vars",
      "ansible_host=${var.ansible_host} ansible_connection=${var.ansible_connection}"
    ]
  }

  provisioner "shell" {
    inline = [
      "apt-get clean"
    ]
  }

  post-processors {
    post-processor "docker-tag" {
      repository = var.judge_docker_image_name
      tags       = var.judge_docker_image_tags
    }
  }
}
