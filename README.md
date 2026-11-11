# Bubble Shelf

## Overview

**Bubble Shelf** is a comic book recommendation application based on a personality test.

Users answer 13 questions to identify one or more reader profiles. Based on the results, the application recommends comic books that match their reading preferences.

The questionnaire and results are available in French and English.

## Features

- Personality test with 13 questions.
- Identification of one or more reader profiles.
- Personalized comic book recommendations.
- French and English translations for questions, answers, results and comics.

## Tech Stack

| Component | Technology |
|---|---|
| Backend | Java 21, Spring Boot 4 |
| Build tool | Maven |
| Frontend | Angular 21 |
| Package manager | npm |
| Node.js management | nvm |
| Database | PostgreSQL |

## Project Structure

```text
bubble_shelf/
├── bubble_api/    # Spring Boot backend
├── bubble_ui/     # Angular frontend
├── bubble_db/     # Database scripts (DDL and DML)
└── README.md
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/milena-771/bubble-shelf.git
cd bubble_shelf
```

### 2. Set up the database

Create a PostgreSQL database named `bubble_shelf`:

```sql
CREATE DATABASE bubble_shelf;
```

The `bubble_db` directory contains the SQL scripts:

- **data.ddl.sql**: create the database tables and relationships.
- **data.dml.sql**: populate the database with profiles, questions, answers, comics and their translations.

Run the DDL scripts first, followed by the DML scripts.

### 3. Configure and run the backend

Configure the database connection in `bubble_api/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bubble
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 4. Install and run the frontend

Make sure [nvm](https://github.com/nvm-sh/nvm) is installed.

From the project root:

```bash
cd bubble_ui
nvm install 22.22.3
nvm use 22.22.3
npm install
ng serve
```

The frontend is available at:

```text
http://localhost:2006
```

## Language Support

The application supports:

- English
- French

Questions, answers, reading profiles and comic book information are stored with translations in the database.