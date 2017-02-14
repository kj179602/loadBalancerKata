package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {

		for(Vm vm : vms){
			addToLessLoadedServer(servers, vm);
		}
	}

	protected void addToLessLoadedServer(Server[] servers, Vm vm) {
		List<Server> listOfCapableServers = findCapableServers(servers, vm);
		Server lessLoadedServer = findLessLoadedServer(listOfCapableServers);
		if(lessLoadedServer != null){
			lessLoadedServer.addVm(vm);
		}
		
	}

	protected List<Server> findCapableServers(Server[] servers, Vm vm) {
		List<Server> listOfCapableServers = new ArrayList<Server>();
		for(Server server : servers){
			if(server.canFit(vm)){
				listOfCapableServers.add(server);
			}
		}
		return listOfCapableServers;
	}

	protected Server findLessLoadedServer(List<Server> listOfCapableServers) {
		Server lessLoadedServer = null;
		for(Server server : listOfCapableServers){
			if(lessLoadedServer == null || server.getCurrentLoadPecentage() < lessLoadedServer.getCurrentLoadPecentage()){
				lessLoadedServer = server;
			}
		}
		return lessLoadedServer;
	}

}
