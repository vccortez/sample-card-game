package com.tardigrade.deck;

public interface iCard {
	void setId(String id);
	String getId();
	void setName(String name);
	String getName();
    void setDescription(String description);
	String getDescription();

    void setAttribute(String label, String value);
    String getAttribute(String label);

    void execute();
	void revert();
}
