package com.example.makemoremeat.controllers

import android.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.makemoremeat.models.Game
import com.example.makemoremeat.R

class ControllerHeader(private val game: Game, private val viewHeader: View) {

    private var money: TextView = viewHeader.findViewById(R.id.textViewMoney)
    private var moneyPerSeconde: TextView = viewHeader.findViewById(R.id.textViewMoneyPerSecond)
    private var buttonX1: Button = viewHeader.findViewById(R.id.buttonX1)
    private var buttonX10: Button = viewHeader.findViewById(R.id.buttonX10)
    private var buttonX25: Button = viewHeader.findViewById(R.id.buttonX25)
    private var buttonXMax: Button = viewHeader.findViewById(R.id.buttonXMax)
    private var settings: ImageButton = viewHeader.findViewById(R.id.imageButtonSettings)

    init{
        settings.setOnClickListener {
            hardReset()
        }
    }

    private fun hardReset() {
        val builder = AlertDialog.Builder(viewHeader.context)
        builder.setMessage("Are you sure you want to Reset?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                // Delete selected note from database
                game.hardReset()
            }
            .setNegativeButton("No") { dialog, _ ->
                // Dismiss the dialog
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    fun refresh() {
        money.text = game.money.toString()
        moneyPerSeconde.text = game.moneyPerSecond.toString()
        when (game.fastUP) {
            2 -> {
                buttonX1.isClickable = true
                buttonX10.isClickable = false
                buttonX25.isClickable = true
                buttonXMax.isClickable = true
            }
            3 -> {
                buttonX1.isClickable = true
                buttonX10.isClickable = true
                buttonX25.isClickable = false
                buttonXMax.isClickable = true
            }
            4 -> {
                buttonX1.isClickable = true
                buttonX10.isClickable = true
                buttonX25.isClickable = true
                buttonXMax.isClickable = false
            }
            else -> {
                buttonX1.isClickable = false
                buttonX10.isClickable = true
                buttonX25.isClickable = true
                buttonXMax.isClickable = true
            }
        }
    }

}