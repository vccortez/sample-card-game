package io.vcortez.samplecardgame

fun main(args: Array<String>) {
  GameModel.resetGame()
  GamePresenter.onPlayerMove(0)
  GameModel.debugPrint()
}