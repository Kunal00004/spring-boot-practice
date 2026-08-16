# Day 1: Loose Coupling, Dependency Injection & Spring IoC Container

## 📌 Topic Overview: Loose Coupling & Dependency Injection
(Based on Coder Army's Spring Boot Full Course #4)

### 1. Tight Coupling vs Loose Coupling
* **Tight Coupling:** When a class (e.g., `OrderService`) creates an object of its dependency (e.g., `new EmailService()`) directly inside itself.
    * *Problem:* It violates the Single Responsibility Principle and Open-Closed Principle. To change the notification type (e.g., to `SmsService`), you must modify the `OrderService` code.
* **Loose Coupling:** When a class interacts with its dependencies through an **Interface** rather than a concrete class, and does not create the dependency itself.
    * *Solution:* Code to interfaces (e.g., `NotificationService` interface implemented by `EmailService`, `SmsService`, `PopupService`).

### 2. Dependency Injection (DI)
* **What is it?** A design pattern where a class receives its dependencies from an external source rather than creating them itself.
* **Famous Principle:** *"Don't create your own dependency, get your dependency."* Or *"A class should ask what it needs and not build everything itself."*
* **Benefits:**
    * Makes code loosely coupled and easily modifiable.
    * Makes **Unit Testing** incredibly easy (e.g., injecting a `FakeEmailService` to test `OrderService` without sending real emails).
* **Types discussed:**
    * Constructor Injection
    * Setter Injection
    * Field Injection (Discussed more in Spring contexts)

### 3. Inversion of Control (IoC)
* **What is it?** IoC is a principle (or idea) where the control flow of a program is inverted.
* **Example:** Instead of `OrderService` controlling the creation of `EmailService` (control inside the class), an external driver (like `main` method or Spring container) creates `EmailService` and passes it to `OrderService` (control is externalized/inverted).
* **Relationship:** IoC is the principle/idea, and Dependency Injection is the technique used to achieve IoC.

### 4. Role of Spring IoC Container
* In plain Java, the `main` method handles wiring dependencies (creating objects and passing them around), which becomes chaotic in large projects.
* **Spring IoC Container** takes over this job. It:
    1. Creates the objects (called **Beans**).
    2. Manages the objects' life cycles.
    3. Connects (wires) the objects together automatically.
* *Note:* Every Spring Bean is an object, but not every Java object is a Spring Bean. Only objects managed by the Spring IoC Container are Beans.

---

## 📌 Topic Overview: Spring IoC Container & Beans
(Based on Coder Army's Spring Boot Full Course #5)

### 1. What is IoC Container?
* **IoC (Inversion of Control) Container** is the core of the Spring framework.
* Instead of the developer creating and managing objects manually using the `new` keyword, the **Spring Framework** takes control.
* It handles creating objects, wiring them together (Dependency Injection), and managing their complete life cycle.
* In Spring, the IoC Container is represented by the `ApplicationContext` interface (which extends the older `BeanFactory`).

### 2. What is a "Bean"?
* Any object that is created, assembled, and managed by the Spring IoC Container is called a **Spring Bean**.
* *Note:* Every Bean is an object, but not every object is a Bean. Only the objects handed over to Spring are Beans.

### 3. Essential Spring Annotations
* **`@Configuration`**: Applied to a class (like `AppConfig.java`) to tell Spring that this class contains configuration rules and Bean definitions.
* **`@ComponentScan("package.name")`**: Tells the Spring IoC container which packages to scan for components to register as Beans automatically.
* **`@Component`**: Applied directly to a class (like `OrderService` or `PaymentService`). It acts as a marker telling Spring: *"Hey, please create an object of this class and manage it as a Bean."*
* **`@Autowired`**: Used to automatically inject dependencies. If `OrderService` needs `PaymentService`, `@Autowired` links them together.

### 4. Types of Dependency Injection
1. **Constructor Injection (Highly Recommended):**
    * Dependency is passed via the constructor.
    * *Benefits:* Objects are fully initialized immediately, allows using `final` variables, and makes Unit Testing much easier.
2. **Setter Injection:** Dependency is passed through setter methods after object creation.
3. **Field Injection (Not Recommended):** Placing `@Autowired` directly on the variable field. It makes the code tightly coupled and extremely hard to test without the Spring container.

### 5. Resolving Multiple Bean Conflicts (`@Primary` & `@Qualifier`)
If you have an Interface (e.g., `PaymentService`) with multiple implementations (e.g., `UpiPayment` and `CardPayment`), Spring gets confused about which one to inject. We solve this by:
* **`@Primary`**: Giving priority to one specific class. If both exist, the primary one is always chosen.
* **`@Qualifier("beanName")`**: Explicitly specifying which bean to use at the injection point.

### 6. The `@Bean` Annotation
* Applied to **methods** inside a `@Configuration` class.
* **Why use it instead of `@Component`?**
    * You cannot use `@Component` on classes from 3rd-party JARs (external libraries) because they are read-only (`.class` files).
    * You cannot use `@Component` if a class requires complex initialization logic (like a constructor expecting specific `String name, int age` values).
    * In these cases, you manually write a method returning the object, and mark it with `@Bean` so Spring takes over its management.

---

## 📂 Code Flow Example
1. `AppConfig.class` is loaded into `AnnotationConfigApplicationContext`.
2. Spring scans for `@Component` or `@Bean`.
3. It stores bean definitions (metadata using Java Reflection APIs).
4. Spring creates the objects (Beans) in its container.
5. Spring handles the wiring (Dependency Injection) via `@Autowired`.
6. We use `context.getBean(OrderService.class)` to fetch the ready-to-use object.