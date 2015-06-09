package Character;

import Enumerators.Roles;
import GameEngine.CannotGetPlayerException;
import GameEngine.GameEngine;
import GameEngine.Player;
import Resources.GameMessage;

public class Vigilante extends Character {
	
	private int shots;
	
	public Vigilante(Player player) {
		super(Roles.Vigilante, player);
		shots = 3;
	}

	@Override
	public String doAction() throws CannotGetPlayerException {
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION();
		}
		
		Player target = getTarget().get(0);
		
		if(!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}
		
		if(this.isRoleBlocked()) {
			shots--;
			return GameMessage.VIGILANTE_FEEDBACK(shots);
		}
		
		if(shots <= 0) {
			return GameMessage.VIGILANTE_FEEDBACK(shots);
		}
		GameEngine.getCharacter(target).addVisitor(getPlayer());

		GameEngine.getCharacter(target).kill();	
		
		shots--;
		
		return GameMessage.VIGILANTE_FEEDBACK(shots);
	}
}
