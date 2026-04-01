package ConnectFour;

public class connectFour {
    
}


// Build a tow player connect four game. Players take turns dropping red or yellow discs into a 6x7 grid. The first to align four of thier own discs vertically, horizontally, or diagonally wins.


//1. Primary capabilities
//2. Error handling
//3. Scope boundaries

//Requirements
//1. Two Players take turns dropping discs into a 6x7 grid.
//2. A disc fals to the lowest available row in a chosen column
//3. The game ends when:
//   - a player gets 4 discs in a row
//   - the grid is filled
//4. Invalid moves should be rejected clearly:
//   - Column is full
//   - Moving out of turn
//   - Moving after game is over


//Entities -> go through the requirements and look for nouns
//Game
//Board
//Player
//Disc


//Class Design
//Each class should have a single responsibility, a clear job. Single Responsibility Principle.


class Game{
    - Player player1;
    - Player player2;
    - Player currentPlayer;
    - Board board;
    - GameState gameState; //IN_PROGRESS, WON, DRAW will be enum
    - Player winner;

    + Game(Player player1, Player player2)
    + makeMove(Player player, int column) -> boolean

    - Player getCurrentPlayer()
    - Player getWinner()
    - GameState getGameState()
    - Board getBoard()
}


class Board{
    - int rows;
    - int cols;
    - DiscColour?[][] grid;  //enum DiscColour {RED, YELLOW}

    + boolean canPlace(column)
    + placeDisc(int column, DiscColour discColour) -> int // returns row where disc was placed or -1 if not possible to play for some reason
    + isFull() -> boolean
    + checkWin(int row, int col, DiscColour discColour) -> boolean

    -getRow()
    -getCol()
}

enum DiscColor:
    RED
    Blue

class Player{
    -color: DiscColor
    -name: string

    + getColor() -> DiscColor
    + getName() -> string
}




<!-- 
Implementation
1. Define Core logic
2. COnsider the edge cases

Game.makeMove, board.placeDisc, board.checkWin -->