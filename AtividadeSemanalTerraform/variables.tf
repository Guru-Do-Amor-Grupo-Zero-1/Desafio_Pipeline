variable "project_id" {}
variable "region" {
  default = "us-central1"
}
variable "zone" {
  default = "us-central1-a"
}
variable "cluster_name" {
  default = "cluster-entrega"
}
variable "environment" {
  type    = string
  default = "stage"
}