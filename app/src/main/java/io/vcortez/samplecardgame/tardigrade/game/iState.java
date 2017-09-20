package io.vcortez.samplecardgame.tardigrade.game;

import io.vcortez.samplecardgame.tardigrade.utils.iCallback;

import java.util.List;

public interface iState{
	void setOnMakeListener(iCallback callback);
	void setOnChangeListener(iCallback callback);
	void setOnUpdateListener(iCallback callback);

	interface iTuple {
		void append(Object object);
		Integer getOperation();
		List<Object> retrieve();
	}
}
