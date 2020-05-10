# Tatooine
Simple app JAX-RS with jersey + grizzly2 + Morphia/MongoDB


### Running Tests

```shell
> mvn clean test
```

### Running App
```shell
> mvn clean test exec:java
```

Server must be up at localhost:8080 and MongoDB must be at localhost:27017
 

### Routes
| Path          | Method | Description                               | Body JSON fields                                                                  |
| ---------- | :----: | --------------------------------------- | :-------------------------------------------------------------------------------: |
| /planets/seed |  GET  | Executes a seed with 6 planets from SWAPI. Warning: data loss | N/A |
| /planets     |  GET  | Gets all planets | N/A |
| /planets/{id} |  GET  | Gets a planet by its ID | N/A |
| /planets/name/{name} |  GET  | Gets a planet by its name | N/A |
| /planets     |  POST  | Creates a planet | name: String, climate: String, terrain: String [, filmAppearances: Integer]|
| /planets/{id} |  DELETE  | Deletes a planet by its ID | N/A |

