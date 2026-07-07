# Track Day Legends API 🏁

¡Bienvenido a **Track Day Legends API**! Esta es una API REST elegante y robusta diseñada para petrolheads, entusiastas del motor y pilotos amateur de track days. Permite gestionar un catálogo técnico de deportivos icónicos y coches preparados para circuito, organizados por modelos de coches (`CarModel`) y sus respectivas especificaciones o variantes de motorización (`EngineSpec`).

## 🏗️ Arquitectura del Proyecto

El proyecto está diseñado siguiendo rigurosamente los principios de **Arquitectura Hexagonal (Puertos y Adaptadores)** y **Diseño Guiado por el Dominio (DDD)** para garantizar la máxima mantenibilidad, escalabilidad y testabilidad:

1. **Dominio puro (`domain`)**: Contiene las entidades esenciales de negocio (`CarModel` y `EngineSpec`) completamente libres de dependencias de Spring, JPA, Jackson u otros frameworks.
2. **Aplicación (`application`)**: Define los casos de uso (interfaces de entrada `in` y adaptadores de salida `out`) e implementa la lógica de negocio como POJOs puros de Java.
3. **Persistencia (`adapter.outbound.persistence`)**: Adaptadores para Spring Data JPA sobre una base de datos **H2 en memoria**, con mappers manuales explícitos para no acoplar el dominio a la base de datos.
4. **Web (`adapter.inbound.web`)**: Adaptadores REST con controladores limpios, DTOs de petición y respuesta con validaciones declarativas robustas, mappers dedicados y un manejador centralizado de excepciones (`GlobalExceptionHandler`).
5. **Infraestructura (`infrastructure`)**: Configuración explícita de Spring Beans (`BeanConfiguration`) e inicialización de datos de demo realistas (`DemoDataLoader`).

> **Nota de Diseño**: No se utiliza Lombok ni MapStruct para favorecer un código Java 17 explícito, transparente y de altísima legibilidad.

---

## 🗂️ Árbol de Directorios

```text
Track-Day-Legends-API/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── trackdaylegends/
    │   │               ├── TrackDayLegendsApplication.java
    │   │               ├── domain/
    │   │               │   ├── exception/ (Excepciones específicas del dominio)
    │   │               │   └── model/ (Modelos puros: CarModel, EngineSpec)
    │   │               ├── application/
    │   │               │   ├── port/ (Puertos de entrada/salida)
    │   │               │   └── usecase/ (Implementaciones de casos de uso sin estereotipos)
    │   │               ├── adapter/
    │   │               │   ├── inbound/web/ (Controladores REST, DTOs, mappers web, handlers)
    │   │               │   └── outbound/persistence/ (Entidades JPA, repositorios, adaptadores)
    │   │               └── infrastructure/
    │   │                   ├── config/ (Wiring manual de Beans de Spring)
    │   │                   └── bootstrap/ (Carga inicial de datos demo)
    │   └── resources/
    │       └── application.properties
    └── test/ (Tests unitarios e integración JUnit 5 + Mockito)
```

---

## 🚀 Cómo Ejecutar el Proyecto

### Requisitos previos:
- **Java 17** o superior instalado.
- **Maven 3.8+** instalado.

### Pasos para compilar y arrancar:
1. Clona o accede a la carpeta raíz del proyecto:
   ```bash
   cd Track-Day-Legends-API
   ```
2. Compila el proyecto y ejecuta los tests para validar la integridad del código:
   ```bash
   mvn clean test
   ```
3. Ejecuta la aplicación Spring Boot:
   ```bash
   mvn spring-boot:run
   ```
4. El servidor arrancará en el puerto **`8080`** de `localhost`.
5. Puedes acceder a la **Consola H2** en tu navegador:
   - **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - **JDBC URL**: `jdbc:h2:mem:trackdaydb`
   - **Username**: `sa`
   - **Password**: *(vacío)*

---

## 🔌 Endpoints de la API

### 🚗 Gestión de Modelos de Coche (`CarModel`)

