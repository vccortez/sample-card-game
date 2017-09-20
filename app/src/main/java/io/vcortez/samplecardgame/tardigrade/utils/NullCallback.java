package io.vcortez.samplecardgame.tardigrade.utils;

import io.vcortez.samplecardgame.tardigrade.comunication.iPack;

public class NullCallback implements iCallback {
	@Override
	public void callingBack(iPack pack) {
		return;
	}
}
