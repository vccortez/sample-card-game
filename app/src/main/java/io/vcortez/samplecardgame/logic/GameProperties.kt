package io.vcortez.samplecardgame

import java.security.SecureRandom

val clubs = "Clubs"
val diamonds = "Diamonds"
val hearts = "Hearts"
val spades = "Spades"

val suits = arrayOf(clubs, diamonds, hearts, spades)

val redSuits = arrayOf(diamonds, hearts)
val blackSuits = arrayOf(clubs, spades)

val cardsMap = (0..12).associate {
  when (it) {
    0 -> Pair(it, "A")
    in 1..9 -> Pair(it, "${it + 1}")
    10 -> Pair(it, "J")
    11 -> Pair(it, "Q")
    else -> Pair(it, "K")
  }
}

val suitsMap = mapOf<String, Int>(clubs to 0, diamonds to 1, hearts to 2, spades to 3)

fun getSuitChar(suit: String): String = when (suit) {
  diamonds -> "\u2666"
  clubs -> "\u2663"
  hearts -> "\u2665"
  spades -> "\u2660"
  else -> "Incorrect suit"
}

fun randInt(from: Int = 0, to: Int): Int {
  return SecureRandom().nextInt(to - from) + from
}