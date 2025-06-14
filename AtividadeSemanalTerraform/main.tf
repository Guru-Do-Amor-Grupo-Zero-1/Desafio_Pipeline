provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

# Criação de rede para o cluster
resource "google_compute_network" "k8s_network" {
  name                    = "gke-network"
  auto_create_subnetworks = true

  lifecycle {
    prevent_destroy = true
  }
}

# Firewall para permitir acesso ao cluster e apps (inclui HTTP/Grafana/etc.) d
resource "google_compute_firewall" "gke_firewall" {
  name    = "gke-firewall"
  network = google_compute_network.k8s_network.name

  allow {
    protocol = "tcp"
    ports    = ["22", "80", "443", "3000", "9090", "5000"]
  }

  source_ranges = ["0.0.0.0/0"]
}

# Cluster GKE
resource "google_container_cluster" "primary" {
  name     = var.cluster_name
  location = var.region

  remove_default_node_pool = true
  initial_node_count       = 1

  network    = google_compute_network.k8s_network.name
  subnetwork = null
}

# Node Pool
resource "google_container_node_pool" "primary_nodes" {
  name       = "primary-node-pool"
  location   = var.region
  cluster    = google_container_cluster.primary.name

  node_config {
    machine_type = "e2-medium"
    oauth_scopes = [
      "https://www.googleapis.com/auth/cloud-platform",
    ]
  }

  node_count = 1
}

