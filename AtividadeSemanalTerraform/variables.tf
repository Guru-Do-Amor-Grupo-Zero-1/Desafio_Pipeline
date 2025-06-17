variable "project_id" {
  description = "cluster-mensal-grupo-zero1"
  type = string
  default = "projeto-mensal-04-prod"
}

variable "region" {
  default = "us-central1"
}
variable "zone" {
  default = "us-central1-f"
}
variable "cluster_name" {
  default = "cluster-entrega"
}
variable "environment" {
  type    = string
  default = "production"
}