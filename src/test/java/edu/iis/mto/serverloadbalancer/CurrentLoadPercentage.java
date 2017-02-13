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
		return expectedLoadPercentage == server.currentLoadPercentage 
				|| Math.abs(expectedLoadPercentage - server.currentLoadPercentage) > 0.01d;
	}


}
