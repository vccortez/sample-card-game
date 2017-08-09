package com.tardigrade.comunication;

import com.tardigrade.utils.iCallback;

public interface iDiscovery {
	void init(iNetwork channel);
	
	boolean start();
	void stop();

	void setOnStartListener(iCallback callback);
	void setOnFoundListener(iCallback callback);
	void setOnLostListener(iCallback callback);
	void setOnStopListener(iCallback callback);
	
	boolean isWorking();
}