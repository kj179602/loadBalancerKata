package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasAVmsCountOf;
import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().withSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("the server should contains vm", theServer.contains(theVm));
	}

	@Test 
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent(){
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().withSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(10.0d));
		assertThat("the server should contains vm", theServer.contains(theVm));
		
	}
	
	@Test
	public void balancingTheServerWithEnoughRoom_fillsTheServerWithAllVms(){
		Server theServer = a(server().withCapacity(100));
		Vm firstVm = a(vm().withSize(1));
		Vm secondVm = a(vm().withSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(firstVm, secondVm));

		assertThat(theServer, hasAVmsCountOf(2));
		assertThat("the server should contains first vm", theServer.contains(firstVm)); 
		assertThat("the server should contains second vm", theServer.contains(secondVm)); 
	}
	
	@Test
	public void vmShouldBeBalancedOnLessLoadedServerFirst(){
		Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoad(50.0d));
		Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoad(45.0d));
		Vm theVm = a(vm().withSize(10)); 
		
		balance(aListOfServersWith(moreLoadedServer, lessLoadedServer), aListOfVmsWith(theVm));
		
		assertThat("less loaded server should contains the vm", lessLoadedServer.contains(theVm)); 
		assertThat("more loaded server should not contain the vm", !moreLoadedServer.contains(theVm)); 
	}

	@Test
	public void balancingServerWithNotEnoughRoom_shouldNotBeFilledWithTheVm(){
		Server theServer = a(server().withCapacity(10).withCurrentLoad(90.0d));
		Vm theVm = a(vm().withSize(2));
		
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));
		
		assertThat("server should not contain the vm", !theServer.contains(theVm));
	}
	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] aListOfVmsWith(Vm... vms) {
		return vms ;
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server... servers) {
		return servers;
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}
}
