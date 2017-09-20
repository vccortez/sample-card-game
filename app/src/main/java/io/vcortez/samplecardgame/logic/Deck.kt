package io.vcortez.samplecardgame

import io.vcortez.samplecardgame.tardigrade.deck.iCard
import io.vcortez.samplecardgame.tardigrade.deck.iDeck
import io.vcortez.samplecardgame.tardigrade.utils.iCallback
import java.util.*

class Deck : TardigradeDeck() {
  val cards = Array(52, { Card(it % 13, getSuit(it)) })

  var cardsInDeck: MutableList<Card> = cards.toMutableList()

  private fun getSuit(i: Int) = when (i / 13) {
    0 -> clubs
    1 -> diamonds
    2 -> hearts
    else -> spades
  }

  fun drawCard(): Card = cardsInDeck.removeAt(0).also { it.faceUp = true }

  fun reset() {
    cardsInDeck = cards.toMutableList().also { it.forEach { it.faceUp = false } }
    Collections.shuffle(cardsInDeck)
  }
}

open class TardigradeDeck : iDeck {
  override fun init() = Unit

  override fun loadDeck() = Unit

  override fun shuffleDeck() = Unit

  override fun putCard(card: iCard?) = Unit

  override fun getCard(id: String?): iCard = TODO("not implemented")

  override fun getAllCards(): MutableList<iCard> = mutableListOf()

  override fun useCard(card: iCard?) = Unit

  override fun setOnLoadListener(callback: iCallback?) = Unit

  override fun setOnUseCardListener(callback: iCallback?) = Unit
}