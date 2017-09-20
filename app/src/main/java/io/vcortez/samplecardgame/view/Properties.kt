package io.vcortez.samplecardgame

import android.content.Context
import android.view.View
import org.jetbrains.anko.dip
import org.jetbrains.anko.displayMetrics

val cardBackDrawable = R.drawable.cardback_green5
val emptyPileDrawable = R.drawable.cardback_blue1

fun View.getResIdForCard(card: Card): Int {
  val resName = "card${card.suit}${cardsMap[card.value]}".toLowerCase()
  return context.resources.getIdentifier(resName, "drawable", context.packageName)
}

val Context.cardWidth: Int
  get() = (displayMetrics.widthPixels - dip(8)) / 7
val Context.cardHeight: Int
  get() = cardWidth * 190 / 140