04.06.2015

CHANGES:
    - Add and updated .gitignore
    - Guys use IntelliJ for development, Eclipse sucks
    - UPDATE.txt -> CHANGELOG.md
    - From now on non-administrative changes will be done only through PR-and-review before being passed into master
    
Before 04.06.2015

UPDATES FOR THE MAFIA CODE
*Note No longer using README.md as a memo because README.md should be reserved for a manual of the game.

UPDATE #3
1) Added a simple echo server client for now. The server is multi-threaded.
The server is the game server while the client will eventually be turned into whichever input method we use. 
2) Implemented assign all character method.
Uses json in this format:
{
  "20.0": {
    "id": 1054,
    "size": 20,
    "name": "Assign All Character Example",
    "characters": [
      "Mayor",
      "Vigilante",
      "Vigilante",
      "Doctor",
      "Doctor",
      "Investigator",
      "Investigator",
      "Escort",
      "Escort",
      "BusDriver",
      "Detective",
      "Lookout",
      "Sheriff",
      "Godfather",
      "Consigliere",
      "Mafioso",
      "Mafioso",
      "Consort",
      "SerialKiller",
      "Arsonist"
    ]
  }
}
A good place to convert format is: http://parsejson.com/
3) Did some cleaning up.
4) Should check out the flaaffy branch!
implemented the server-client CLI
the server is a threaded server that can handle multiple clients
and the client will be the midpoint between the server and harrispheg
run both of them and the in the client type in
"yourname help"
for the temporary CLI list.

UPDATE #2 -- will no longer use README.md for update, instead a separate UPDATE.txt.
1) New SCRUM.txt to be a log of the scrum meetings. It's good to keep track of everything we discussed in the meeting and know what we have done.

2) The characters have been mostly implemented and moving to make the CLI and GameEngine.java

3) The README.md will be reserved for manual of the game. Updates will be here. Everyone please log the updates making stuff to do / stuff done / etc easier to navigate and keep everyone on the same page.

2) Cleaned up GameEngine/Character.java
Sections labelled the following:
- find constructor : CONSTRUCTOR METHODS FOR THIS CLASS
- find new day : METHOD THAT RESETS THE VALUES OF THIS CHARACTER FOR A NEW DAY
- find role : METHODS THAT HAVE TO DO WITH ROLE
- find player : METHODS THAT HAVE TO DO WITH PLAYER
- find target : METHODS THAT HAVE TO DO WITH TARGET
- find kill : METHODS THAT HAVE TO DO WILL KILLING AND NIGHT IMMUNITY
- find visitor : VISITOR METHODS AND METHODS FOR ROLES THAT HAS TO DO WITH VISITORS
- find vote find lynch : METHODS THAT HAVE TO DO WITH VOTING
- find last will : LAST WILL WILL BE CHANGED UPDATED OBTAINED HERE
- find special : THE FOLLOW SECTION IS FOR ROLE METHODS THAT ARE SPECIFICALLY USED TO THE ROLE
- find investigator : INVESTIGATOR METHOD THAT GETS VAGUE INVESGITATOR RESULTS
- find arsonist : ARSONIST METHODS THAT HAVE TO DO WITH DOUSING
- find escort find consort : BLOCKING OR BLOCK IMMUNE METHODS
- find doctor find heal : THE METHODS THAT HAVE TO DO WITH DOCTOR HEALING
- find abstract : THE FOLLOW ARE ABSTRACT CLASSES

3) GameEngine/GameMessage.java will be a class that contains all game messages. This helps keep
game messages in code clean as they will be all grouped in one place. An example with noAction();
is given. Can be implemented and added later.

4) Player.get(); now throws an exception if player is already in game or is not registered rather
than returning null. Playing game with a null Player.java will be fatal.

5) TIMELINE :
- Finish all important roles Character/*.java
NOTE: When making doAction for a role, remember to check if no action, role blocked, and visitied.

- Finish junit tests for all important roles
NOTE: Lots of different game mechanics to be tested. We should have a list to check off tested.
Check all modifiable variables if they are modified correctly after a test is performed.

- Make GameEngine and interaction of the characters with each other in the game
NOTE* Assign characters, set new day, perform all night time actions, lynch, restart game, etc.

- Make GUI
NOTE: I DON'T KNOW HOW :D
derek: (try NetBeans, it has a visual GUI maker)

- Finish CLI
This will give us a basic way of playing the game until we develop a GUI
We can test all of our features this way to make sure our GameEngine works

7) Features to consider and not implemented yet :
- Catagories : Make list of players still in play as a catagory instead of listing role.
Ex. 2 Town Power, and 1 Mafia Killing still in play instead of 1 Bus Driver, 1 Veteran, and 
1 Godfather still play.
- GameOptions : Game options for all roles.
- Add roles : Add in all other roles.
- GameMessage : A class that prints messages for the game.
