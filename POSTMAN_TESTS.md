[◘ ROLE - FUNCIONALIDADES ◘]

[♦ CREAR ♦]{

[*TIENES QUE LOGARTE CON UN USUARIO ADMIN PARA CREAR UN USER*]

[*USER*]{

*
Método HTTP: POST

URL: http://localhost:9090/api/user

Cuerpo de la solicitud (JSON):

{
    "username": "marc_actualizado",
    "password": "$2a$12$.pfOD/Bly4UyEBn1vVqAjuHlJGa.1RaWKf2JLINd5AFMhzky0PGmO",
    "roles": ["ROLE_ADMIN"]
}
//El usuario es: marc_actualizado
//La contraseña es: contraseña123

Respuesta esperada (201 Created):

{
    "id": 3,
    "username": "marc_actualizado",
    "roles": [
        "ROLE_ADMIN"
    ]
}

Método HTTP: GET por List All
http://localhost:9090/api/user

Método HTTP: GET por id
http://localhost:9090/api/user/1

Método HTTP: GET por name
http://localhost:9090/api/user/name/admin

Método HTTP: PUT para update
http://localhost:9090/api/user/1
{
    "username": "Leo_BETA-TESTER",
    "password": "$2a$12$EOKxnJ2hYOaIg.46pVExIe51rNB7kaLJ7WuFfZWYLsATNjKNToYia",
    "roles": ["ROLE_ADMIN"]
}
// el nuevo nombre es: Leo_BETA-TESTER 
Método HTTP: DELETE por id
http://localhost:9090/api/user/id/3

Método HTTP: DELETE por name
http://localhost:9090/api/user/name/3

}

[*Category*]{

Método HTTP: POST

URL: http://localhost:9090/api/category

Cuerpo de la solicitud (JSON):

{
    "name": "Componentes_PC",
    "description" : "Productos ensamblar o actualizar tu computadora"
}

Respuesta esperada (201 Created):

{
    "id": 52,
    "name": "Componentes_PC_Y_LAPTOPS",
    "description": "Productos ensamblar o actualizar tu computadora o laptop."
}


Método HTTP: GET por List All
http://localhost:9090/api/category

Método HTTP: GET por id
http://localhost:9090/api/category/1

Método HTTP: GET por name
http://localhost:9090/api/category/name/Salud

Método HTTP: PUT para update
http://localhost:9090/api/category/1
{
    "name": "Componentes_PC_Y_LAPTOPS",
    "description" : "Productos ensamblar o actualizar tu computadora o laptop."
}

Método HTTP: DELETE por id
http://localhost:9090/api/category/id/3

Método HTTP: DELETE por name
http://localhost:9090/api/category/name/Componentes_PC_Y_LAPTOPS

}

[*Product*]{

Método HTTP: POST

URL: http://localhost:9090/api/product

Cuerpo de la solicitud (JSON):

{
    "name": "Smartphone X Pro",
    "description" : "Smartphone de alta gama con cámara de 64 MP y pantalla OLED.",
    "price": 899.00,
    "stock": 50,
    "minStock": 5,
    "categoryId": 1
}

Respuesta esperada (201 Created):

{
    "id": 51,
    "name": "Smartphone X Pro",
    "description" : "Smartphone de alta gama con cámara de 64 MP y pantalla OLED.",
    "price": 899.00,
    "stock": 50,
    "minStock": 5,
    "categoryId": 1
}


Método HTTP: GET por List All
http://localhost:9090/api/product

Método HTTP: GET por id
http://localhost:9090/api/product/51

Método HTTP: GET por name
http://localhost:9090/api/product/name/Smartphone X Pro

Método HTTP: PUT para update
http://localhost:9090/api/product/51
{
    "name": "Smartphone XX Pro",
    "description" : "Smartphone de HYPE gama con cámara de 500 MP y pantalla 4k 120fps.",
    "price": 5000.00,
    "stock": 100,
    "minStock": 20,
    "categoryId": 1
}

Método HTTP: DELETE por id
http://localhost:9090/api/product/id/51

Método HTTP: DELETE por name
http://localhost:9090/api/product/name/Smartphone XX Pro
}
}


[► POSTMAN ◄]
{URL} http://localhost:9090/api/roles
[◘ PRUEBAS POSTMAN ◘]

[◘ ROLE ◘]
[♦ RESUMEN ♦]
GET /api/roles: Obtener todos los roles.

POST /api/roles: Crear un nuevo rol.

GET /api/roles/{name}: Obtener un rol por nombre.

PUT /api/roles/{name}: Actualizar un rol por nombre.

DELETE /api/roles/{name}: Eliminar un rol por nombre.

