# Mafia
Our mafia game repository

UPDATE #1 -- will start using README.txt as a memo

1) The below comment will be written at on the top of Character/*.java if the character role
is fully written and ready for testing/already tested.
//*****************************************************************
//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
//this is a tag for all the characters/classes that are done
//*****************************************************************

Currently in this catagory contains : Bus Driver, Consigliere, Consort, Escort, Doctor, 
Investigator, Sheriff, Mayor, Serial Killer, Mayor.

Of those two : Sheriff and Investigator has options included and are donoted by (｡◕‿◕｡)

2) Cleaned up GameEngine/Character.java
Sections labelled the following:
- find constructor : CONSTRUCTOR METHODS FOR THIS CLASS
- find temp : METHODS THAT SHOULD BE CHANGED OR REVISED
- find new day : METHOD THAT RESETS THE VALUES OF THIS CHARACTER FOR A NEW DAY
- find role : METHODS THAT HAVE TO DO WITH ROLE
- find player : METHODS THAT HAVE TO DO WITH PLAYER
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
is given. Can be implemented added later.

4) Player.get(); now throws an exception if player is already in game or is not registered rather
than returning null. Playing game with a null Player.java will be fatal.

5) TIMELINE :
- Finish all important roles Character/*.java
NOTE* When making doAction for a role, remember to check if no action, role blocked, and visitied.

- Finish junit tests for all important roles
NOTE* Lots of different game mechanics to be tested. We should have a list to check off tested.
Check all modifiable variables if they are modified correctly after a test is performed.

- Make GameEngine and interaction of the characters with each other in the game
NOTE* Assign characters, perform all night time actions, lynch, etc.

- Make GUI
NOTE* I DON'T KNOW HOW :D

7) Features to consider and not implemented yet :
- Catagories : Make list of players still in play as a catagory instead of listing role.
Ex. 2 Town Power, and 1 Mafia Killing still in play instead of 1 Bus Driver, 1 Veteran, and 
1 Godfather still play.
- GameOptions : Game options for all roles.
- Add roles : Add in all other roles.
- GameMessage : A class that prints messages for the game.