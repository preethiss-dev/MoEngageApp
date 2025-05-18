# MoEngage Web Application 🧠

This is a simple Java-based web application that allows users to:
- Sign up and log in
- Search for dog images using HTTP response codes
- Save filtered dog image lists
- View, edit, and delete saved lists

## 🔧 Technologies Used

- Java Servlets & JSP
- JDBC with MySQL
- Apache Tomcat
- HTML/CSS

## ✅ Features

- **User Authentication**: Sign-up & login with session tracking
- **Search**: Enter response codes (like 203, 2xx) to view corresponding dog images from [http.dog](https://http.dog)
- **Save Lists**: Save selected dog images with a custom list name
- **View Lists**: Browse saved lists for each user
- **Edit/Delete**: Update list names or remove them entirely

## 🚀 Setup Instructions

1. Clone this repo and open it in Eclipse as a **Dynamic Web Project**
2. Set up Apache Tomcat (v9+)
3. Configure MySQL and run the provided schema to set up your database
4. Update DB connection settings in the DAOImpl files
5. Run the app on `http://localhost:8080/MoEngageApp`

## 🖼️ Screenshots

> Add screenshots of your working UI here (Search, View Lists, Edit/Delete)

## 🧠 Credits

Built as part of a practical assignment involving:
- Servlets & JSP
- MVC-style structure
- UI styling using plain HTML/CSS

