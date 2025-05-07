# 🌟 Cómo Ejecutar el Sistema

## 📋 Requisitos

### 🛠 Herramientas
- **IDE**: 💻 [IntelliJ IDEA](https://www.jetbrains.com/idea/)
  
### ⚙️ Tecnologías
- **Java** ☕
- **Spring Boot** 🚀
- **Spring Security** 🔐
- **Spring Data JPA** 🗄
- **Spring Web** 🌐
- **Lombok** ✂️
- **MySQL Driver** 🛢
- **JJWT** 🔑

### 💻 Motor de Base de Datos
- **MySQL Workbench** 💼

### 🌍 Front-End
- **Node.js** 🌱
- **Angular v19** ⚡

### 🔧 Solución para problemas de puertos (Front-End)
Si tienes problemas con puertos, utiliza este comando para encontrar y liberar el puerto bloqueado:

```bash
netstat -aon | findstr :<PUERTO>

TCP    0.0.0.0:9090    0.0.0.0:0    LISTENING    1234 <-- Copiar

taskkill /PID 1234 /F
```
---

## ⚡ Backend

### 🚀 Pasos para Ejecutar el Backend

1. **Abrir el Proyecto**:  
   - Abre el proyecto en **IntelliJ IDEA**.

2. **Ir a la Clase Principal**:  
   - Navega a la clase `BackendApplication`.

3. **Ejecutar el Proyecto**:  
   - Haz clic en el botón **Run** para iniciar el backend.

4. **Pruebas con Postman**:  
   - Abre **Postman** y realiza las pruebas de los endpoints.

---

## 📅 Estructura del Backend

```plaintext
src/main/java/com/lozano
├── application
│   ├── dto
│   ├── mapper
│   └── service
├── domain
│   ├── entity
│   └── repository
├── security
│   ├── filter
│   └── util
└── web.controller
```

---

## 🧩 Flujo del Backend

1. **Entity**  
2. **DTO**  
3. **Mapper**  
4. **Repository**  
5. **Service** (IService, ServiceImpl)  
6. **Controller**

---

## 📊 Análisis del Proyecto

### 📂 **Entidades** (Entities)

- **User** 💻  
- **Role** 🔑  
- **Product** 📦  
- **Category** 🏷  
- **Sale** 💰  
- **SaleDetail** 📄  
- **Coupon** 🎟

### 📤 **DTO** (Data Transfer Objects)

- **User**: Request / Response  
- **Role**: Request / Response  
- **Product**: Request / Response  
- **Category**: Request / Response  
- **Sale**: Request / Response  
- **SaleDetail**: Request / Response  
- **Coupon**: Request / Response

### 🛠 **Mappers**

- **UserMapper**  
- **RoleMapper**  
- **ProductMapper**  
- **CategoryMapper**  
- **SaleMapper**  
- **SaleDetailMapper**  
- **CouponMapper**

### ⚙️ **Servicios** (Services)

- **IUserService** – UserServiceImpl  
- **IRoleService** – RoleServiceImpl  
- **ICategoryService** – CategoryServiceImpl  
- **IProductService** – ProductServiceImpl  
- **ISaleService** – SaleServiceImpl  
- **ISaleDetailService** – SaleDetailServiceImpl  
- **IAuthService** – AuthServiceImpl  
- **ICouponService** – CouponServiceImpl

**Métodos Comunes**:
- `create` ✨
- `update` 🔄
- `findByName` 🔍
- `findById` 📋
- `findAll` 📑
- `deleteByName` 🗑️
- `deleteById` 🗑️
- Otros métodos de búsqueda 🔍

### 🎮 **Controladores** (Controllers)

- **UserController**  
- **RoleController**  
- **ProductController**  
- **CategoryController**  
- **SaleController**  
- **SaleDetailController**  
- **CouponController**  

---

