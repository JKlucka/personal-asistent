# Stage 1: Build frontend
FROM node:20-alpine AS frontend-builder
WORKDIR /app/frontend
COPY frontend/package.json frontend/package-lock.json* ./
RUN npm ci
COPY frontend/ ./
RUN npm run build

# Stage 2: Build backend
FROM gradle:8.7-jdk17 AS backend-builder
WORKDIR /app/backend
COPY backend/build.gradle.kts backend/settings.gradle.kts ./
# Cache dependencies
RUN gradle dependencies --no-daemon || true
COPY backend/ ./
# Copy compiled frontend into backend static resources
COPY --from=frontend-builder /app/frontend/dist src/main/resources/static/
RUN gradle shadowJar --no-daemon

# Stage 3: Runtime
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --from=backend-builder /app/backend/build/libs/*-all.jar app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
