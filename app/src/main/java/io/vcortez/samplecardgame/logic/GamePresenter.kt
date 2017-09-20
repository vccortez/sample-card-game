package io.vcortez.samplecardgame

import android.view.View
import io.vcortez.gamepad.GamepadEvent
import io.vcortez.gamepad.GamepadViewHolder
import io.vcortez.gamepad.Input
import io.vcortez.gamepad.ToggleButton

object GamePresenter : GamepadViewHolder {
  override val instance: View by lazy { GameModel.currentView!! }

  var view: GameUI? = null

  fun setGameView(gameUI: GameUI) {
    view = gameUI
  }

  fun onPlayerMove(cardIndex: Int) {
    GameModel.onPlayerStageSelect(cardIndex)

    view?.update()
  }

  override fun onGamepadEvent(event: GamepadEvent) {
    if (event.action != GamepadEvent.ACTION_DOWN)
      return

    when (event.code) {
      GameModel.btnA!!.code -> GameModel.onPowerUp(true)//GameModel.redPowerUp = (GameModel.redPowerUp + 1).coerceIn(1..3)
      GameModel.btnB!!.code -> GameModel.onPowerUp(false)//GameModel.blackPowerUp = (GameModel.blackPowerUp + 1).coerceIn(1..3)
    }

    view?.update()
  }
}