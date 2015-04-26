package Character;

import Enumerators.Roles;
import Enumerators.Sides;
import GameEngine.CannotGetPlayerException;
import GameEngine.Character;
import GameEngine.GameMessage;
import GameEngine.Player;
import GameEngine.GameEngine;
import GameOptions.SheriffOptions.DetectArsonist;
import GameOptions.SheriffOptions.DetectCultist;
import GameOptions.SheriffOptions.DetectMafia;
import GameOptions.SheriffOptions.DetectMassMurderer;

// *****************************************************************
// DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE
// this is a tag for all the characters/classes that are done
// *****************************************************************

/**
 * The Sheriff targets a Player and determines if they are Suspicious or Not
 * Suspicious
 */
public class Sheriff extends Character {
	// Game options
	GameOptions.Options detectsMafia;
	GameOptions.Options detectsArsonist;
	GameOptions.Options detectsCultist;
	GameOptions.Options detectsMassMurderer;

	/**
	 * Default constructor that set options to default
	 */
	public Sheriff(Player player) {
		super(Roles.Sheriff, player);
		detectsMafia = GameOptions.SheriffOptions.DetectMafia.DETECT_MAFIA_ON;
		detectsArsonist = GameOptions.SheriffOptions.DetectArsonist.DETECT_ARSONIST_ON;
		detectsCultist = GameOptions.SheriffOptions.DetectCultist.DETECT_CULTIST_ON;
		detectsMassMurderer = GameOptions.SheriffOptions.DetectMassMurderer.DETECT_MASSMURDERER_ON;
	}

	/**
	 * Constructors with options param
	 * 
	 * @param detectsMafia
	 * @param detectsArsonist
	 * @param detectsCultist
	 * @param detectsMassMurderer
	 */
	Sheriff(Player player, GameOptions.Options detectsMafia,
			GameOptions.Options detectsArsonist,
			GameOptions.Options detectsCultist,
			GameOptions.Options detectsMassMurderer) {
		super(Roles.Sheriff, player);
		this.detectsMafia = detectsMafia;
		this.detectsArsonist = detectsArsonist;
		this.detectsCultist = detectsCultist;
		this.detectsMassMurderer = detectsMassMurderer;
	}

	@Override
	public String doAction() throws CannotGetPlayerException {
		if (getTarget().size() != 1) {
			return GameMessage.NO_ACTION();
		}

		Player target = getTarget().get(0);

		if (!GameEngine.getCharacter(target).isAlive()) {
			return GameMessage.TARGET_DEAD();
		}
		if (this.isRoleBlocked()) {
			return GameMessage.NO_FEEDBACK();
		}

		GameEngine.getCharacter(target).addVisitor(getPlayer());

		String result;

		Roles targetRole = GameEngine.getCharacter(target).getRole();
		if (detectsArsonist.equals(DetectArsonist.DETECT_ARSONIST_ON)
				&& targetRole.equals(Roles.Arsonist)) {
			result = "an arsonist";
		} else if (detectsCultist.equals(DetectCultist.DETECT_CULTIST_ON)
				&& targetRole.equals(Roles.Cultist)) {
			result = "a cultist";
		} else if (detectsMassMurderer
				.equals(DetectMassMurderer.DETECT_MASSMURDERER_ON)
				&& targetRole.equals(Roles.MassMurderer)) {
			result = "a mass murderer";
		} else if (detectsMafia.equals(DetectMafia.DETECT_MAFIA_ON)
				&& targetRole.getSide().equals(Sides.Mafia)
				|| detectsMafia.equals(DetectMafia.DETECT_MAFIA_ON)
				&& targetRole.getSide().equals(Sides.Triad)) {
			result = "suspicious";
		} else {
			result = "not suspicious";
		}
		return GameMessage.SHERIFF_FEEDBACK(result);
	}
}