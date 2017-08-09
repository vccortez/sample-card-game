package com.tardigrade.utils;

import com.tardigrade.comunication.iPack;

public class NullCallback implements iCallback {
	@Override
	public void callingBack(iPack pack) {
		return;
	}
}