| Método | Endpoint | Descripción | Parámetros de Filtro (Opcionales) |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/car-models` | Listar todos los modelos | `brand`, `year`, `segment`, `active` |
| **GET** | `/api/car-models/{id}` | Detalle de un modelo y sus motorizaciones | |
| **POST** | `/api/car-models` | Crear un nuevo modelo de coche | *(Cuerpo JSON)* |
| **PUT** | `/api/car-models/{id}` | Actualizar un modelo de coche | *(Cuerpo JSON)* |
| **PATCH** | `/api/car-models/{id}/deactivate` | Desactivar un modelo y sus variantes | |
| **DELETE** | `/api/car-models/{id}` | Eliminar un modelo de coche | |

### ⚙️ Gestión de Variantes Mecánicas (`EngineSpec`)

| Método | Endpoint | Descripción | Parámetros de Filtro (Opcionales) |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/engine-specs` | Listar todas las motorizaciones | `engineType`, `minHorsepower`, `maxZeroToHundred`, `drivetrain`, `active` |
| **GET** | `/api/engine-specs/{id}` | Obtener una motorización por ID | |
| **POST** | `/api/car-models/{carModelId}/engine-specs` | Crear motorización para un coche | *(Cuerpo JSON)* |
| **PUT** | `/api/engine-specs/{id}` | Actualizar una motorización | *(Cuerpo JSON)* |
| **PATCH** | `/api/engine-specs/{id}/deactivate` | Desactivar una motorización | |
| **DELETE** | `/api/engine-specs/{id}` | Eliminar una motorización | |

### 📈 Consultas de Rendimiento y Estadísticas

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/engine-specs/search/fastest` | Top 5 motorizaciones más rápidas por aceleración 0-100 km/h |
| **GET** | `/api/engine-specs/search/most-powerful` | Top 5 motorizaciones con más caballos de potencia |
| **GET** | `/api/stats/summary` | Cuadro de mando global (totales, medias, récord 0-100, etc.) |
| **GET** | `/api/health/demo` | Endpoint de diagnóstico simple |

---

## 💡 Ejemplos de Peticiones `curl` reales

A continuación se muestran ejemplos prácticos listos para probar la API una vez arrancada:

### 1. Listar modelos de coches (filtrando por la marca "Toyota")
```bash
curl -X GET "http://localhost:8080/api/car-models?brand=Toyota" -H "Accept: application/json"
```

### 2. Obtener un modelo por ID con todas sus motorizaciones (ID 1: Toyota GR Yaris)
```bash
curl -X GET "http://localhost:8080/api/car-models/1" -H "Accept: application/json"
```

### 3. Crear un nuevo modelo de coche (Alpine A110 R)
```bash
curl -X POST "http://localhost:8080/api/car-models" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Alpine",
    "model": "A110 R",
    "year": 2024,
    "segment": "S",
    "bodyStyle": "Coupe",
    "country": "Francia",
    "description": "La versión radical enfocada 100% a pista del icónico Alpine A110, repleta de fibra de carbono para reducir peso al extremo."
  }'
```

### 4. Crear una motorización extrema asociada al modelo creado (ID del modelo creado, por ejemplo, ID 9)
```bash
curl -X POST "http://localhost:8080/api/car-models/9/engine-specs" \
  -H "Content-Type: application/json" \
  -d '{
    "versionName": "A110 R Turini",
    "engineType": "Gasolina Turbo",
    "engineConfiguration": "1.8L L4",
    "displacementCc": 1798,
    "horsepowerHp": 300,
    "torqueNm": 340,
    "transmission": "EDC doble embrague 7 vel.",
    "drivetrain": "RWD",
    "zeroToHundredSeconds": 3.9,
    "topSpeedKph": 285,
    "fuelConsumptionL100": 7.0
  }'
```

### 5. Obtener las motorizaciones más potentes (Top 5 más potentes de la base de datos)
```bash
curl -X GET "http://localhost:8080/api/engine-specs/search/most-powerful" -H "Accept: application/json"
```

### 6. Obtener estadísticas de todo el catálogo técnico
```bash
curl -X GET "http://localhost:8080/api/stats/summary" -H "Accept: application/json"
```

### 7. Comprobar validaciones enviando un modelo de coche inválido (año incorrecto y marca vacía)
```bash
curl -X POST "http://localhost:8080/api/car-models" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "",
    "model": "Invalido",
    "year": 1945,
    "segment": "C"
  }'
```
*Respuesta esperada (400 Bad Request):*
```json
{
  "timestamp": "2026-07-07T17:48:40.123456",
  "status": 400,
  "error": "Bad Request",
  "message": "Error de validación en la petición",
  "path": "/api/car-models",
  "validationErrors": {
    "brand": "La marca no puede estar vacía",
    "year": "El año debe ser como mínimo 1950"
  }
}
```

---

¡Disfruta quemando goma virtual con **Track Day Legends API**! 🏎️💨
