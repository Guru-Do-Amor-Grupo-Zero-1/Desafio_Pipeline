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

resource "google_container_cluster" "primary" {
  name     = var.cluster_name
  location = var.region
  network  = "gke-network-v2"

  remove_default_node_pool = true
  initial_node_count       = 1
  deletion_protection      = false

  lifecycle {
    ignore_changes = [
      master_version,
      node_locations,
      network,
      subnetwork,
      initial_node_count,         # Continua ignorando após o create
      logging_service,
      monitoring_service,
      services_ipv4_cidr,
      cluster_ipv4_cidr,
      addons_config,
      release_channel,
      private_cluster_config,
      database_encryption,
      ip_allocation_policy,
      resource_labels,
    ]
  }
}

resource "google_container_node_pool" "primary_nodes" {
  name       = "primary-node-pool"
  cluster    = google_container_cluster.primary.name
  location   = google_container_cluster.primary.location
  node_count = 1

  node_config {
    machine_type = "e2-medium"
    disk_type    = "pd-standard"

    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform",
    ]
  }
}
