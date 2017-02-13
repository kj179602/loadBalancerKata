package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

	private int capacity;
	
	public ServerBuilder withCapacity(int capacity) {
		// TODO Auto-generated method stub
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		// TODO Auto-generated method stub
		return new Server();
	}
	
	public static ServerBuilder server() {
		// TODO Auto-generated method stub
		return new ServerBuilder();
	}
}
