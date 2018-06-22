
# Spring Blog

- JPA: Java Persistence API
    - just an interface!
    - Hibernate is one of the most common implementations
- Spring Data: integrates JPA w/ Spring
    - under the hood, uses hibernate, but we won't interact w/ it directly
    - `starter` dependency in the `pom.xml`

[More details on JPA vs Spring Data vs Hibernate][1]

## Setup

1. Create your `application.properties` file based on the `example.properties`

    ```
    cp src/main/resources/example.properties src/main/resources/application.properties
    ```

1. Replace the values in example.properties w/ your specific environment values

[1]: https://coderanch.com/t/685335/certification/JPA-Spring-JPA-Spring-Data
