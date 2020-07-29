# Chat App

This chat app is a very quick implementation of a basic javascript free client, and a java based server. Works by templating some html to load in another templated iframe, with meta refresh causing it to refresh every second, and display the new messages. 

Once running you can view at [localhost:5678](http://localhost:5678/).
This took about an hour to design and implement.


## Prep

(If you don't have maven installed on your system, a waven wrapper is included, replace all `mvn` commands with `./mvnw`)

`mvn clean install`


## Running
`mvn exec:java`

## Packaging
`mvn package`, file is `target/iims-server.jar`