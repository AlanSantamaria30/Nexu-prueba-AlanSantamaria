# Nexu-prueba-AlanSantamaria
Prueba para la empresa NEXU

PROYECTO DE PRUEBA ALAN SANTAMARIA - NEXUS - MARCAS MODELOS.

1.- Se requiere ejecutar el archivo Script BD que se encuentra en la carpeta BD del repositorio.

2.- se requiere tener instalado java 17

3.- descargar el proyecto Nexus y ejecutar el comando mvn spring-boot:run con esto debe iniciar el proyecto sin ningun tema.


4.- ejecutar los scripts ( para ejecutar este paso es requerido que se levante de manera correcta el proyecto)
    -table-brands.sql
    -table-models.sql
 de la carpeta BD.


 A Continuacion se mandan las URL que se usan para validar la informacion:
GET
 -localhost:8080/brandsController

POST
 -localhost:8080/brandsController/brands
      - JSON
        {
            "name": "KIA",
            "averagePrice": 450500
        }

POST
 -localhost:8080/brandsController/models
      - JSON
        {
            "name": "Rio sedan",
            "averagePrice": 15000,
            "brand": {
                "id": 6
            }
        }

 GET
 - localhost:8080/brandsController/brands/1/models

 PUT
 - localhost:8080/brandsController/models/4?newAveragePrice=50000

 GET 
 - localhost:8080/brandsController/modelsRanges?menor=400000&mayor=460000




