package TestUtils;

import java.util.ArrayList;
import java.util.List;

import Enumerators.Roles;
import GameEngine.Character;
import GameEngine.Player;

public class TestCharacter extends Character {
	
	public TestCharacter(Roles role, Player player) {
		super(role, player);
	}
	
	public TestCharacter(Roles role, Player player, boolean invulnerable) {
		super(role, player, invulnerable);
	}
	
	public TestCharacter(Roles role, Player player, boolean invulnerable, boolean block_immune) {
		super(role, player, invulnerable, block_immune);
	}
	
	public boolean setHealed(boolean healed) {
		if (healed) {
			return healPlayer();
		} else {
			return false;
		}
	}

	@Override
	public boolean setTarget(List<Player> targets) {
		return super.setTarget(targets);
	}
		
	/**
	 * Set a single target.
	 * @param player The single target
	 * @return true if target was set successfully, false otherwise
	 */
	public boolean setTarget(Player player) {
		ArrayList<Player> targets = new ArrayList<Player>();
		targets.add(player);
		return setTarget(targets);
	}
	
	/**
	 * Add a target to the target list
	 * @param player The target to add
	 * @return true if target was added successfully, false otherwise
	 */
	public boolean addTarget(Player player) {
		ArrayList<Player> new_targets = (ArrayList<Player>) getTarget();
		new_targets.add(player);
		return super.setTarget(new_targets);
	}
	
	@Override
	public String doAction() {
		return "doAction!";
	}
	
}
