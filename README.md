# CocktailShop

A shop for, you guessed it, cocktails...

## What is CocktailShop

## Why is CocktailShop

## Where is CocktailShop

## Requirements

### Data storage

Dynamic database switch

- [X] Filesystem
  - [X] LOG-files
- [O] PostgreSQL
  - [O] Userdata storage: User, Roles
- [X] MariaDB
  - [O] Cocktail-DB

### Front- and Backend

- [O] MVC everywhere
- [O] Dynamically generated
  - [O] Thymeleaf & Spring-Boot
- [O] Validation
  - [X] SQL-Injection
  - [X] Invalid entries
  - [O] Session-handling
- [O] Shop-system
  - [O] Multiple articles can be (de)selected
  - [X] Hashed login + session handling
  - [X] RBAC-system
    - [O] Admins manage users
    - [O] Admins manage cocktails
  - [O] Form validation
  - [O] Sorting cocktails possible (ABC, price)
  - [O] HTML + CSS
  - [O] Send mail for orders
  - [O] Pay with twint