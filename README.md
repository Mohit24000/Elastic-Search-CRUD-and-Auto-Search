# Elastic-Search-CRUD-and-Auto-Search
This is my Spring Boot Project of Performs CRUD and Auto search feature using Edge N_gram 

# 🚀 Elasticsearch CRUD & Auto Search (Spring Boot)

## 📌 Overview

This project is a **Spring Boot application** integrated with **Elasticsearch** to perform:

* ✅ CRUD operations (Create, Read, Delete)



## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Elasticsearch (v8+)
* Postman (for testing)
* Maven

---

## 📂 Project Structure

```
com.ElasticSearch.CRUD.and.AutoSearch
│
├── controller
│   └── PinController.java
│
├── service
│   └── PinTagServiceImpl.java
│
├── model
│   └── PinTags.java
│
├── config
│   └── ElasticsearchConfig.java
```


## ⚠️ Common Issues & Fixes

### ❌ Connection is closed

* Ensure Elasticsearch is running
* Use `"http"` in `HttpHost`
* Avoid closing the client manually

---
## 🚀 Future Improvements

* 🔥 Autocomplete search (n-gram analyzer)

---

## 👨‍💻 Author

**Mohit**
B.Tech Mechanical Engineering Student
Learning Backend + Search Systems 🚀

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
