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

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithOneVm(){
		
		Server theServer = a(ServerBuilder.server().withCapacity(1));
		
		Vm theVm = a(vm().withSize(1));
		balancing(aServerListWith(theServer), aVmListWith(theVm));
		
		assertThat(theServer, CurrentLoadPercentage.hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
		
	}
	private Vm a(VmBuilder builder) {
		// TODO Auto-generated method stub
		return builder.build();
	}

	private VmBuilder vm() {
		// TODO Auto-generated method stub
		return new VmBuilder();
	}

	private Vm[] aVmListWith(Vm... vms) {
		// TODO Auto-generated method stub
		return vms;
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
