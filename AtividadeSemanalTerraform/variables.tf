variable "project_id" {
  description = "cluster-mensal-grupo-zero1"
  type = string
}

variable "region" {
  default = "us-central1"
}
variable "zone" {
  default = "us-central1-a"
}
variable "cluster_name" {
  default = "cluster-entrega-zero1"
}
variable "environment" {
  type    = string
  default = "stage"
}