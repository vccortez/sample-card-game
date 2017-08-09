package com.tardigrade.comunication;

public interface iChannel {
	String getName();
	String getAddress();
	void send(iPack pack);

	int getPort();
}
