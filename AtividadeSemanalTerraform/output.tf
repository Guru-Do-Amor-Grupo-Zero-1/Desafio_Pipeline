output "cluster_endpoint" {
  description = "Endpoint do cluster Kubernetes GKE"
  value       = google_container_cluster.primary.endpoint
}

output "cluster_name" {
  description = "Endpoint do cluster Kubernetes GKE"
  value       = google_container_cluster.primary.name
}

