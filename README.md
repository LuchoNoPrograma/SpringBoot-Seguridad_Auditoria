# Proyecto de demostración de Seguridad y Auditoría en Spring Boot
Este es un proyecto de demostración de Spring Boot, que incluye dependencias para funciones de seguridad y auditoría.

## Descripción:
La aplicación ofrece un sistema de autenticación y autorización que utiliza la base de datos 
para validar las credenciales de los usuarios y determinar sus roles y autoridades. 
Los usuarios pueden acceder a diferentes secciones de la aplicación en función de sus roles y autoridades. 
Además, se utiliza una superclase Auditoria para implementar la auditoría de acciones realizadas por los usuarios, 
como iniciar sesión o actualizar información del perfil.

## Dependencias: 
**spring-boot-starter-data-jpa / 2.7.10** 
**spring-boot-starter-security / 2.7.10**  
**spring-boot-starter-thymeleaf / 2.7.10**  
**spring-boot-starter-web / 2.7.10**  
**thymeleaf-extras-springsecurity5 / 3.0.5.RELEASE**    
**spring-boot-starter-mail / 2.7.10 (opcional)**    
**spring-boot-devtools / 2.7.10 (opcional)**  
**postgresql / 42.3.8 (tiempo de ejecución)**  
**lombok / 1.18.26 (opcional)**  
**spring-boot-starter-test / 2.7.10 (pruebas)**    
**spring-security-test / 5.7.7 (pruebas)**    

## Funcionalidades
- Autenticación y autorización de usuarios con roles y autoridades  
- Control de acceso a rutas por roles y autoridades   
- Auditoría de acciones realizadas por los usuarios  
- CRUD de usuarios, roles y autoridades  

## Construir y Ejecutar
Para ejecutar este proyecto, necesitarás tener instalado Java17 y PostgreSQL en tu sistema. Luego, sigue estos pasos:  
**1.-** Clona este repositorio en tu máquina local.  
**2.-** Crea una base de datos PostgreSQL llamada "seguridad".  
**3.-** Configura tus credenciales de PostgreSQL en el archivo application.properties.  
**4.-** Abre una terminal en el directorio raíz del proyecto y ejecuta el comando mvn spring-boot:run.  
  
Abre un navegador web y navega a http://localhost:9090 para acceder a la aplicación.
  
Nota: La dependencia spring-boot-devtools se incluye como opcional. Esto permitirá reinicios automáticos durante el desarrollo.  
Nota: La dependencia spring-boot-starter-email se incluye como opcional. Esto permitirá enviar emails durante el desarrollo.  
