package io.vcortez.samplecardgame.tardigrade.comunication;

import io.vcortez.samplecardgame.tardigrade.utils.iCallback;

public interface iManager {
	void registerObserver(iChannel channel);
	void removeObserver(iChannel channel);
	void notifyObserver(iPack pack);

	void update(iPack pack);

	void setOnObserverListChangeListener(iCallback callback);
}