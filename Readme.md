POST /notes

GET /notes

GET /notes/filter/tag/java

GET /notes/filter/important/true

POST /notes/{id}/like

GET /notes/trending

POST http://localhost:8080/notes

{
"note": "Learning Java Spring Boot API",
"tags": "study,backend",
"important": true
}
{
"id": 1,
"note": "Learning Java Spring Boot API",
"tags": "study,backend,java,spring,api",
"important": true,
"likes": 0
}


GET http://localhost:8080/notes
[
{
"id": 1,
"note": "Learning Java Spring Boot API",
"tags": "study,backend,java,spring,api",
"important": true,
"likes": 0
}
]

GET http://localhost:8080/notes/filter/tag/java
tags : "java"

GET http://localhost:8080/notes/filter/important/true
→ Sirf important = true notes aayenge.

POST http://localhost:8080/notes/1/like
{
"id": 1,
"note": "Learning Java Spring Boot API",
"tags": "study,backend,java,spring,api",
"important": true,
"likes": 1
}

GET http://localhost:8080/notes/trending
→ Notes ko likes ke descending order me return karega.