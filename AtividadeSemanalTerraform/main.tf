terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "~> 6.8"
    }
  }
  required_version = ">= 1.3.0"
}

provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

# Definição do Cluster limpa e correta
resource "google_container_cluster" "primary" {
  name     = var.cluster_name
  location = var.region
  network  = "gke-network-v2"

  # Diz para não criar o pool de nós padrão.
  # As configurações de initial_node_count e node_config foram removidas daqui.
  remove_default_node_pool = true
  
  # A linha abaixo é importante, mas o problema real está na GUI do GCP.
  deletion_protection = false
}

# Node pool
resource "google_container_node_pool" "primary_nodes" {
  name       = "primary-node-pool"
  cluster    = google_container_cluster.primary.name
  location   = google_container_cluster.primary.location
  node_count = 1

  node_config {
    machine_type = "e2-medium"
    disk_size_gb = 12
    disk_type    = "pd-standard"

    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform",
    ]
  }
}