package edu.iis.mto.serverloadbalancer;


public class Server {

	public double currentLoadPecentage;
	public int capacity;
	
	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return true;
	}

}
