package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoadPecentage;
	public int capacity;
	
	private List<Vm> vms = new ArrayList<Vm>();
	
	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public void addVm(Vm vm) {
		currentLoadPecentage = (double) vm.size / (double) capacity * MAXIMUM_LOAD;
		this.vms.add(vm);
	}

	public int countVms() {
		// TODO Auto-generated method stub
		return vms.size();
	}

}
