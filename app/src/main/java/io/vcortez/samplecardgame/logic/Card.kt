package io.vcortez.samplecardgame

import io.vcortez.samplecardgame.tardigrade.deck.iCard

class Card(value: Int, suit: String, var faceUp: Boolean = false) : TardigradeCard(), Comparable<Card> {
  val value: Int by lazy { value.coerceIn(0..12) }
  val suit: String by lazy { if (suit in suits) suit else clubs }

  override fun compareTo(other: Card): Int {
    return value - other.value
  }

  override fun toString(): String = if (faceUp) {
    "${cardsMap[value]}".padEnd(2) + getSuitChar(suit)
  } else {
    "xxx"
  }
}

open class TardigradeCard : iCard {
  override fun setId(id: String?) = Unit

  override fun getId(): String = TODO("not implemented")

  override fun setName(name: String?) = Unit

  override fun getName(): String = TODO("not implemented")

  override fun setDescription(description: String?) = Unit

  override fun getDescription(): String = TODO("not implemented")

  override fun setAttribute(label: String?, value: String?) = Unit

  override fun getAttribute(label: String?): String = TODO("not implemented")

  override fun execute() = Unit

  override fun revert() = Unit
}