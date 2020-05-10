# Tatooine
Simple app JAX-RS with jersey + grizzly2 + Morphia + mongo


### Running Tests

```shell
> mvn clean test
```

### Running App
```shell
> mvn clean test exec:java
```

Server must be up at localhost:8080 
 

### Routes
| Path          | Method | Description                               | Body JSON fields                                                                  |
| ---------- | :----: | --------------------------------------- | :-------------------------------------------------------------------------------: |
| /planets/seed |  Get  | Executes a seed with 6 planets from SWAPI | N/A |
| /planets     |  Get  | Gets all planets | N/A |
| /planets/{id} |  Get  | Gets a planet by its ID | N/A |
| /planets/name/{name} |  Get  | Gets a planet by its name | N/A |
| /planets     |  Post  | Creates a planet by its ID | name: String, climate: String, terrain: String [, filmAppearances: Integer]|
| /planets/{id} |  Delete  | Gets a planet by its ID | N/A |

