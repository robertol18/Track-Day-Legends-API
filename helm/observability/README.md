# Observability Stack Helm Chart 📊

This directory contains the Helm umbrella chart for the **Track Day Legends API** observability stack. It packages and coordinates **Grafana**, **Prometheus**, **Loki**, **Tempo**, **OpenTelemetry Collector**, and **Mimir**.

---

## 🚀 Deployment

Before deploying, build chart dependencies:

```bash
helm dependency build ./helm/observability
```

### Install/Upgrade in Dev (`track-day-legends-dev`)

```bash
helm upgrade --install track-day-observability ./helm/observability \
  -f ./helm/observability/values.yaml \
  -f ./helm/observability/values-dev.yaml \
  --namespace track-day-legends-dev \
  --create-namespace
```

### Install/Upgrade in Test (`track-day-legends-tst`)

```bash
helm upgrade --install track-day-observability ./helm/observability \
  -f ./helm/observability/values.yaml \
  -f ./helm/observability/values-tst.yaml \
  --namespace track-day-legends-tst \
  --create-namespace
```

---

## 🔍 How to Access and Browse Deployed Services

### 1. Check Pod and Service Status

Verify that all component pods are running:

```bash
# List all running pods in the observability namespace
kubectl get pods -n track-day-legends-dev

# List all services and their target ports
kubectl get svc -n track-day-legends-dev
```

---

### 2. Access Grafana (Central Unified Dashboard)

Grafana is pre-configured with datasources for **Prometheus**, **Mimir**, **Loki**, and **Tempo**.

* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/grafana 3000:80 -n track-day-legends-dev
  ```
* **URL:** [http://localhost:3000](http://localhost:3000)
* **Credentials:**
  * **Username:** `admin`
  * **Password (dev):** `admin-dev` (configured in `values-dev.yaml`)
  * **Password (default):** `admin` (configured in `values.yaml`)

> **Usage:** Go to **Explore** or **Dashboards** in Grafana to view metrics, search application logs, and trace requests.

---

### 3. Access Individual Service Endpoints

#### 📊 Prometheus (Metrics Query UI)
* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/prometheus-server 9090:80 -n track-day-legends-dev
  ```
* **URL:** [http://localhost:9090](http://localhost:9090)

#### 📡 OpenTelemetry Collector (Receiver & Health)
* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/otel-collector 4318:4318 13133:13133 -n track-day-legends-dev
  ```
* **Endpoints:**
  * **HTTP Ingestion:** `http://localhost:4318` (OTLP traces/metrics/logs)
  * **Health Check:** `http://localhost:13133`

#### 🏛️ Mimir (Long-Term Metrics Storage)
* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/mimir 8080:8080 -n track-day-legends-dev
  ```
* **Endpoint:** `http://localhost:8080/prometheus`

#### 📜 Loki Gateway (Log Storage & Query API)
* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/loki-gateway 3100:80 -n track-day-legends-dev
  ```
* **Endpoint:** `http://localhost:3100`

#### 🔍 Tempo (Distributed Tracing Storage)
* **Port-Forward Command:**
  ```bash
  kubectl port-forward svc/tempo 3200:3200 -n track-day-legends-dev
  ```
* **Endpoint:** `http://localhost:3200`

---

## 📋 Quick Reference Table

| Component | Service Name (`svc`) | Local Port | Access / Inspection Endpoint |
| :--- | :--- | :--- | :--- |
| **Grafana** | `grafana` | `3000` | [http://localhost:3000](http://localhost:3000) *(Unified UI)* |
| **Prometheus** | `prometheus-server` | `9090` | [http://localhost:9090](http://localhost:9090) *(PromQL UI)* |
| **OTEL Collector** | `otel-collector` | `4318` / `13133` | OTLP Ingestion & Health Check |
| **Mimir** | `mimir` | `8080` | `http://localhost:8080/prometheus` |
| **Loki** | `loki-gateway` | `3100` | Log Ingestion & Query API |
| **Tempo** | `tempo` | `3200` | Trace Query API |
