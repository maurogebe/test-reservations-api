# API de Reservaciones de Hotel

## Descripción

Esta es una API para la gestión de reservaciones en hoteles. La API permite manejar países, estados, ciudades, hoteles, habitaciones y reservas, con autenticación y autorización implementadas mediante JWT.

## Tecnologías Usadas

- **Java:** Lenguaje principal para el desarrollo de la lógica de negocio. ☕
- **Spring Boot:** Framework utilizado para crear servicios web RESTful. 🌿
- **Spring Security:** Para la implementación de seguridad en la aplicación. 🔐
- **JWT (JSON Web Tokens):** Para la generación y validación de tokens JWT.
- **MySQL:** Sistema de gestión de base de datos utilizado para almacenar la información de la aplicación. 💾
- **Mailjet Client:** Para el envío de correos electrónicos. ✉️
- **Swagger:** Para la documentación interactiva de la API. 📄
- **MapStruct:** Para el mapeo de entidades y DTOs. 🔄
- **Lombok:** Para reducir el código boilerplate en Java. 🔧

## Instalación

Para clonar este repositorio y ejecutar la aplicación localmente, sigue estos pasos:

1. **Clona el repositorio:**
    ```bash
    git clone https://github.com/maurogebe/hotel-reservation-api.git
    ```

2. **Navega al directorio del proyecto:**
    ```bash
    cd hotel-reservation-api
    ```

3. **Configura la base de datos:**
   Asegúrate de tener MySQL configurado y ejecutándose. Actualiza las propiedades de la base de datos en `application.properties` o `application.yml`.

4. **Construye el proyecto usando Gradle:**
    ```bash
    ./gradlew build
    ```

5. **Ejecuta la aplicación:**
    ```bash
    ./gradlew bootRun
    ```

6. **Accede a la documentación Swagger:**
   Una vez la aplicación esté corriendo, puedes ver la documentación de la API en el siguiente enlace:
    ```
    http://localhost:8080/swagger-ui/index.html
    ```

## Primeros pasos

### Sembrar datos iniciales

1. **Sembrar países, estados y ciudades:**
   Ejecuta el seed de países, estados y ciudades usando el siguiente endpoint:
    ```bash
    POST http://localhost:8080/country/seed
    ```

2. **Sembrar hoteles y habitaciones:**
   Ejecuta el seed de hoteles y habitaciones usando el siguiente endpoint:
    ```bash
    POST http://localhost:8080/hotel/seed
    ```

### Creación de usuario y autenticación

1. **Registrar un nuevo usuario:**
   Puedes crear un usuario enviando una petición `POST` al siguiente endpoint:
    ```bash
    POST http://localhost:8080/auth/sign-up
    Content-Type: application/json

    {
      "firstName": "Mauricio",
      "lastName": "Mauricio",
      "email": "maurogebe.96@gmail.com",
      "password": "Admin1234"
    }
    ```

2. **Iniciar sesión:**
   Para obtener el token de autenticación, envía una petición `POST` al siguiente endpoint:
    ```bash
    POST http://localhost:8080/auth/sign-in
    Content-Type: application/json

    {
      "email": "maurogebe.96@gmail.com",
      "password": "Admin1234"
    }
    ```

   Esto te devolverá un token JWT que deberás utilizar para autenticarte en los demás endpoints protegidos.

### Acceso a endpoints públicos

- **/country/seed** y **/hotel/seed**: Estos endpoints están disponibles sin autenticación.
- **/auth/sign-up** y **/auth/sign-in**: Endpoints para registrar y autenticar usuarios, sin protección.

### Acceso a endpoints protegidos

Para acceder a otros endpoints de la API, necesitarás el token JWT obtenido en el paso de inicio de sesión. Usa este token en el encabezado `Authorization` como `Bearer <tu-token>`.

## Contacto

- **Nombre:** Fabian Mauricio Guerra Bedoya
- **LinkedIn:** [Fabian Mauricio Guerra Bedoya](https://www.linkedin.com/in/fabian-mauricio-guerra-bedoya)
- **Correo Electrónico:** maurogebe.96@gmail.com

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
