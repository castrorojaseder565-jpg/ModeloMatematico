# EduOptima AI

Asignación óptima de estudiantes a tutores: **Spring Boot + MySQL + FastAPI/PuLP (Investigación de Operaciones)**.
Curso Desarrollo Web Avanzado · Tecnocomfenalco · 2026.

## Requisitos
- Java 21+, Maven (o `mvnw`)
- MySQL 8.x en `localhost:3306` (usuario `root`, sin contraseña; editar `application.properties` si difiere)
- Python 3.10+

## Ejecución (en este orden)

### 1. Microservicio de optimización (puerto 8000)
```bash
cd optimizer-api
python -m venv venv
# Windows: .\venv\Scripts\Activate.ps1      Linux/macOS: source venv/bin/activate
pip install -r requirements.txt            # fija pulp==2.9.0 (ver nota)
uvicorn main:app --reload --port 8000
# Swagger: http://127.0.0.1:8000/docs
```

### 2. Base de datos
Hibernate crea las tablas solo (`ddl-auto=update`, `createDatabaseIfNotExist=true`).
Para cargar datos de ejemplo después del primer arranque de Spring Boot:
```bash
mysql -uroot < sql/seed.sql
```
(`sql/schema.sql` contiene el esquema equivalente, por si se quiere crear a mano.)

### 3. Aplicación Spring Boot (puerto 8080)
```bash
./mvnw spring-boot:run        # Windows: .\mvnw spring-boot:run
```
Abrir http://localhost:8080 → **Ejecutar Optimización** (`/optimize`).

## Resultado esperado con `sql/seed.sql`
Valor óptimo Z = **169**: María López→Carlos Ruiz (50), Ana García→Carlos Ruiz (36),
Laura Díaz→Julián Torres (32), Andrés Cano→Julián Torres (30), Luis Pérez→Sandra Mora (21).
Sin asignar: Pedro Gómez y Sofía Rojas (capacidad agotada) y Camila Vega (sin tutor de Química).

## Notas
- `pulp==2.9.0` está fijado porque PuLP 4.x cambió la API de `LpVariable` (la guía usa PuLP 2.x).
- `Assigment` lleva un `@Id`: una `@Entity` sin identificador impide arrancar a Hibernate.
- `OptimizationController` muestra un mensaje si FastAPI no responde (mejora sobre la guía).
- Evidencia de ejecución en `docs/evidencia/`.
