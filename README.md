# Chess Game
This is a game for the Software Design Exam. This game was made by Andrea Bezzolato
and Samuele Poma, students of the HZ University of Applied Sciences.

The goal of this, is to use (active) design patterns in a Java console program in pairs.
We decided to do this by creating a chess game. Moreover, we decided to implement
6 design pattern, two per each category:
- Creational: <b>Prototype</b> and <b>Singleton</b>.
- Structural: <b>Decorator</b> and <b>Facade</b>.
- Behavioural: <b>State</b> and <b>Strategy</b>.

Now we will explain each design pattern individually and explain the function of them.

## Singleton Design Pattern:
In this game, the Singleton Design Pattern was implemented using the Game class.
This class, in fact allows the program to have only one instance of the game, while
providing a global access point to this instance. It solves 2 main problems: ensures
that a class has just a single instance and provides a global access point to that instance.
This is achieved by having a static instance of the class inside of it, making the
constructor private and having a getInstance method that returns the class and
creates it if it doesn't exist yet.

## Facade
In this game, the Facade Design Pattern was implemented using the BoardFacade class.
This class, provides a simple interface so that the application doesn't need to
operate with complex functionalities but can have a layer in between. This is achived
by having the Board class functionalities being called from the Facade and not from
the Main. A facade might provide limited functionality in comparison to working with
the subsystem directly. However, it includes only those features that clients really
care about. This is useful when the clients need only some operations and not all of
them.

## State
In this game, the State Design Pattern was implemented using the GameState, SetupState
and PlayState classes. This classes allow the game class to act differently based on
the state of the application. At the beginning in fact, the game will be in the setup
state, where the pieces are put inside of the game. Next, the game will go in the play
state, where the actual game will happen. Instead of implementing all behaviors on its
own, the original object, called context, stores a reference to one of the state objects
that represents its current state, and delegates all the state-related work to that object.