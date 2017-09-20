package io.vcortez.samplecardgame.view

import android.content.Context
import android.view.ViewManager
import io.vcortez.samplecardgame.*
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.imageView
import org.jetbrains.anko.sdk25.coroutines.onClick

class StageView(context: Context, val isPlayer: Boolean) : _LinearLayout(context) {
  init {
    addViews()
  }

  fun update() {
    removeAllViews()
    addViews()
  }

  private fun addViews() {
    val cards = with(GameModel) { if (isPlayer) playerStage else enemyStage }

    for (i in 0..GameModel.stageSize - 1) {
      val card = cards.elementAtOrNull(i)
      imageView {
        imageResource = if (card != null && card.faceUp && isPlayer) getResIdForCard(card) else cardBackDrawable
        onClick {
          GamePresenter.onPlayerMove(i)
        }
      }.lparams(context.cardWidth, context.cardHeight) {
        rightMargin = dip(8)
      }
    }
  }
}

fun ViewManager.stageView(player: Boolean = true, init: StageView.() -> Unit = {}) =
  ankoView({ StageView(it, player) }, 0, init)