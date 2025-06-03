provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

resource "google_compute_network" "default" {
  name = "network-iac"
}

resource "google_compute_firewall" "default" {
  name    = "allow-ssh-http"
  network = google_compute_network.default.name

  allow {
    protocol = "tcp"
    ports    = ["22", "80", "443", "3000"]
  }

  source_ranges = ["0.0.0.0/0"]
}

resource "google_compute_instance" "vm_instance" {
  name         = var.vm_name
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image  = "${var.image_project}/${var.image_family}"
      size   = var.disk_size
    }
  }

  network_interface {
    network = google_compute_network.default.name

    access_config {
      // Isso gera um IP público
    }
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    sudo apt update
    sudo apt install -y python3
  EOT
}

output "vm_instance_ip" {
  description = "O endereço IP externo da VM."
  value       = google_compute_instance.vm_instance.network_interface[0].access_config[0].nat_ip
}