DELETE /api/roles/id/{id}: Eliminar un rol por ID.


[◘ ROLE - FUNCIONALIDADES ◘]

[♦ CREAR ♦]

Método HTTP: POST

URL: http://localhost:9090/api/roles

Cuerpo de la solicitud (JSON):

{
  "name": "ADMIN"
}
Respuesta esperada (201 Created):

{
  "role_id": "abc123",
  "name": "ADMIN"
}

[♦ LISTAR ♦]

Método: GET

URL: http://localhost:9090/api/roles

Respuesta esperada (200 OK):

[
  {
    "role_id": "abc123",
    "name": "ADMIN"
  },
  {
    "role_id": "def456",
    "name": "USER"
  }
]

[♦ LISTAR POR ROL♦]

Método: GET

URL: http://localhost:9090/api/roles/{name}

Respuesta esperada (200 OK):


[◘ PRODUCT ◘]
{URL} http://localhost:9090/api/product

[♦ RESUMEN ♦]
GET    /api/product             : Listar todos los productos
GET    /api/product/{id}        : Obtener un producto por ID
GET    /api/product/name/{name} : Obtener productos por nombre
POST   /api/product             : Crear un nuevo producto
PUT    /api/product/{id}        : Actualizar un producto por ID
DELETE /api/product/id/{id}     : Eliminar un producto por ID
DELETE /api/product/name/{name} : Eliminar productos por nombre

[◘ PRODUCT - FUNCIONALIDADES ◘]

[♦ CREAR ♦]
Método HTTP: POST
URL: http://localhost:9090/api/product

Cuerpo de la solicitud (JSON):

{
  "name": "Camisa Casual",
  "description": "Camisa de algodón manga larga",
  "price": 49.99,
  "stock": 100,
  "minStock": 10,
  "categoryId": 3
}

Respuesta esperada (201 Created):

{
  "id": 15,
  "name": "Camisa Casual",
  "description": "Camisa de algodón manga larga",
  "price": 49.99,
  "stock": 100,
  "minStock": 10,
  "categoryId": 3,
  "categoryName": "Ropa"
}

[♦ LISTAR ♦]
Método: GET
URL: http://localhost:9090/api/product
Respuesta esperada (200 OK):

[
  {
    "id": 15,
    "name": "Camisa Casual",
    "description": "Camisa de algodón manga larga",
    "price": 49.99,
    "stock": 100,
    "minStock": 10,
    "categoryId": 3,
    "categoryName": "Ropa"
  },
  {
    "id": 16,
    "name": "Pantalón Jeans",
    "description": "Jeans azul oscuro",
    "price": 79.99,
    "stock": 50,
    "minStock": 5,
    "categoryId": 3,
    "categoryName": "Ropa"
  }
]


[♦ OBTENER POR ID ♦]
Método: GET
URL: http://localhost:9090/api/product/15
Respuesta esperada (200 OK):

{
  "id": 15,
  "name": "Camisa Casual",
  "description": "Camisa de algodón manga larga",
  "price": 49.99,
  "stock": 100,
  "minStock": 10,
  "categoryId": 3,
  "categoryName": "Ropa"
}

[♦ OBTENER POR NOMBRE ♦]
Método: GET
URL: http://localhost:9090/api/product/name/Camisa%20Casual
Respuesta esperada (200 OK):

[
  {
    "id": 15,
    "name": "Camisa Casual",
    "description": "Camisa de algodón manga larga",
    "price": 49.99,
    "stock": 100,
    "minStock": 10,
    "categoryId": 3,
    "categoryName": "Ropa"
  }
]


[♦ ACTUALIZAR ♦]
Método HTTP: PUT
URL: http://localhost:9090/api/product/15

Cuerpo de la solicitud (JSON):

{
  "name": "Camisa Formal",
  "description": "Camisa de algodón manga larga, corte slim",
  "price": 59.99,
  "stock": 80,
  "minStock": 10,
  "categoryId": 3
}

Respuesta esperada (200 OK):
{
  "id": 15,
  "name": "Camisa Formal",
  "description": "Camisa de algodón manga larga, corte slim",
  "price": 59.99,
  "stock": 80,
  "minStock": 10,
  "categoryId": 3,
  "categoryName": "Ropa"
}

[♦ ELIMINAR POR ID ♦]
Método: DELETE
URL: http://localhost:9090/api/product/id/15
Respuesta esperada (204 No Content):

[♦ ELIMINAR POR NOMBRE ♦]
Método: DELETE
URL: http://localhost:9090/api/product/name/Camisa%20Formal
Respuesta esperada (204 No Content):




