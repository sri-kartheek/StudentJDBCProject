# 🎓 Student Management System (JDBC-Based)

This is a Java console-based Student Management System built using **JDBC** with **MySQL** as the backend database.  
The program allows users to perform basic CRUD operations on a `students` table using a simple command-line interface.

---

## 🔧 Features

- ✅ Add new students  
- ✅ View all students  
- ✅ Search student by ID  
- ✅ Delete a student
- ✅ Update a student  
- ✅ JDBC connection via `db.properties`  
- ✅ Error handling and input validation  

---

## 🗃️ Technologies Used

- Java 17  
- JDBC API  
- MySQL  
- VS Code / IntelliJ  

---

## 📁 Folder Structure

```
StudentJDBCProject/
├── StudentManager.java       # Main class with menu and JDBC logic
├── DBUtil.java               # Loads DB config from properties file
├── db.properties             # External file with DB credentials
└── lib/
    └── mysql-connector-j-<version>.jar
```

---

## 📄 db.properties Sample

```properties
url=jdbc:mysql://localhost:3306/testdb
username=root
password=your_password
```

> ⚠️ Do **NOT** upload this file with real credentials to GitHub.

---

## ✅ How to Run

### 1. Compile

```bash
javac -cp ".;lib/mysql-connector-j-<version>.jar" *.java
```

### 2. Run

```bash
java -cp ".;lib/mysql-connector-j-<version>.jar" StudentManager
```

> 📝 Replace `<version>` with your connector version (e.g., `9.3.0`)

---

## 🧠 Concepts Covered

- JDBC connection setup  
- PreparedStatement & ResultSet  
- Config loading via `.properties`  
- Basic error handling  
- Transaction management basics (if extended)

---

## 🚀 Future Enhancements
 
- Export student data to CSV  
- Implement pagination or search by name  
- Convert to web app using Servlets or Spring Boot  

---

## 👤 Author

**Sri Kartheek**  
[GitHub Profile](https://github.com/sri-kartheek)
