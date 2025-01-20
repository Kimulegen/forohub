# ForoHub

## Descripción General
ForoHub es una API REST que proporciona una plataforma para que los usuarios participen en discusiones a través de tópicos y respuestas. La plataforma implementa mecanismos seguros de autenticación y autorización para garantizar la privacidad de los datos y la seguridad del usuario.

## Características
- Autenticación y registro de usuarios
- Seguridad basada en JWT
- Creación y gestión de tópicos
- Sistema de respuestas
- Gestión de perfiles de usuario

## Stack Tecnológico
- Java Spring Boot
- Spring Security
- Autenticación JWT
- Encriptación de Contraseñas BCrypt
- MySQL/PostgreSQL (Base de datos)
- Maven (Herramienta de construcción)

## Endpoints de la API

### Autenticación
- `POST /login` - Autenticar usuario y recibir token JWT
- `POST /register` - Registrar nuevo usuario

### Usuarios
- `GET /users/userss` - Listar todos los usuarios (paginado)
- `GET /users/{id}` - Obtener detalles del usuario
- `PUT /user/{id}` - Actualizar información del usuario
- `DELETE /users/{id}` - Desactivar usuario
- `DELETE /user/{id}/deactivate` - Endpoint alternativo de desactivación

### Tópicos
- `POST /topic/topic` - Crear nuevo tópico
- `GET /topic/topics` - Listar todos los tópicos (paginado)
- `GET /topic/{id}` - Obtener detalles del tópico
- `PUT /topic/{id}` - Actualizar tópico
- `DELETE /topic/{id}` - Eliminar tópico

### Respuestas
- `POST /response` - Crear nueva respuesta
- `GET /response/responses` - Listar todas las respuestas (paginado)
- `GET /response/{id}` - Obtener detalles de la respuesta
- `PUT /response/{id}` - Actualizar respuesta
- `DELETE /response/{id}` - Eliminar respuesta

## Seguridad
- Autenticación basada en token JWT
- Encriptación de contraseñas con BCrypt
- Gestión de sesiones sin estado
- Endpoints protegidos que requieren autenticación


## Dependencias Requeridas
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
    </dependency>
    <!-- Dependencias adicionales necesarias -->
</dependencies>
```

## Comenzando

### Prerrequisitos
- Java 17 o superior
- Maven
- Base de datos MySQL/PostgreSQL

### Pasos de Instalación
1. Clonar el repositorio
2. Configurar propiedades de la base de datos en `application.properties`
3. Ejecutar `mvn clean install`
4. Iniciar la aplicación con `mvn spring-boot:run`

## Contribuir
Este es un proyecto de desafío pero las contribuciones son bienvenidas:
1. Hacer fork del repositorio
2. Crear una rama de funcionalidad
3. Hacer commit de los cambios
4. Hacer push a la rama
5. Crear un Pull Request

## Estructura del Proyecto
```
forohub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/kimulegen/forohub/
│   │   │       ├── controller/
│   │   │       ├── domain/
│   │   │       │   ├── user/
│   │   │       │   ├── topic/
│   │   │       │   └── response/
│   │   │       └── infra/
│   │   │           └── security/
│   │   └── resources/
│   └── test/
└── pom.xml
```
