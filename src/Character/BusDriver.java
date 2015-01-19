package Character;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.GameEngine;
import GameEngine.Player;

public class BusDriver extends Character {
	BusDriver() {
		super(Roles.BusDriver);
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		//bus driver must only target 2 people
		if (targets.size() != 2) {
			return false;
		}
		if(!GameEngine.AliveList.containsAll(targets)){
			return false;
		}
		this.targets = new ArrayList<Player>(targets);
		return true;
	}

	@Override
	public String doAction() {
		List<Player> target1List = GameEngine.getCharacter(targets.get(0)).getTargets();
		List<Player> target2List = GameEngine.getCharacter(targets.get(1)).getTargets();
		List<Player> tempSwap = new ArrayList<Player>(GameEngine.getCharacter(targets.get(0)).getTargets());
		target1List.clear();
		target1List.addAll(target2List);
		target2List.clear();
		target2List.addAll(tempSwap);
		return "Your night action has been received.";
	}

	@Override
	public boolean vote(List<Player> lynches) {
		// TODO Auto-generated method stub
		return false;
	}
}
