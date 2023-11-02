# Equipo-FRA
Proyecto final Java-Escuela 

## INTEGRANTES:

1. Castro San Agustin Rey
2. Vélez Saldaña Fernando
3. Vences Martinez Alejandro Aldair

## DESCRIPCIÓN:

En este proyecto se han generado 3 subproyectos que a su vez, levantan una API de servicios rest pensadas para el uso diario de una escuela, para los siguientes departamentes:

 - Servicios Escolares
 - Biblioteca
 - Materias

Cada una de estas API, se ha configurado para que se ejecuten en un puerto predeterminado para permitir la carga simultánea y la interacción entre ellos.

Cada api cuenta con la configuración maven para poder obtener las versiones y dependencias correspondientes y a su vez cuentan con la configuración para poder acceder a cada end point desde swagger: <http://localhost:[puerto]/swagger-ui/index.html#/>.

### API Servicios Escolares:

Esta API se encuentra configurada para ejecutarse sobre el puerto 8082, y está destinada para proveer de los servicios necesarios para gestionar tanto a los alumnos como a la inscripción en cada periodo:
 - Consulta, Crea, Actualiza y Borra alumnos
 - Consulta si el alumno tienen adeudos desde biblioteca
 - Consulta, Crea, Actualiza y Borra Inscripciones, validando que no tenga adeudos en la biblioteca
 - Indica si el alumno esta inscrito en el periodo actual

Para acceder a swagger será la siguiente liga: <http://localhost:8082/swagger-ui/index.html#/>

Para acceder a la documentación del código se podrá acceder mediante la ruta de instalación: *[DIRECTORIO_INSTALACION]ServiciosEscolares\target\site\apidocs\index.html*

### API Biblioteca

Esta API se encuentra configurada para ejecutarse sobre el puerto 8083, y esta destinada para proveer de los servicios necesarios para gestionar tanto los libros contenidos como a la préstamos realizados a los alumnos:
 - Consulta, Crea, Actualiza y borra libros
 - Consulta el alumno desde servicios escolares
 - Consulta la inscripción desde servicios escolares
 - Registra el préstamo de un libro, validando que este inscrito en el periodo actual
 - Registra la entrega de un libro
 - Indica si el alumno tiene adeudos

Para acceder a swagger será la siguiente liga: <http://localhost:8083/swagger-ui/index.html#/>

Para acceder a la documentación del código se podrá acceder mediante la ruta de instalación: *[DIRECTORIO_INSTALACION]Biblioteca\target\site\apidocs\index.html*

### Api Materias

Esta API se encuenrta configurada para ejecutarse sobre el puerto 8084, y esta destinada para proveer de los servicios necesarios para gestionar tanto los profesores, materias y tira de materias por alumno:
 - Consulta, Crea, Actualiza y borra materias
 - Consulta, Crea, Actualiza y borra profesores
 - Consulta el alumno desde servicios escolares
 - Consulta la inscripción desde servicios escolares
 - Consulta si el alumno tienen adeudos desde biblioteca
 - Consulta, Crea, Actualiza y borra tiras de materia, validando que no tenga adeudos en la biblioteca y que este inscrito en el periodo actual


Para acceder a swagger será la siguiente liga: http://localhost:8084/swagger-ui/index.html#/

Para acceder a la documentación del código se podrá acceder mediante la ruta de instalación: *[DIRECTORIO_INSTALACION]Materias\target\site\apidocs\index.html*

## REQUISITOS

Para poder inicializar estos servicios es necesario contar con lo siguiente:
 - Contar con la variable de ambiente JAVA_HOME con la ruta donde se tiene instalada la versión de java.
 - Contar con la variable de ambiente M2_HOME con la ruta donde se tiene instalada la versión de maven.
 - Incluir las siguientes rutas a la variable de ambiente PATH:<br>
    **%JAVA_HOME$\bin**<br>
	**%M2_HOME%\bin**
 - Java 8 o superior, esto se puede validar con el siguiente comando:<br>
	**java -version**
 - Apache Maven 3.8.8 o superior, esto se puede validar con el siguiente comando:<br>
	**mvn --versiones**<br>
 - Contar con la instación de git 2.27.0 o superior, esto se puede validar mediante el siguiente comando:<br>
    **git --version**

## INSTRUCCIONES DE DESCARGA

Actualmente el proyecto se encuentra compartido en un proyecto de GIT, este se puede descargar mediante los siguientes pasos:
 - Establecer una ruta de instalación a la cual denominaremos *DIRECTORIO_INSTALACION*, por ejemplo **%HOME%\git**
 - Una vez posicionados en el *DIRECTORIO_INSTALACION*, se podrá descargar el proyecto con el siguiente comando de git:<br>
	**git clone https://github.com/SabadellMinsait/Equipo-FRA.git**

## COMPILACION Y DOCUMENTACIÓN

Para la generación de archivos de ejecución, se podrá realizar mediante los siguientes pasos:

 - Posicionarse en el directorio del proyecto, mediante el siguiente comando:<br>
    **cd [DIRECTORIO_INSTALACION]\Equipo-FRA**
  - Compilación e instalación del Proyecto mediante maven:<br>
    **mvn clean install**
  - Generación de documentación<br>
    **mvn javadoc:javadoc**

## INICIALIZACIÓN DE LOS SERVICIOS

Es importante mencionar que cada servcios depende de otro por lo que es necesario inicializar los servicios simultáneamente, para la inicialización simultánea de los 3 servicios se podrá realizar mediante las siguientes instrucciones:

 - Posicionarse en el directorio del proyecto, mediante el siguiente comando:<br>
 	**cd [DIRECTORIO_INSTALACION]\Equipo-FRA**
 - Levantar el servicio de biblioteca en segundo plano con la siguiente instrucción:<br>
    **start java -jar Biblioteca/target/Biblioteca-1.0-SNAPSHOT.jar**
 - Levantar el servicio de materias en segundo plano con la siguiente instrucción:<br>
    **start java -jar Materias/target/Materias-1.0-SNAPSHOT.jar**
 - Levantar el servicio de servicios escolares en segundo plano con la siguiente instrucción:<br>
    **start java -jar ServiciosEscolares/target/ServiciosEscolares-1.0-SNAPSHOT.jar**

## COMANDOS BÁSICOS

Comandos básicos que se podran utilizar:

* mvn –version:      Este comando nos ayuda a conocer la versión actual de Maven que está instalada
* mvn clean:         Limpia el proyecto y elimina todos los archivos generados por la compilación anterior.
* mvn clean install: Este comando maven ayuda a ejecutar un ciclo de vida de compilación limpio e instala la fase de compilación en el ciclo de compilación predeterminado.
* mvn compile:       Compila el código fuente del proyecto.
* mvn test-compile:  Compila el código fuente de prueba.
* mvn test:          Ejecuta pruebas para el proyecto.
* Paquete mvn:       Crea un archivo JAR o WAR para que el proyecto lo convierta en un formato distribuible.
* mvn install:       Implementa el archivo JAR/WAR empaquetado en el repositorio local.
* mvn deployment:    Copia el archivo JAR/WAR empaquetado en el repositorio remoto después de compilar, ejecutar pruebas y compilar el proyecto.
* mvn help:          Este comando imprime el uso de maven y todas las opciones disponibles para que las usemos.
* mvn archetype:     Sirve para crear un nuevo proyecto basado en un Arquetipo