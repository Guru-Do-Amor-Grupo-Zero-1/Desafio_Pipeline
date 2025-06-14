output "cluster_endpoint" {
  description = "Endpoint do cluster Kubernetes GKE"
  value       = google_container_cluster.primary.endpoint
}
