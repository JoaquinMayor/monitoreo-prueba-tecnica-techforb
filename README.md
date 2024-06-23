# MonitoreoPruebaTecnicaTechfordBackend

## Prueba técnica de BackEnd Con SpringBoot de sistema de plantas y alertas.
### Solicitante TechFord.
Aplicación solicitada para la prueba técnica de TeckFornd sobre sistema de gestión de plantas y lecturas de la misma, acompañada con un sistema de logueo y registrop de usuarios.

## Secciones
El proyecto está dividido en diferentes carpetas para diferenciar y separar sus distintos componentes entre ellos se encuentra las **entidades**, los **repositorios**, los **servicios**, los **controladores** y el sistema de **seguridad**.
Dentro de cada uno podremos encontrar los archivos correspondientes a continuación se mostrarán como se encuentran clasificados.

## Entidades
En las entidades se busca crear clases que se mappen a la base de datos para luego poderlos manejar en los repositorios.
### Carpeta "*plantas*"
- Alerta
- TipoLectura
 - Sensor
 - Lectura
 - Planta
 
 ### Carpeta "*usuarios*"
 - Roles
 - Usuarios
 
 ## Repositorios
 Los repositorios se van a encargar de la conección con la base de datos y el mapeo con las entidades creadas.
 ### Carpeta "*planta*"
 - AlertaRepository
 - TipoLEcturaRepository
 - LecturaRepository
 - SensorRepository
 - PlantaRepository
 
 ### Carpeta "*usuario*"
 - RolRepository
 - UsuarioRepository
 
 ## Servicios
 Los servicios van a ser los encargados de manejar toda la logica de negocio con relación a la información recibida de los controladores y los repositorios.
 Entre ellos contamos con:
 - JpaUserDEtailsService
 - LecturaService
 - PlantaService
 - SensorService
 -UsuarioService
 
 ### Controladores
 Los controladores van a ser los encargados de permitir la generación de las apis y distintos links para conectar con el frontend.
 
 Dentro de los controladores contamos con:
 
 - HandlerExceptionController
 - LecturaController
 - PlantaController
 - SensorController
 - UsuarioController
 
 ### Seguridad
 Dentro de la carpeta de seguridad *"segurity"filter"* podremos encontrar la configuración de spring security haciendo manejo de roles para el acceso a las distintas peticiones y generación de tokens en base a su rol, además de configurar el *PasswordEncoded* para poder encriptar las contraseñas de los usuarios.