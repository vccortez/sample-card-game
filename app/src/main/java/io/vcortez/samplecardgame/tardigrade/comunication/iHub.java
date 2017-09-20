package io.vcortez.samplecardgame.tardigrade.comunication;

import io.vcortez.samplecardgame.tardigrade.utils.iCallback;

public interface iHub {
	void start();
	void stop();

	boolean isConnected();

	void setOnChangeListener(iCallback callback);
	void setOnAwareListener(iCallback callback);
}