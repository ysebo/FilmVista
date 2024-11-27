# Movie Review Platform

A platform for sharing opinions about movies, where users can leave reviews, rate films, and discuss what they have watched. The main goal of this project is to provide an easy-to-use interface for movie selection and user interaction, as well as to gather valuable feedback to improve content and marketing campaigns.

## Technologies

- **Java 17**
- **Spring Boot 3**
- **Spring Security 6**
- **PostgreSQL 16**
- **TMDb API** (for fetching movie data)

## Description

This service allows users to:

- Search for movies and view detailed information.
- Leave reviews and rate movies.
- Discuss films with other users.
- Receive recommendations based on ratings and preferences of other viewers.

The platform can be used by movie theaters and streaming services to collect reviews and improve content based on user feedback. It can also be useful for marketers and producers to develop targeted campaigns and new projects.

## Architecture

The project consists of the following components:

- **Backend (Spring Boot)**: Handles the application logic, including database interactions and integration with the TMDb API for movie data retrieval.
- **Database (PostgreSQL)**: Stores movie data, reviews, ratings, and user information.
- **Security (Spring Security)**: Ensures the security of the application, including user authentication and authorization.


## Installation and Setup

### Requirements

- **Java 17** or higher
- **PostgreSQL** (if not using Docker)

### Local Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/ysebo/FilmVista.git
   cd movie-review-platform
    ```
2.
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
3. There should be application-secret.properties file where you can store your TMDb API key , all needed keys.
   ```
    tmdb.api.key=your_api_key
    ```
