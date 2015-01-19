package Character;

import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

/**
 * 
 * SerialKiller targets one person to kill at night and is immune at night.
 */

public class SerialKiller extends Character {

	SerialKiller(){
		super(Roles.SerialKiller, true);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		this.targets = targets;
		return(targets.size() == 1);
	}

	/**
	 * questionable-- alive status of victim is important or not?
	 */
	@Override
	public String doAction() {
		Character victim = GameEngine.getCharacter(targets.get(0));
		if(victim.checkAlive() == true){
			victim.kill();
		}
		return null;
	}
	
}