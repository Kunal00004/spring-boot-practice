# Day 2: Circular Dependency in Spring

## 📌 Topic Overview
This module contains practice code for understanding **Circular Dependency** issues in Spring and how to resolve them.
(Based on Coder Army's Spring Boot Full Course #6)

---

## 🧠 Key Concepts & Notes

### 1. Circular Dependency
* **What is it?** A situation where Class A depends on Class B, and Class B depends on Class A (e.g., `OrderService` needs `PaymentService` to be created, but `PaymentService` needs `OrderService` to be created).
* **The Problem:** Spring IoC container gets stuck in an infinite loop trying to instantiate the beans when using **Constructor Injection**. It throws a `BeanCurrentlyInCreationException`.
* **How to fix it:**
    * **Bad Practice but works:** Use *Field Injection* or *Setter Injection* (because objects are created first, and dependencies are injected later).
    * **Another trick:** Use `@Lazy` annotation on one of the injected dependencies. This injects a *Proxy Object* temporarily, breaking the cycle until the actual object is needed.
    * **Best Practice:** Refactor your code! Circular dependency is a sign of bad code design (Violating the Single Responsibility Principle). The logic should flow in a linear direction. (e.g., `OrderService` -> `PaymentService`, not both ways).

---

## 📂 Practical Takeaways for Coding
1. **Avoid Circular Dependencies.** Re-think your architecture if two services tightly call each other.