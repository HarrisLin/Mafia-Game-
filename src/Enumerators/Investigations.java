package Enumerators;

import java.util.ArrayList;
import java.util.List;

/**
 * An enumerator with possible investigation results and contains a method that
 * returns possible investigations for the role
 * 
 * @author pacified
 *
 */
public enum Investigations {
	Trespassing, Kidnapping, NoCrime, Corruption, IdentityThief, Soliciting, Murder, DisturbanceOfPeace, Conspiracy, DestructionOfProperty, Arson;
	/**
	 * 
	 * @param role 
	 * @return list of possible investigation results for that role
	 */
	public static List<Investigations> doInvestigation(Roles role) {
		List<Investigations> investigations = new ArrayList<Investigations>();
		
		//not yet implemented
		
		return investigations;
	}
}
