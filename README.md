# hotel-cqrs

Implementación mínima de CQRS para reservas de hotel con:

- `command-service` (Spring Boot): recibe comandos y publica eventos en Kafka.
- `query-service` (Spring Boot): consume eventos de Kafka y mantiene un modelo de lectura en memoria.
- `ui` (Angular): envía comandos y consulta el modelo de lectura.
- `docker-compose.yml`: entorno local con Zookeeper, Kafka y los 3 servicios.

## Arquitectura

1. La UI envía `POST /api/commands/bookings` al servicio de comandos.
2. El servicio de comandos publica `BookingCreatedEvent` en el tópico `hotel-booking-events`.
3. El servicio de consulta consume el evento y actualiza su proyección.
4. La UI consulta `GET /api/bookings` o `GET /api/bookings/{id}` en el servicio de consulta.

## Ejecutar con Docker Compose

```bash
docker compose up --build
```

Servicios disponibles:

- UI Angular: `http://localhost:4200`
- Command API: `http://localhost:8081/api/commands/bookings`
- Query API: `http://localhost:8082/api/bookings`
- Kafka: `localhost:9092`

## Ejecutar localmente sin Docker

### Command service

```bash
cd command-service
mvn spring-boot:run
```

### Query service

```bash
cd query-service
mvn spring-boot:run
```

### UI Angular

```bash
cd ui
npm install
npm start
```
