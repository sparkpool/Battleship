# Battleship Game

This game is for two players who will play on a square grid where each player will set their ships on the cell of a grid 
and then each player will get a chance to destroy opponent ship by guessing its coordinates.

Each ship is of size one cell.

Each player will get equal chance to destroy opponent ships. Chance will be less than total cells in the Grid.

There are two sample files for input and output with name SampleInput.txt and SampleOutput.txt.

There is a main method in class BattleshipStarter which contains main method.

Code reads input from /tmp/ folder with file name Input.txt and if you want you can change the path in the constant file name BattleshipConstants under constants package inside sr/main/java

Code writes output to the file name Output.txt inside the same folder from where it has read the input i.e /tmp and if you override for read file then it will automatically change for output file.

Two players name are hard coded in the code instead of reading from the file so if you want to change the payers name then you can change from the same constant file mentioned above for changing input and output files folder.

This is a Maven Project so you can run Junit Test cases using mvn command or directly from the IDE



