
 
```shell
mvn test -DtestFailureIgnore=true
```
* Запрос на получение одной сущности
```shell
curl -i -X GET http://localhost:8080/api/get/33
```
* Запрос на получение списка сущностей
```shell
curl -i -X GET http://localhost:8080/api/getAll
```

* Создание сущности
```shell
curl -i -X POST http://127.0.0.1:8080/api/create -H 'Content-Type: application/json' -d ' {
  "addition": {
    "additional_info": "Дополнительные сведения",
    "additional_number": 2
  },
  "important_numbers": [
    42,
    87,
    15
  ],
  "title": "Заголовок сущности",
  "verified": true
}'
```

* Удаление сущности
```shell
curl -i -X DELETE http://127.0.0.1:8080/api/delete/78 -H 'Content-Type: application/json' 
```

 

* Обновление сущности и ее дополнений
```shell
curl -i -X PATCH http://127.0.0.1:8080/api/patch/1 -H 'Content-Type: application/json' -d ' {
  "addition": {
    "additional_info": "Дополнительные сведения",
    "additional_number": 1234567
  },
  "important_numbers": [
    42,
    87,
    15
  ],
  "title": "Заголовок сущности",
  "verified": true
}'
```