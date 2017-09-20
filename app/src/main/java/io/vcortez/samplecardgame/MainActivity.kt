package io.vcortez.samplecardgame

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import io.vcortez.gamepad.*
import io.vcortez.samplecardgame.view.StageView
import io.vcortez.samplecardgame.view.WastePileView
import io.vcortez.samplecardgame.view.stageView
import io.vcortez.samplecardgame.view.wastePileView
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), GameUI {
  var enemyDeckView: DeckView? = null
  var playerDeckView: DeckView? = null

  var enemyWasteView: WastePileView? = null
  var playerWasteView: WastePileView? = null

  var enemyStageView: StageView? = null
  var playerStageView: StageView? = null

  var scoreView: TextView? = null
  var powerView: TextView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    requestWindowFeature(Window.FEATURE_NO_TITLE)
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

    verticalLayout {
      leftPadding = dip(4)
      rightPadding = dip(4)
      topPadding = dip(8)

      scoreView = textView("Score: player 0 | 0 opponent") {
        textSize = px2dip(32)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
      }

      powerView = textView("red 1 x 1 black") {
        textSize = px2dip(32)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
      }

      linearLayout {
        enemyDeckView = deckView(false).lparams(cardWidth, cardHeight)
        view().lparams(cardWidth, 0)
        enemyStageView = stageView(false).lparams(matchParent, cardHeight)
      }.lparams {
        topMargin = cardHeight / 2
        gravity = Gravity.CENTER_HORIZONTAL
      }

      linearLayout {
        playerWasteView = wastePileView().lparams { margin = dip(8) }
        view().lparams(cardWidth, 0)
        enemyWasteView = wastePileView(false).lparams { margin = dip(8) }
      }.lparams {
        topMargin = cardHeight / 2
        gravity = Gravity.CENTER_HORIZONTAL
      }

      linearLayout {
        playerDeckView = deckView().lparams(cardWidth, cardHeight)
        view().lparams(cardWidth, 0)
        playerStageView = stageView().lparams(matchParent, cardHeight)
      }.lparams {
        topMargin = cardHeight / 2
        gravity = Gravity.CENTER_HORIZONTAL
      }
    }

    // println("CONTENT VIEW $contentView")

    GameModel.currentView = contentView
    GamePresenter.setGameView(this)
    GameModel.resetGame()

    GameModel.victoryDialog = this.createDialog {
      setTitle("Congratulations, you won!")
      positiveButton("OK", { restart() })
    }

    GameModel.defeatDialog = this.createDialog {
      setTitle("Sorry, you lost!")
      negativeButton("OK", { restart() })
    }

    GamePresenter.gamepad {
      GameModel.btnA = toggleButton {
        accelerometer("a")

        handler = { (_, bnd) ->
          val ac = bnd.getBundle("a")

          ac.getFloat(Accelerometer.Y_AXIS.first) > Math.max(ac.getFloat(Accelerometer.X_AXIS.first), ac.getFloat(Accelerometer.Z_AXIS.first))
        }
      }

      GameModel.btnB = toggleButton {
        proximity("a", { data ->
          when (data) {
            is Proximity.Static.Data.Something -> Bundle().apply { putBoolean("value", true) }
            else -> Bundle().apply { putBoolean("value", false) }
          }
        })

        handler = { (_, bnd) ->
          val pr = bnd.getBundle("a")

          pr.getBoolean("value")
        }
      }
    }
  }

  override fun update() {
    enemyDeckView?.update()
    enemyStageView?.update()
    enemyWasteView?.update()

    playerDeckView?.update()
    playerStageView?.update()
    playerWasteView?.update()

    scoreView!!.text = "Score: player ${GameModel.score.first} | ${GameModel.score.second} opponent"
    powerView!!.text = "red ${GameModel.redPowerUp} x ${GameModel.blackPowerUp} black"
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menu.add("Restart")
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    restart()
    return true
  }

  private fun restart() {
    GameModel.resetGame()
    update()
  }
}