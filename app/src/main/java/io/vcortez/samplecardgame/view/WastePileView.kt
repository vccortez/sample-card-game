package io.vcortez.samplecardgame.view

import android.content.Context
import android.view.ViewManager
import io.vcortez.samplecardgame.*
import org.jetbrains.anko._FrameLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageView

class WastePileView(context: Context, val isPlayer: Boolean) : _FrameLayout(context) {
  init {
    render()
  }

  fun update() {
    removeAllViews()
    render()
  }

  private fun render() {
    val cards = with(GameModel) { if (isPlayer) playerWaste else enemyWaste }

    if (cards.isEmpty())
      imageView { imageResource = emptyPileDrawable }.lparams(context.cardWidth, context.cardHeight) { rotation = 0f }
    else
      cards.forEach { card ->
        imageView {
          imageResource = getResIdForCard(card)
        }.lparams(context.cardWidth, context.cardHeight) {
          rotation = randInt(-15, 15).toFloat()
        }
      }
  }
}

fun ViewManager.wastePileView(player: Boolean = true, init: WastePileView.() -> Unit = {}) =
  ankoView({ WastePileView(it, player) }, 0, init)