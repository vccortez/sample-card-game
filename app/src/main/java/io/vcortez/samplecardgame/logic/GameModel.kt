package io.vcortez.samplecardgame

import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import io.vcortez.gamepad.AnyInput
import java.util.*

object GameModel {
  val enemyDeck = Deck()
  val playerDeck = Deck()

  val enemyWaste = Stack<Card>()
  val playerWaste = Stack<Card>()

  val decks = arrayOf(enemyDeck, playerDeck)

  val enemyStage: MutableCollection<Card> = linkedSetOf()
  val playerStage: MutableCollection<Card> = linkedSetOf()

  val stages = arrayOf(enemyStage, playerStage)

  var currentView: View? = null
  var score = Pair(0, 0)
  var victoryDialog: AlertDialog? = null
  var defeatDialog: AlertDialog? = null

  var btnA: AnyInput? = null
  var btnB: AnyInput? = null

  var stageSize: Int = 3
  var redPowerUp: Int = 0
  var blackPowerUp: Int = 0

  fun resetGame() {
    score = Pair(0, 0)

    redPowerUp = 0
    blackPowerUp = 0

    enemyWaste.clear()
    playerWaste.clear()

    stages.forEach { it.clear() }
    decks.forEach { it.reset() }

    stages.forEachIndexed { i, stage ->
      val drawnCards: MutableList<Card> = Array(stageSize, { decks[i].drawCard() }).toMutableList()
      stage.addAll(drawnCards)
    }
  }

  fun onPlayerStageSelect(cardIndex: Int) {
    if (playerStage.size > cardIndex) {
      val playerCard = playerStage.elementAt(cardIndex)
      val enemyCard = enemyStage.elementAt(randInt(to = enemyStage.size))

      playCards(playerCard, enemyCard)

      playerStage.remove(playerCard)
      enemyStage.remove(enemyCard)

      enemyWaste.add(enemyCard)
      playerWaste.add(playerCard)

      isGameOver()
    }
  }

  private fun playCards(a: Card, b: Card) {
    Log.d(javaClass.simpleName, "Initial power: $a and $b")

    var va = a.value
    var vb = b.value

    if (a.suit in redSuits) { va += redPowerUp } else { va += blackPowerUp }
    if (b.suit in redSuits) { vb += redPowerUp } else { vb += blackPowerUp }

    val pa = Card(va, a.suit, true)
    val pb = Card(vb, b.suit, true)

    if (pa.value != a.value) if (pa.suit in redSuits) redPowerUp = 0 else blackPowerUp = 0
    if (pb.value != b.value) if (pb.suit in redSuits) redPowerUp = 0 else blackPowerUp = 0

    Log.d(javaClass.simpleName, "Final power: $pa and $pb")

    if (pa > pb)
      score = Pair(score.first + 1, score.second)
    else if (pa < pb)
      score = Pair(score.first, score.second + 1)

    Log.d(javaClass.simpleName, "Current score: $score")
  }

  private fun isGameOver() {
    if (playerDeck.cardsInDeck.isNotEmpty()) {
      stages.forEachIndexed { i, stage ->
        stage.add(decks[i].drawCard())
      }
    } else if (playerStage.isEmpty()) {
      if (score.first > score.second) victoryDialog?.show() else defeatDialog?.show()
    }
  }

  fun debugPrint() {
    println("Score: $score\n")

    var enemyLine = (if (enemyDeck.cardsInDeck.isEmpty()) "___" else "xxx").padEnd(12)

    enemyStage.forEach { enemyLine += it.toString().padEnd(6) }

    println(enemyLine.plus("\n"))

    var playerLine = (if (playerDeck.cardsInDeck.isEmpty()) "___" else "xxx").padEnd(12)

    playerStage.forEach { playerLine += it.toString().padEnd(6) }

    println(playerLine)
  }

  fun onPowerUp(red: Boolean) {
    if (red) redPowerUp = (redPowerUp + 1).coerceIn(0..3) else blackPowerUp = (blackPowerUp + 1).coerceIn(0..3)
  }
}