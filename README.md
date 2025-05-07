# COMO EJECUTAR EL SISTEMA

## REQUISITOS
[Herramientas]

[IDE]: InteliJ

[Tecnologias]:
• Java
• Spring Boot
• Spring Security
• Spring Data JPA
• Spring Web
• Lombok
• MySQL Driver
• JJWT 
[Motor de Base de Datos]:
MySQL Workbench

[Front-end]:
Node.js
Angular v19

[Front-end - Solucion para problemas de lso puertos]:
netstat -aon | findstr :<PUERTO>

TCP    0.0.0.0:9090    0.0.0.0:0    LISTENING    1234 <-- Copiar

taskkill /PID 1234 /F

## BACKEND
- Abrir Proyecto
- Ir a BackendApplication
- Ir a Run

Abrir PostMan para pruebas

## 📅 ESTRUCTURA BACKEND
-------------
src/main/java
└── com/lozano
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
	
[FLUJO DEL BACKEND]
1. entity
2. dto
3. mapper
4. repository
5. service (IService , ServiceImpl)
6. controller

[Analisis del Proyecto]

[-- ENTIDADES --]

User    💻
Role     💻  
Product 💻
Category 💻
Sale       💻
SaleDetail 💻
Coupon 💻

[-- DTO --]

User - Request / Response
Role - Request / Response
Product - Request / Response
Category - Request / Response
Sale - Request / Response
SaleDetail - Request / Response
Coupon - Request / Response

[-- MAPPER --]

UserMapper
RoleMapper
ProductMapper
CategoryMapper
SaleMapper
SaleDetailMapper
CouponMapper

[-- SERVICE --]

IUserService – UserServiceImpl
IRoleService – RoleServiceImpl
ICategoryService – CategoryServiceImpl
IProductService – ProductServiceImpl
ISaleService – SaleServiceImpl
ISaleDetailService – SaleDetailServiceImpl
IAuthService – AuthServiceImpl
ICouponService - CouponServiceImpl

Methods{
create
update
findByName
findById
findAll
deleteByName
deleteById
otros metodos de busqueda
}

[-- CONTROLLER --]
UserController
RoleController
ProductController
CategoryController
SaleController
SaleDetailController
CouponController