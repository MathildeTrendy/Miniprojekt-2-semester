# Onboarding of new member

## Welcome and Introduction

In this project, we are developing a digital wish list that allows customers to create a digital wish list, share it with others, and reserve items on the wish list. The purpose of this project is to enhance our skills in some of the subject areas covered in the exam project. This project provides us with an opportunity to work in a problem-based and interdisciplinary manner, focusing on requirements, design, and implementation of a web-based database application.

## Technical Overview

In this project, we are using GitHub as our version control tool to manage the source code for the project. Additionally, we are also using MySQL Workbench as a tool to manage the database system for the project. Both of these programs are integral to our workflow and will be used in the day-to-day tasks of the project.

## Connect to the database
The app uses a MySql database.

You will be needing two databases

-	One for development
-	Another for production


To simplify the development process we'll skip installing dependencies and instead run them from docker containers. That gives us the benefit that the process is the same wether you're on a Mac, a Windows PC or an online environment and regardless of what IDE you're on. 

If you are on your own PC (Mac or Windows) simply install and start [Docker Desktop] (https://llk.dk/74f3of).

You can manipulate your database in different ways. But in this project, you just have to do it through a database management tool: 

MySql Workbench conncetion
 
- username (root)
-	password (MathildeSabrina)
-	database (miniProjekt) 
-	host (127.0.0.1)
-	port (3306)
  
  
Below you can copy paste the Database script or you can find it located at: https://llk.dk/cjjmth
  
```
DROP DATABASE IF EXISTS miniProjekt;
CREATE DATABASE miniProjekt;

USE miniProjekt;

CREATE TABLE items(
item_id INT NOT NULL AUTO_INCREMENT,
itemName varchar(255) NOT NULL,
itemDescription varchar(255) NOT NULL,
itemPrice double,
itemQuantity int,
itemUrl varchar(255) NOT NULL,
primary key(item_id)
);

INSERT INTO items(item_id, itemName, itemDescription, itemPrice, itemQuantity, itemUrl)
VALUES
(1, "Nike Shorts", "Lårkorte shorts", 250, 1, "www.nikeshorts.dk");


CREATE TABLE user(
user_id int NOT NULL AUTO_INCREMENT,
firstName varchar(255) NOT NULL,
lastName varchar(255) NOT NULL,
email varchar(255) NOT NULL,
password varchar(255) NOT NULL,
primary key (user_id)
);

INSERT INTO user(user_id, firstName, lastName, email, password)
values
(11, "Louise", "Ingerslev", "Lulu@gmail.com", "1234");

CREATE TABLE wishLists(
list_id int NOT NULL AUTO_INCREMENT,
listName varchar(255) NOT NULL,
listUrl varchar(255) NOT NULL,
primary key (list_id)
);

INSERT INTO wishLists(list_id, listName, listUrl)
values
(11, "Fødselsdagsliste", "www.listeføds.com");
```

## Access to Resources
  
To provide an overview of the project, the new team member will have full access to our GitHub repository, where all the source code and project-related documentation are stored. This includes an ER model (conceptual model) of the database design and a class diagram of the program design, which will be updated regularly with changes in the project.

## Meetings and Communication

A calendar has been created in the GitHub project, where scheduled meeting times, team members present, and the agenda for the meeting will be indicated. Information about any snacks or refreshments during the meeting will also be provided. Team members are encouraged to add comments if they need to leave a meeting early. This calendar will serve as a central place to coordinate and communicate about meetings in the project.

## Project Tasks

A Scrum board has been set up in the GitHub project, where all the tasks for the project are organized. Here, you can find an overview of who is working on what, what has been done, and what still needs to be completed. This Scrum board will be a central place where we manage and prioritize our project tasks, and it will also be a tool to coordinate work across the team.

## Contact List

If users have any questions or need assistance during their onboarding process, you can contact our team by GitHub account. Our team is dedicated to providing prompt and helpful support to ensure a smooth onboarding experience for new members.

- <a href="https://github.com/najamoe">Naja Egede Moe (Scrum Master) </a>
- <a href="https://github.com/MathildeTrendy">Mathilde Trend</a>
- <a href="https://github.com/sabr5840">Sabrina Ebbesen</a>
