package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentage extends TypeSafeMatcher <Server> {

	private double expectedLoadPercentage;
	
	public CurrentLoadPercentage(double expectedLoadPercentage) {
		// TODO Auto-generated constructor stub
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		description.appendText("a server with load percentage of ").appendValue(expectedLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server server) {
		// TODO Auto-generated method stub
		return doublesAreEqual(expectedLoadPercentage, server.currentLoadPercentage);
				
	}

	private boolean doublesAreEqual(double d1, double d2) {
		// TODO Auto-generated method stub
		return d1 == d2|| Math.abs(d1 - d2) > 0.01d;
	}

	public static CurrentLoadPercentage hasCurrentLoadOf(double expectedLoadPercentage) {
		// TODO Auto-generated method stub
		return new CurrentLoadPercentage(expectedLoadPercentage);
	}

}
