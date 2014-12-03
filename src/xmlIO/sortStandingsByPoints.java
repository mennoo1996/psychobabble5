package xmlIO;
import java.util.Comparator;

import libraryClasses.Standings;


public class sortStandingsByPoints implements Comparator<Standings>{

	/**
	 * Method to sort standings by points
	 */
	@Override
	public int compare(Standings arg0, Standings arg1) {
		if(arg0.getPoints() < arg1.getPoints()) {
			return 1;
		} else if(arg0.getPoints() == arg1.getPoints()) {
			return 0;
		} else {
			return -1;
		}
	}

}
