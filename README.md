# DungeonGame
A java game I'm making for practice. Based around a text adventure concept.

Made some changes to refactor the game to run in a loop and have some objects setup for the various types of elements that might be present in a game like this.

To run it compile then execute the java program

`javac Game.java`

`java Game`

## Prerequisites

### Development
`sudo apt install openjdk-17-jdk -Y`

#### Building
on Windows
`./gradlew.bat build`
or on Mac/Linux
`./gradlew build`
#### Testing
on Windows
`./gradlew.bat cleanTest test`
or on Mac/Linux
`./gradlew cleanTest test`
#### Running (without extra gradle output)

on Windows
`./gradlew.bat run --console=plain`
or on Mac/Linux
`./gradlew run --console=plain`

Assumes have JDK 9+

`sudo apt install openjdk-17-jdk -Y`
