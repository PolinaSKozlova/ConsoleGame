# Console Game & Maven

## Surrender, You're Surrounded


Do you remember good old Java games? In the early 2000s, they were in each phone. Now Java developers design scalable enterprise systems, but at that time... 

Your objective today is to get a bit nostalgic and implement a game where you run from artificial intelligence entities across a square field. 

The program shall generate a random map with obstacles. Both player and its enemies are located on the map in a random manner. Each map element shall have a certain color.

Example of a generated map:

**Designations**: <br>
`o` - позиция игрока на карте<br>
`#` - стена<br>
`x` - враг<br>
`O` - цель, которую нужно достичь для выигрыша.

**Правила игры**:
1. Каждый игрок делает одно движение. Враг догоняет игрока, если на его ходе он может встать на позицию игрока.
2. Доступные движения - влево, вправо, вверх и вниз.
3. Если враг не может двигаться вперед(есть стена или другой враг, или достигнута граница карты), он пропускает ход.
4. Целевая точка также является препятствием для врага.
5. Если игрок не может продвинуться(окружен стенами, врагами или достиг края карты), он проиграл.
6. Игрок проигрывает, если враг настиг его до того, как игрок дошел до конечной точки.
7. Игрок ходит первым.

**Настройки игры**:
1. Размер поля, число стен и врагов вводятся в параметрах строки:<br>
`$ java -jar game.jar --enemiesCount=10 --wallsCount=10 --size=30 --profile=production`
2. A check shall be made whether it is possible to put the specified number of enemies and obstacles on the map of given size. If input data is incorrect, the program shall throw an unchecked IllegalParametersException and shut down.
2. Enemies, obstacles, the player, and the target point are positioned on the field randomly. 
3. When generating the map, enemies, the player, obstacles, and the target point shall not overlap.
4. In the beginning of the game, the map must be generated so that the player can reach the target point (the player must not be blocked by walls and map edge in starting position).
5. To make a move, the player shall enter a number in the console that corresponds to the movement direction A, W, D, S (left, upward, right, downward).
6. If the player is unable to make a move in the specified direction, another number (direction) shall be entered.
7. If the player understands in the beginning or middle of the game that the target point is unreachable, they shall end the game by entering 9 (player loses).
8. Once the player has made a move, it is its enemy's turn to make a move towards the player. 
9.  In the development mode, each enemy's step shall be confirmed by the player by entering 8.
10. Upon each a step of any participant, map must be redrawn in the console. In development mode, the map shall be displayed without updating the screen.
11. Pursuing algorithm shall take account of the target object location in each step.

**Architecture requirements**:
1. Two projects shall be implemented: Game (contains game logic, application entry point, output functionality, etc.) and ChaseLogic (contains pursuing algorithm implementation).
2. Both are mavenprojects, and ChaseLogic shall be added as a dependency to pom.xml inside Game.
3. Game.jar archive shall be portable:  JCommander and JCDP must be directly included in the archive. At the same time, all libraries connected to the project shall be declared as maven-dependency. To build such archive, the following plugins shall be used.

It is also necessary to create a configuration file called application-production.properties. In this file, you will specify your application settings. The example of this file is shown below:

enemy.char = X <br>
player.char = o <br>
wall.char = \# <br>
goal.char = O <br>
empty.char= <br>
enemy.color = RED <br>
player.color = GREEN <br>
wall.color = MAGENTA <br>
goal.color = BLUE <br>
empty.color = YELLOW

This configuration file will be located in resources folder of the launched jar archive.

In addition to that, application-dev.properties file should be implemented. Structure of this file is similar to that of application.properties. Here, you can specify parameters for distinguishing application startup in development mode (for example, different colors/characters for map components).

It is necessary to keep in mind that the program may also be started in other modes. For this purpose, the respective properties file can be added to the source project, and the mode itself is passed via --profile parameter.
