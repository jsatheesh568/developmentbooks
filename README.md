
 # Development Books Kata - TDD with Spring Boot

This project is a **Test-Driven Development (TDD) Kata** built with **Java 17** and **Spring Boot**. It demonstrates the use of TDD principles to solve a pricing algorithm challenge using a RESTful API, along with integrated **Swagger UI** for testing.

---

## Kata Description

There are **5 software development books** available:

1. Clean Code (Robert Martin, 2008)  
2. The Clean Coder (Robert Martin, 2011)  
3. Clean Architecture (Robert Martin, 2017)  
4. Test Driven Development by Example (Kent Beck, 2003)  
5. Working Effectively with Legacy Code (Michael Feathers, 2004)  

### Pricing Rules

- Each book costs **€50**.
- Discounts apply when **different titles** are bought together:
  - 2 different books = **5% discount**
  - 3 different books = **10% discount**
  - 4 different books = **20% discount**
  - 5 different books = **25% discount**

If books are repeated, they may be grouped in such a way that maximizes discounts.

#### 💡 Example

Basket:
- 2 × Clean Code  
- 2 × The Clean Coder  
- 2 × Clean Architecture  
- 1 × TDD by Example  
- 1 × Working Effectively with Legacy Code  

Best grouping:
- Group 1: 4 books = 20% discount  
- Group 2: 4 books = 20% discount  

Calculation:
(4 × €50) - 20% = €160
(4 × €50) - 20% = €160
Total = €320


---

## Prerequisites

- Java 17
- Maven 3.x
- Spring Boot 3.2.5
- Internet for downloading dependencies

---

## How to Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
