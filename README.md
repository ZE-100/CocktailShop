# CocktailShop

A shop for, you guessed it, cocktails...

## What is CocktailShop

## Why is CocktailShop

## Where is CocktailShop

## Requirements

### Data storage

Dynamic database switch

- Filesystem
  - LOG-files
- PostgreSQL
  - Userdata storage: User, Roles
- MariaDB
  - Cocktail-DB

### Front- and Backend

- MVC everywhere
- Dynamically generated
  - Thymeleaf & Spring-Boot
- Validation
  - SQL-Injection
  - Invalid entries
  - Session-handling
- Shop-system
  - Multiple articles can be (de)selected
  - Hashed login + session handling
  - RBAC-system
    - Admins manage users
    - Admins manage cocktails
  - Form validation
  - Sorting cocktails possible (ABC, price)
  - HTML + CSS
  - Send mail for orders
  - Pay with twint