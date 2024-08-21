# Proyecto de Identificación de Alienígenas

## Descripción

Este proyecto implementa un sistema para identificar si un ser es humano o alienígena basándose en secuencias de ADN. Utiliza Spring Boot y Maven para la implementación de la lógica y la API REST. La solución incluye:

1. **Detección de Alienígenas**: Un algoritmo para determinar si un ser es alienígena a partir de una matriz de ADN.
2. **API REST**: Permite enviar secuencias de ADN y recibir la clasificación.
3. **Persistencia de Datos**: Guarda los registros de ADN verificados en una base de datos.
4. **Estadísticas**: Proporciona estadísticas sobre los resultados de las verificaciones.
5. **Cobertura de Código**: Verifica el porcentaje de cobertura de pruebas con Jacoco.

## Estructura del Proyecto
├── main
│ ├── java
│ │ ├── com/amaris/prueba/controllers/AlienController.java
│ │ ├── com/amaris/prueba/models/DNARequest.java
│ │ ├── com/amaris/prueba/models/DNARecord.java
│ │ ├── com/amaris/prueba/repositorys/DNARecordRepository.java
│ │ └── com/amaris/prueba/services/DNAAnalyzer.java
│ └── resources
│ ├── application.properties
│ └── schema.sql
└── test
├── java
│ ├── com/amaris/prueba/controllers/AlienControllerTest.java
│ └── com/amaris/prueba/services/DNAAnalyzerTest.java
└── resources


## Requisitos

- Java 17
- Maven

## Configuración del Proyecto

### Dependencias

El proyecto utiliza las siguientes dependencias principales en `pom.xml`:

- **Spring Boot**: Para crear la aplicación web.
- **Jacoco**: Para la cobertura de código.
- **H2**: Base de datos en memoria para pruebas.

### Configuración de Jacoco

Agrega la configuración de Jacoco en el archivo `pom.xml` para generar el reporte de cobertura:

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <rules>
            <rule>
                <element>CLASS</element>
                <includes>
                    <include>*</include>
                </includes>
                <counter>
                    <type>LINE</type>
                    <minimum>0.8</minimum> <!-- 80% de cobertura de línea -->
                </counter>
                <counter>
                    <type>BRANCH</type>
                    <minimum>0.8</minimum> <!-- 80% de cobertura de rama -->
                </counter>
            </rule>
        </rules>
    </configuration>
</plugin>


Configuración de Base de Datos
La base de datos H2 se configura en application.properties:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


Ejecución del Proyecto
Compilar y ejecutar:
mvn clean install
mvn spring-boot:run


Enviar una secuencia de ADN a la API:

Usa curl para enviar una solicitud POST al endpoint /alien:

curl -X POST http://localhost:8080/alien -H "Content-Type: application/json" -d '{
    "dna": ["HTDCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"]
}'


Obtener estadísticas:

Accede al endpoint /stats para ver las estadísticas de las verificaciones de ADN:
curl http://localhost:8080/stats


Pruebas
Ejecutar pruebas unitarias:
mvn test


Verificar la cobertura de código:

Después de ejecutar las pruebas, el reporte de cobertura se encontrará en target/site/jacoco/index.html. Abre este archivo en un navegador para revisar la cobertura de código.
