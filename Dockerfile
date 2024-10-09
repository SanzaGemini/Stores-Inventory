FROM openjdk:17
EXPOSE 8080
ADD target/stores-inventory.jar stores-inventory.jar
ENTRYPOINT [ "java","-jar","/stores-inventory.jar" ]
