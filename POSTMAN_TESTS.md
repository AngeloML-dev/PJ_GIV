# 🌐 **Documentación de Pruebas API** 🧪

---

## 📑 **Índice**

1. [👤 USER (`/api/user`)](#-user-apiuser-)
   - [♦ Crear Usuario](#-crear-usuario)
   - [♦ Consultar Usuarios](#-consultar-usuarios)
   - [♦ Actualizar Usuario](#-actualizar-usuario)
   - [♦ Eliminar Usuario](#-eliminar-usuario)
2. [🛡️ ROLE (`/api/role`)](#%EF%B8%8F-role-apirole-)
   - [♦ Crear Rol](#-crear-rol)
   - [♦ Consultar Roles](#-consultar-roles)
   - [♦ Actualizar Rol](#-actualizar-rol)
   - [♦ Eliminar Rol](#-eliminar-rol)
3. [🌟 CATEGORY (`/api/category`)](#-category-apicategory-)
   - [♦ Crear Categoría](#-crear-categoría)
   - [♦ Consultar Categorías](#-consultar-categorías)
   - [♦ Actualizar Categoría](#-actualizar-categoría)
   - [♦ Eliminar Categoría](#-eliminar-categoría)
4. [🛒 PRODUCT (`/api/product`)](#-product-apiproduct-)
   - [♦ Crear Producto](#-crear-producto)
   - [♦ Consultar Productos](#-consultar-productos)
   - [♦ Actualizar Producto](#-actualizar-producto)
   - [♦ Eliminar Producto](#-eliminar-producto)
5. [💰 SALE (`/api/sale`)](#-sale-apisale-)
   - [♦ Crear Venta](#-crear-venta)
   - [♦ Consultar Ventas](#-consultar-ventas)
   - [♦ Actualizar Venta](#-actualizar-venta)
   - [♦ Eliminar Venta](#-eliminar-venta)
6. [📄 SALE DETAIL (`/api/saledetail`)](#-sale-detail-apisaledetail-)
   - [♦ Crear Detalle de Venta](#-crear-detalle-de-venta)
   - [♦ Consultar Detalles de Venta](#-consultar-detalles-de-venta)
   - [♦ Actualizar Detalle de Venta](#-actualizar-detalle-de-venta)
   - [♦ Eliminar Detalle de Venta](#-eliminar-detalle-de-venta)
7. [🎟 COUPON (`/api/coupon`)](#-coupon-apicoupon-)
   - [♦ Crear Cupón](#-crear-cupón)
   - [♦ Consultar Cupones](#-consultar-cupones)
   - [♦ Actualizar Cupón](#-actualizar-cupón)
   - [♦ Eliminar Cupón](#-eliminar-cupón)

---

## 👤 **USER** (`/api/user`) 👤

### 🛠️ **[♦ CREAR ♦]**
#### 🔐 **Crear Usuario** *(Solo ADMIN logueado)*

- 📡 **Método HTTP:** `POST`  
- 🌐 **Endpoint:** `http://localhost:9090/api/user`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "username": "marc_actualizado",
  "password": "$2a$12$.pfOD/Bly4UyEBn1vVqAjuHlJGa.1RaWKf2JLINd5AFMhzky0PGmO",
  "roles": ["ROLE_ADMIN"]
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 3,
  "username": "marc_actualizado",
  "roles": ["ROLE_ADMIN"]
}
```

---

### 📄 **[♦ CONSULTAR ♦]**

#### 🔍 **Listar Todos los Usuarios**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/user`

#### 🔍 **Obtener Usuario por ID**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/user/{id}`  
  *(Ejemplo: `http://localhost:9090/api/user/1`)*

#### 🔍 **Obtener Usuario por Nombre**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/user/name/{username}`  
  *(Ejemplo: `http://localhost:9090/api/user/name/admin`)*

---

### ✏️ **[♦ ACTUALIZAR ♦]**

#### ♻️ **Actualizar un Usuario**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/user/{id}`  
  *(Ejemplo: `http://localhost:9090/api/user/1`)*  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "username": "Leo_BETA-TESTER",
  "password": "$2a$12$EOKxnJ2hYOaIg.46pVExIe51rNB7kaLJ7WuFfZWYLsATNjKNToYia",
  "roles": ["ROLE_ADMIN"]
}
```
- 📌 **Nota:** El nuevo nombre de usuario será: `Leo_BETA-TESTER`

---

### ❌ **[♦ ELIMINAR ♦]**

#### 🗑️ **Eliminar Usuario por ID**
- 🧨 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/user/id/{id}`  
  *(Ejemplo: `http://localhost:9090/api/user/id/3`)*

#### 🔥 **Eliminar Usuario por Nombre**
- 🧹 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/user/name/{username}`  
  *(Ejemplo: `http://localhost:9090/api/user/name/Leo_BETA-TESTER`)*

---

## 🛡️ **ROLE** (`/api/role`) 🛡️

### 🛠️ **[♦ CREAR ♦]**
#### ✨ **Crear un nuevo rol**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/role`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "ROLE_MANAGER"
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 4,
  "name": "ROLE_MANAGER"
}
```

---

### 📄 **[♦ CONSULTAR ♦]**
#### 📑 **Listar todos los roles**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/role`

#### 🔍 **Obtener un rol por ID**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/role/4`

#### 🔍 **Obtener un rol por nombre**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/role/name/ROLE_MANAGER`

---

### ✏️ **[♦ ACTUALIZAR ♦]**
#### ♻️ **Actualizar un rol**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/role/4`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "ROLE_SUPERVISOR"
}
```

---

### ❌ **[♦ ELIMINAR ♦]**
#### 🗑️ **Eliminar un rol por ID**
- 📡 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/role/id/4`

#### 🗑️ **Eliminar un rol por nombre**
- 📡 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/role/name/ROLE_SUPERVISOR`

---

## 🌟 **CATEGORY** (`/api/category`) 🌟

### 🛠️ **[♦ CREAR ♦]**
#### ✨ **Crear una nueva categoría**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/category`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "Componentes_PC",
  "description": "Productos para ensamblar o actualizar tu computadora"
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 52,
  "name": "Componentes_PC_Y_LAPTOPS",
  "description": "Productos para ensamblar o actualizar tu computadora o laptop."
}
```

---

### 📄 **[♦ CONSULTAR ♦]**
#### 📑 **Listar todas las categorías**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/category`

#### 🔍 **Obtener una categoría por ID**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/category/{id}`  
  *(Ejemplo: `http://localhost:9090/api/category/1`)*

#### 🔍 **Obtener una categoría por Nombre**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/category/name/{name}`  
  *(Ejemplo: `http://localhost:9090/api/category/name/Salud`)*

---

### ✏️ **[♦ ACTUALIZAR ♦]**
#### ♻️ **Actualizar Categoría**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/category/{id}`  
  *(Ejemplo: `http://localhost:9090/api/category/1`)*  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "Componentes_PC_Y_LAPTOPS",
  "description": "Productos para ensamblar o actualizar tu computadora o laptop."
}
```

---

### ❌ **[♦ ELIMINAR ♦]**
#### 🗑️ **Eliminar Categoría por ID**
- 🔗 **URL:** `http://localhost:9090/api/category/id/{id}`  
  *(Ejemplo: `http://localhost:9090/api/category/id/3`)*  

#### 🔥 **Eliminar Categoría por Nombre**
- 🔗 **URL:** `http://localhost:9090/api/category/name/{name}`  
  *(Ejemplo: `http://localhost:9090/api/category/name/Componentes_PC_Y_LAPTOPS`)*

---

## 🛒 **PRODUCT** (`/api/product`) 🛒

### 🛠️ **[♦ CREAR ♦]** - **Crear Producto**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/product`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "Smartphone X Pro",
  "description": "Smartphone de alta gama con cámara de 64 MP y pantalla OLED.",
  "price": 899.00,
  "stock": 50,
  "minStock": 5,
  "categoryId": 1
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 51,
  "name": "Smartphone X Pro",
  "description": "Smartphone de alta gama con cámara de 64 MP y pantalla OLED.",
  "price": 899.00,
  "stock": 50,
  "minStock": 5,
  "categoryId": 1
}
```

#### 📄 **Listar todos los productos**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/product`

#### 🔍 **Obtener Producto por ID**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/product/{id}`  
  *(Ejemplo: `http://localhost:9090/api/product/51`)*

#### 🔍 **Obtener Producto por Nombre**
- 🌐 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/product/name/{name}`  
  *(Ejemplo: `http://localhost:9090/api/product/name/Smartphone X Pro`)*

#### ✏️ **Actualizar Producto**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/product/{id}`  
  *(Ejemplo: `http://localhost:9090/api/product/51`)*  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "name": "Smartphone XX Pro",
  "description": "Smartphone de gama HYPE con cámara de 500 MP y pantalla 4k 120fps.",
  "price": 5000.00,
  "stock": 100,
  "minStock": 20,
  "categoryId": 1
}
```

#### ❌ **Eliminar Producto**
- 🗑️ **Por ID:**  
  🔗 **URL:** `http://localhost:9090/api/product/id/{id}`  
  *(Ejemplo: `http://localhost:9090/api/product/id/51`)*  
- 🔥 **Por Nombre:**  
  🔗 **URL:** `http://localhost:9090/api/product/name/{name}`  
  *(Ejemplo: `http://localhost:9090/api/product/name/Smartphone XX Pro`)*

---

## 💰 **SALE** (`/api/sale`) 💰

### ✨ **[♦ CREAR ♦]** - **Crear una nueva venta**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/sale`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "clientName": "Carlos Romero",
  "total": 249.90,
  "paymentType": "EFECTIVO"
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 101,
  "clientName": "Carlos Romero",
  "total": 249.90,
  "paymentType": "EFECTIVO",
  "createdAt": "2025-05-06T14:32:10"
}
```

---

### 📑 **[♦ LISTAR ♦]** - **Listar todas las ventas**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/sale`

---

### 🔍 **[♦ OBTENER POR ID ♦]** - **Obtener una venta por ID**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/sale/101`

---

### ✏️ **[♦ ACTUALIZAR ♦]** - **Actualizar una venta**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/sale/101`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "clientName": "Carlos R. G.",
  "total": 275.00,
  "paymentType": "TARJETA"
}
```

---

### 🗑️ **[♦ ELIMINAR ♦]** - **Eliminar una venta por ID**
- 📡 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/sale/id/101`

---

## 📄 **SALE DETAIL** (`/api/saledetail`) 📄

### ✨ **[♦ CREAR ♦]** - **Crear un detalle de venta**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/saledetail`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "saleId": 101,
  "productId": 10,
  "quantity": 2,
  "unitPrice": 124.95
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 501,
  "saleId": 101,
  "productId": 10,
  "quantity": 2,
  "unitPrice": 124.95,
  "subtotal": 249.90
}
```

---

### 📑 **[♦ LISTAR ♦]** - **Listar todos los detalles de venta**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/saledetail`

---

### 🔍 **[♦ OBTENER POR ID ♦]** - **Obtener detalle por ID**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/saledetail/501`

---

### ✏️ **[♦ ACTUALIZAR ♦]** - **Actualizar detalle de venta**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/saledetail/501`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "quantity": 3,
  "unitPrice": 119.90
}
```

---

### 🗑️ **[♦ ELIMINAR ♦]** - **Eliminar detalle de venta por ID**
- 📡 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/saledetail/id/501`

---

## 🎟 **COUPON** (`/api/coupon`) 🎟

### ✨ **[♦ CREAR ♦]** - **Crear un cupón**
- 📡 **Método HTTP:** `POST`  
- 🔗 **URL:** `http://localhost:9090/api/coupon`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "code": "PROMO25",
  "discount": 25.0,
  "expirationDate": "2025-12-31"
}
```
- ✅ **Respuesta esperada:** `201 Created`
```json
{
  "id": 12,
  "code": "PROMO25",
  "discount": 25.0,
  "expirationDate": "2025-12-31"
}
```

---

### 📑 **[♦ LISTAR ♦]** - **Listar todos los cupones**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/coupon`

---

### 🔍 **[♦ OBTENER POR ID ♦]** - **Obtener cupón por ID**
- 📡 **Método HTTP:** `GET`  
- 🔗 **URL:** `http://localhost:9090/api/coupon/12`

---

### ✏️ **[♦ ACTUALIZAR ♦]** - **Actualizar un cupón**
- 📡 **Método HTTP:** `PUT`  
- 🔗 **URL:** `http://localhost:9090/api/coupon/12`  
- 📥 **Cuerpo de la solicitud (JSON):**
```json
{
  "discount": 30.0,
  "expirationDate": "2026-01-15"
}
```

---

### 🗑️ **[♦ ELIMINAR ♦]** - **Eliminar cupón por ID**
- 📡 **Método HTTP:** `DELETE`  
- 🔗 **URL:** `http://localhost:9090/api/coupon/id/12`

---


