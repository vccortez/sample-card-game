package io.vcortez.samplecardgame

import android.content.Context
import android.support.v7.app.AlertDialog

fun Context.createDialog(cancelable: Boolean = false, cancelableTouchOutside: Boolean = false, builderFunction: AlertDialog.Builder.() -> Any): AlertDialog {
  val builder = AlertDialog.Builder(this)
  builder.builderFunction()
  val dialog = builder.create();

  dialog.setCancelable(cancelable)
  dialog.setCanceledOnTouchOutside(cancelableTouchOutside)
  return dialog
}

fun AlertDialog.Builder.positiveButton(text: String = "OK", handleClick: (i: Int) -> Unit = {}) {
  this.setPositiveButton(text, { dialogInterface, i -> handleClick(i) })
}

fun AlertDialog.Builder.negativeButton(text: String = "CANCEL", handleClick: (i: Int) -> Unit = {}) {
  this.setNegativeButton(text, { dialogInterface, i -> handleClick(i) })
}

fun AlertDialog.Builder.neutralButton(text: String, handleClick: (i: Int) -> Unit = {}) {
  this.setNeutralButton(text, { dialogInterface, i -> handleClick(i) })
}