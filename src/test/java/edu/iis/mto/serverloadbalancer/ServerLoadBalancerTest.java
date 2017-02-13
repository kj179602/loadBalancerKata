package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serverStayEmpty(){
		
		Server theServer = a(ServerBuilder.server().withCapacity(1));
		
		balancing(aServerListWith(theServer), anEmptyListOfVms());
		
		assertThat(theServer, CurrentLoadPercentage.hasCurrentLoadOf(0.0d));
	}

	
	private void balancing(Server[] servers, Vm[] vms) {
		// TODO Auto-generated method stub
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm [] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private Server a(ServerBuilder builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private Server [] aServerListWith(Server... servers) {
		// TODO Auto-generated method stub
		return servers;
	}


	
}
