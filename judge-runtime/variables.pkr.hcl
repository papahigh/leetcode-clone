variable "judge_docker_base_image" {
  type    = string
  default = "docker.io/papahigh/nsjail"
}

variable "judge_docker_image_name" {
  type    = string
  default = "papahigh/judge-runtime"
}

variable "judge_docker_image_tags" {
  type = list(string)
  default = ["1.0", "latest"]
}

variable "judge_user" {
  type    = string
  default = "judge"
}

variable "ansible_host" {
  type    = string
  default = "judge-runtime-build"
}

variable "ansible_connection" {
  type    = string
  default = "docker"
}
