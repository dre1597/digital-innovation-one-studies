gradle clean
gradle bootJar

#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar build/libs/reactive-flashcards-0.0.1.jar
java -jar build/libs/reactive-flashcards-0.0.1.jar
