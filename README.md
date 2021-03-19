## run implementation based on graphql-java
this stopped working after bumping dgs from 3.2.0 to 3.9.3

`./gradlew bootRun --args='--spring.profiles.active=graphql-java'`

## run implementation based on netflix DGS

`./gradlew bootRun --args='--spring.profiles.active=netflix-dgs'`

when using netflix DGS, there's a GUI under `http://localhost:8080/graphiql`

## known limitations
- DGS doesn't support webflux https://github.com/Netflix/dgs-framework/issues/28
