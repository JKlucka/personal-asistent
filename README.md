# Personal Assistant

A self-hosted personal assistant with a Kotlin/Ktor backend, React frontend, and WhatsApp integration via Twilio. Runs on Kubernetes with Traefik, cert-manager, and ArgoCD.

## Stack

| Layer       | Technology                              |
|-------------|-----------------------------------------|
| Backend     | Kotlin 1.9, Ktor 2.x, Exposed 0.50, PostgreSQL 16 |
| Frontend    | React 18, TypeScript 5, Vite 5          |
| AI          | Anthropic Claude (via API)              |
| Messaging   | Twilio WhatsApp                         |
| Container   | Docker, GitHub Container Registry       |
| Kubernetes  | k3s / any cluster, Traefik, cert-manager, ArgoCD |

## Local Development

### Prerequisites

- Docker & Docker Compose
- (Optional) JDK 17+ and Node 20 for running services outside Docker

### Start everything

```bash
cp .env.example .env
# Fill in your credentials in .env

# Backend + database only
docker compose up

# Backend + database + Vite dev server
docker compose --profile frontend-dev up
```

- Backend API: http://localhost:8080
- Frontend dev server: http://localhost:5173 (proxies `/api` to backend)
- PostgreSQL: localhost:5432

### Run backend tests

```bash
cd backend
./gradlew test
```

### Run frontend tests & lint

```bash
cd frontend
npm ci
npm run lint
npm run test
```

## Project Structure

```
personal-assistant/
├── backend/        Kotlin + Ktor API server
├── frontend/       React + TypeScript SPA
├── k8s/            Kubernetes manifests
├── infra/
│   ├── traefik/    Helm values & middleware CRDs
│   ├── cert-manager/ ClusterIssuer, DNS-01
│   └── argocd/     App-of-apps, ArgoCD install
└── .github/workflows/ CI + Docker build pipelines
```
