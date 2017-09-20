package io.vcortez.samplecardgame

import android.content.Context
import android.view.ViewManager
import android.widget.ImageView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk25.coroutines.onClick

class DeckView(context: Context, val isPlayer: Boolean) : ImageView(context) {
  init {
    imageResource = cardBackDrawable
  }

  fun update() {
    val cards = GameModel.playerDeck.cardsInDeck

    imageResource = if (cards.size > 0) cardBackDrawable else emptyPileDrawable
  }
}

fun ViewManager.deckView(player: Boolean = true, init: DeckView.() -> Unit = {}) =
  ankoView({ ctx: Context -> DeckView(ctx, player) }, 0, init)