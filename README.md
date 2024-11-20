# Complete Java and Spring Boot course
A repo for files from https://www.udemy.com/course/java-curso-completo

> This project refers to a JDBC DAO module from the course

## Database setup

1. Install Docker or Podman
2. Run
```bash
podman run --name my-postgres -p 5432:5432 -e POSTGRES_PASSWORD=example -d postgres
```
3. Reference the Postgres Java Connector in Project Structure > Libraries (IntelliJ)

## About transactions

https://www.ibm.com/docs/en/cics-ts/5.4?topic=processing-acid-properties-transactions

## DAO (Data Access Object - pattern) Diagram

```mermaid
classDiagram

class ClientDao {
    <<Interface>>
    + insert(obj: Client) void
    + update(obj: Client) void
    + deleteById(id: Integer) void
    + findById(id: Integer) Client
    + findAll() List~Client~
}

class ProductDao {
    <<Interface>>
    + insert(obj: Product) void
    + update(obj: Product) void
    + deleteById(id: Integer) void
    + findById(id: Integer) Product
    + findAll() List~Product~
}

class DaoFactory {
    + createClientDao()$ ClientDao
    + createProductDao()$ ProductDao
}

class ClientDaoJDBC
class ProductDaoJDBC

ClientDaoJDBC ..|> ClientDao
ProductDaoJDBC ..|> ProductDao
DaoFactory ..>  ClientDaoJDBC : creates
DaoFactory ..>  ProductDaoJDBC : creates
```

## Entities

```mermaid
classDiagram

class Seller {
- id: Integer
- name: String
- email: String
- bitrhDate: Date
- baseSalary: Double
}

class Department {
- id: Integer
- name: String
}

Seller "1" --> "1" Department : -department
```
