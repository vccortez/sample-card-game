package com.tardigrade.comunication;

import com.tardigrade.utils.iCallback;

public interface iNetwork {
	void start();
	void stop();
	int getPort();
	
	void setOnReceiveListener(iCallback callback);
	boolean isWorking();
}
