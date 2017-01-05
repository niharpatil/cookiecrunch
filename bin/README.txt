=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 40531266
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. I/O
  I use I/O in two key components of my game design. If a user completes a level, that user's user-name 
  along with the information about the level they completed is stored in a "winners.txt" file. My game program
  writes to this file by capitalizing on the Java IO BufferedWriter and FileOutputStream. I have created a class,
  titled "WinnersController" whose instances' sole purpose is writing to the winners.txt file. This is an appropriate 
  use of the concept because I want an easy way to store data about winners after the program has terminated - I found 
  that the best way to do so was by writing to a text file that my program can read from and write to. With my knowledge
  of what I learned in class, I found the BufferedWriter to be the easiest way to write to winners.txt.
  
  Additionally, I used IO to read LevelSettings from a textfile titled "LevelSettings.txt". This file contains information 
  about the target and obstacle object locations. It also contains information about the target objects size. I do not 
  want users to be able to create levels through my program - thus, the only way to create levels is by manually writing
  levels to the text file (note that a very specific format must be followed). I have created a class, titled LevelSetting,
  whose sole purpose is to retrieve information about the settings in a level. Level settings are read from the text file by 
  using FileInputStreamReader, FileInputStream, and BufferedReader. I found that these technologies were appropriate to use
  since they allowed me to read from a file easily. 

  2. Collisions 
  A user completes a level when a bullet and a target object experience a collision. A user loses on a level if any of their
  bullets experience collisions with obstacle objects. Note that if a bullet is not fired (is still attached to the user's 
  gun), then the user does not lose a level - nothing happens. However, as soon as the state of a bullet changes from "loaded"
  to "fired", it is possible for the user to lose the game by that bullet colliding with any obstacle object. Note that the
  target object, obstacle objects, and bullet objects effectively "bounce" around the screen - which essentially is a change
  in the objects' velocities.
  
  My TA did not give me feedback on this section of the game proposal. Actually, I have added more implementation of collisision
  beyond what I have included in the game proposal.
  
  I found that this implementation of collisions was apt since collisions of different objects in my game are the only ways
  a user can win or lose on any given level.

  3. Testable Components
  My tests test the different components of my game, including of my exceptions, GameSpace, LevelSetting (for file reading),
  Location (to test if locations are being stored properly), Shooter (to test that the shooter only moves laterally and does
  not exceed boundaries). Due to the comments that I received from my TA, I also tested the move methods in my game objects.
  I found that these tests were appropriate since my game comprises of many different components that are fairly dependent on 
  each other for working properly. Additionally, these tests helped me pinpoint where errors in my code were and thus made 
  debugging much much easier.

  4. Collections
  I used collections throughout my game. I used a LinkedList to store bullets. I chose to use a LinkedList because efficient
  searching was not important for how I used bullets. I only needed to "fire" the very next bullet. LinkedLists are also easy
  to iterate through so that I could render the bullets at their respective locations. 
  
  I stored all obstacles in a LinkedList<Obstacle> linked list. I used this purely to render the obstacles at different locations.
  Additionally, being able to iterate through the linked list helped me detect collisions with bullets much more easily.
  
  After receiving feedback on my implementation of collections on my original game proposal.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  - BadLevelException
  	- Notifies that an error occurred in reading a level
  	- This exception serves to help me understand what might have gone wrong in loading a level into the game
  - Bullet
  	- Specifies information regarding a bullet's location, size, and state ("shot" or not "shot")
  	- Bullets have a special move method that has different behavior depending on the state of a bullet
  	- Bullets are fired by a user and are used to collide with a target object
  - Direction
  	- The Direction class is used to calculate what direction a game object will travel upon collision with a wall
  - Game
  	- This class is also used to initialize different components of the game and run the game
  - GameObj
  	- This class specifies behaviors that are generally standardized across objects in the game (specifically the
  	target object, obstacle objects, shooter object, and bullet objects). 
  - GameSpace
  	- This class initializes the state of the game - which primarily includes translating information obtained from
  	specifications in the level setting to actual components of the game. For example, it understands that a "target size"
  	specification in the level setting file should translate to intializing the size of the actual target object that
  	the user will see. Additionally, this class contains a game loop in which the state of bullets and obstacles are updated.
  	Collisions are also detected in this loop. This class is used to read user input regarding username and what level the 
  	user wants to play.
  - LevelSetting
  	- This class is used to read information about specifications of what a level should contain from the levelsettings.txt 
  	file. It parses the file and reads specifications including target object size and locations. It also reads in 
  	information about the obstacle locations. Note that all of these specifications are specific to a level that the 
  	user provides.
  - WinnersController
  	- If a user completes a level, that user's user-name along with the information about the level they completed is stored 
  	in a "winners.txt" file. My game program writes to this file by capitalizing on the Java IO BufferedWriter and 
  	FileOutputStream. I have created a class,titled "WinnersController" whose instances' sole purpose is writing to the 
  	winners.txt file.
  - Location
  	- The location class helps to encapsulate the location of objects in the game. It reduces the hassle of having to deal
  	with many independent x and y positions. Additionally, this class makes intuitive sense because the position of any object
  	is not limited to just its x position.
  - Obstacle
  	- The Obstacle class creates Obstacle objects. These objects are very important to gameplay (if a bullet collides with
  	an obstacle object, then the game is over and the user loses). This class inherits from GameObj and includes specific 
  	information about the rendering of the obstacle object (as an image).
  - Shooter
  	- The Shooter class creates Shooter objects. This is the object that the user can control the position of. The shooter 
  	 object has a gun attached to it from which bullets are shot. This class contains infrmation about how a shooter object
  	 can move (note that a shooter object is limited to moving laterally and cannot vertically). Additionally, this class
  	 contains information about the ammo (number of bullets left) that a shooter object has.
  - StartPanel
  	- This class serves to just take some code out of the Game class that did not need to be in the Game class. Specifically,
  	it mainly contains instructions for how to play the game (something that didn't seem too important to be in the Game class).
  - Target
  	- This class creates Target objects. It contains information about the Target's location, size, and specific instructions about
  	its rendering (note that the target object is an image).
  - WinnersRetriever
  	- this class fetches winners from the winners.txt file. 


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  - The most significant stumbling block was working with user input. JOptionPanes are not particularly easy to work with. I needed
  to read quite a bit of documentation to completely understand how they work and to handle exceptions thrown by them.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  There is definitely a lot of separation of functionality. I deliberately took an approach to split up my game as much as 
  possible. It helped a lot with debugging and for understanding how individual components of a program "interact" with
  each other. If given the chance, I would refactor my LevelSetting class to allow for users to create levels in a user-friendly
  way that would not disturb the necessary formatting of the levelsettings.txt file.
  Private state is encapsulated very well and is often either inaccessible or only accessible through getter and setter methods.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  BufferedReader,BufferedWriter,FileInputStream,FileOutputStream,MushroomOfDoom,Google images (for the images for target,obstacle,
  and bullet objects). StackOverflow for displaying scrollable winners.


