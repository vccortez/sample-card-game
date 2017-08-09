package com.tardigrade.comunication;

import com.tardigrade.utils.iCallback;

public interface iManager {
	void registerObserver(iChannel channel);
	void removeObserver(iChannel channel);
	void notifyObserver(iPack pack);

	void update(iPack pack);

	void setOnObserverListChangeListener(iCallback callback);
}