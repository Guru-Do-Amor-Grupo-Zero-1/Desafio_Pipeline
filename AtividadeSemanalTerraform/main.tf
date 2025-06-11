provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

variable "ssh_pub_key" {
  description = "A chave pública SSH para o usuário de automação."
  type        = string
}

resource "google_compute_network" "default" {
  name = "network-iac"
}

resource "google_compute_firewall" "default" {
  name    = "allow-ssh-http"
  network = google_compute_network.default.name

  allow {
    protocol = "tcp"
    ports    = ["22", "80", "443", "9090","3000"]
  }

  source_ranges = ["0.0.0.0/0"]
}

resource "google_compute_instance" "vm_instance" {
  name         = var.vm_name
  machine_type = var.machine_type
  zone         = var.zone

  boot_disk {
    initialize_params {
      image = "${var.image_project}/${var.image_family}"
      size  = var.disk_size
    }
  }

  network_interface {
    network = google_compute_network.default.name
    access_config {
      // Isso gera um IP público
    }
  }

  // Script de inicialização robusto para criar o usuário e configurar o SSH
  metadata_startup_script = <<-EOT
    #!/bin/bash
    # Espera um pouco para garantir que a rede esteja pronta
    sleep 10

    # Cria o usuário 'josim' se ele não existir
    id -u josim &>/dev/null || useradd -m -s /bin/bash -G google-sudoers josim

    # Define o diretório home e o arquivo de chaves autorizadas
    USER_HOME="/home/josim"
    SSH_DIR="$USER_HOME/.ssh"
    AUTH_KEYS_FILE="$SSH_DIR/authorized_keys"

    # Cria o diretório .ssh e o arquivo authorized_keys
    mkdir -p "$SSH_DIR"
    touch "$AUTH_KEYS_FILE"

    # Adiciona a chave pública ao arquivo de chaves autorizadas
    echo "${var.ssh_pub_key}" >> "$AUTH_KEYS_FILE"

    # Define as permissões corretas
    chown -R josim:josim "$USER_HOME"
    chmod 700 "$SSH_DIR"
    chmod 600 "$AUTH_KEYS_FILE"

    # Instala dependências básicas
    apt-get update
    apt-get install -y python3
  EOT

  // Remove a dependência dos metadados de SSH do projeto para evitar conflitos
  metadata = {
    block-project-ssh-keys = "TRUE"
  }
}

output "vm_instance_ip" {
  description = "O endereço IP externo da VM."
  value       = google_compute_instance.vm_instance.network_interface[0].access_config[0].nat_ip
}