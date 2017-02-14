package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAXIMUM_LOAD = 100.0d;
	private double currentLoadPecentage;
	private int capacity;
	
	private List<Vm> vms = new ArrayList<Vm>();
	
	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}


	public double getCurrentLoadPecentage() {
		return currentLoadPecentage;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public void addVm(Vm vm) {
		currentLoadPecentage += loadOfVm(vm);
		this.vms.add(vm);
	}

	protected double loadOfVm(Vm vm) {
		return (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
	}

	public int countVms() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {
		return currentLoadPecentage + (loadOfVm(vm)) <= MAXIMUM_LOAD;
	}


	private int getCapacity() {
		return capacity;
	}



}
