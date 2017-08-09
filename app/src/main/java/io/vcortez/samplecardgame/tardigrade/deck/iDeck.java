package com.tardigrade.deck;

import com.tardigrade.utils.iCallback;

import java.io.IOException;
import java.util.List;

public interface iDeck{
	void init();
	
	void loadDeck() throws IOException;
	void shuffleDeck();
	
	void putCard(iCard card);
	iCard getCard(String id);
	List<iCard> getAllCards();
	
	void useCard(iCard card);

	void setOnLoadListener(iCallback callback);
	void setOnUseCardListener(iCallback callback);
}
