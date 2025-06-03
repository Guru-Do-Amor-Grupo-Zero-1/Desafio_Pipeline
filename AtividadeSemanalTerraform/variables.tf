variable "project_id" {
  description = "ID do projeto GCP"
  type        = string
}

variable "region" {
  default     = "us-central1"
  description = "Região onde a VM será criada, mudar caso necessário (pegar o mais barato)"
}

variable "zone" {
  default     = "us-central1-a"
  description = "Zona onde a VM será criada, mudar caso necessário (pegar o mais barato)"
}

variable "vm_name" {
  default     = "vm-entrega-semanal-01"
}

variable "machine_type" {
  default     = "e2-medium"
}

variable "image_family" {
  default     = "ubuntu-2204-lts"
}

variable "image_project" {
  default     = "ubuntu-os-cloud"
}

variable "disk_size" {
  default     = 20
}
