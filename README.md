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
| /MyResource     |  Get  | Gets a basic message | N/A |
