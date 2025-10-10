# Umascotas
Plataforma planeada con la finalidad de permitir un proceso de adopción, para animales domésticos sin dueño en la universidad Industrial de Santander

## Demo JWT (Backend Spring Boot + Frontend simple)

### Endpoints clave
- POST `/auth/login`: recibe `{ correoElectronico, contrasena }` y devuelve `{ token, tipo: "Bearer" }` si las credenciales son válidas.
- GET `/api/protected/hello`: requiere header `Authorization: Bearer <token>`.
- GET `/jwt`: vista HTML para probar el flujo (login y llamada protegida desde el navegador).

### Configuración
Variables en `application.properties` (con valores por defecto):
- `jwt.secret` (min 32 bytes en HS256)
- `jwt.expirationMillis` (ej. 3600000 = 1h)
- `jwt.issuer`

### Cómo probar
1. Inicia la app (puerto 8080 por defecto).
2. Registra un usuario: `POST /auth/registro` con JSON del usuario.
3. Abre `http://localhost:8080/jwt`.
4. Haz login con tu correo y contraseña; copia el token generado.
5. Pulsa el botón para llamar `GET /api/protected/hello` usando el header Bearer.

### Nota
Este ejemplo es educativo: no incluye filtros de seguridad ni persistencia de sesiones; el token se genera y valida manualmente.