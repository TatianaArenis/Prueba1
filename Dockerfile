FROM openjdk:17
COPY "./target/Biblioteca-1.jar" "app.jar"
EXPOSE "8011"
ENTRYPOINT [ "java", "-jar", "app.jar" ]