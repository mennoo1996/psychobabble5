import java.util.Comparator;


public class sortStandingsByPoints implements Comparator<Standings>{

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
