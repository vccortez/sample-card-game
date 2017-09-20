package io.vcortez.samplecardgame.tardigrade.comunication;

import io.vcortez.samplecardgame.tardigrade.utils.iCallback;

public interface iNetwork {
	void start();
	void stop();
	int getPort();
	
	void setOnReceiveListener(iCallback callback);
	boolean isWorking();
}
