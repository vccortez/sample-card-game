package com.tardigrade.comunication;

import com.tardigrade.utils.iCallback;

public interface iHub {
	void start();
	void stop();

	boolean isConnected();

	void setOnChangeListener(iCallback callback);
	void setOnAwareListener(iCallback callback);
}