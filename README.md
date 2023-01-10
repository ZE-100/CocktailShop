# CocktailShop

## Setup

### Run the docker containers

Find the docker-compose.yml file located in ./.docker/. After doing so, start all
images by entering this command into a terminal`docker-compose up -d`.

To check if all the four images are up and running, enter the following command into a
terminal: `docker ps`.

### Connect to the databases

#### PostgreSQL

If you don't already have the postgres-client installed on your system do so by running
   `sudo apt install postgres-client`.

Connect to the postgres client by entering the following command: 
`psql -h0.0.0.0 -p5432 -Uroot`. To access the image via the client enter the password `root`.

(Section will be replaced by auto config some day)<br/>
Create a database called `cocktailshop` by running ``

#### MariaDB

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