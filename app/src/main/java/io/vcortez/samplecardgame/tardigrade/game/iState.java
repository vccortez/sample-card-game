package com.tardigrade.game;

import com.tardigrade.utils.iCallback;
import com.well.components.Code;

import java.util.List;

public interface iState{
	void setOnMakeListener(iCallback callback);
	void setOnChangeListener(iCallback callback);
	void setOnUpdateListener(iCallback callback);

	interface iTuple {
		void append(Object object);
		Code getOperation();
		List<Object> retrieve();
	}
}
