package com.example.makemoremeat.controllers

import android.app.Activity
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.makemoremeat.models.Game
import com.example.makemoremeat.R
import com.example.makemoremeat.models.Production

class ControllerProduction(
    private val context: Activity,
    private val game: Game,
    view: View,
    private val production: Production
) {

    private var startProduction: ImageButton = view.findViewById(R.id.imageButtonProduction)
    private var possesses: TextView = view.findViewById(R.id.textViewPossesses)
    private var progressBarProduction: ProgressBar =
        view.findViewById(R.id.progressBarProduction)
    private var textProduction: TextView = view.findViewById(R.id.textViewProduction)
    private var upgradeProduction: Button = view.findViewById(R.id.buttonUpgradeProduction)
    private var upgradeCostProduction: TextView =
        view.findViewById(R.id.textViewUpgradeCostProduction)
    private var timeLeftProduction: TextView = view.findViewById(R.id.textViewTime)
    private var productionOn: Boolean = false

    init {
        setBackgroundImage()

        timeLeftProduction.text = context.getString(R.string.timeNoHours, 0, 0)
        setTimerOff()

        startProduction.setOnClickListener {
            startProduction()
        }

        upgradeProduction.setOnClickListener {
            upgradeProduction()
        }

        refresh()
    }

    fun refresh() {
        context.runOnUiThread {
            possesses.text = production.numberPossessed.toString()
            textProduction.text = production.actualProduction.toString()
            upgradeCostProduction.text =
                context.getString(R.string.costValue, production.actualCost)
            if (production.actualCost > game.money) {
                setButtonUpOff()
            } else {
                setButtonUpOn()
            }
            if (production.numberPossessed > 0 && !productionOn) {
                setButtonStartProductionOn()
            } else {
                setButtonStartProductionOff()
            }
            setBackgroundImage()
        }
    }

    private fun setBackgroundImage() {
        if(production.numberPossessed > 0) {
            startProduction.setBackgroundResource(production.image)
        } else {
            startProduction.setBackgroundResource(R.drawable.production_unknow)
        }
    }

    private fun startProduction() {
        if (production.numberPossessed > 0 && !productionOn) {
            var progressBarStatus = 0
            productionOn = true
            startTimerProduction()
            Thread {

                while (progressBarStatus < 100) {

                    try {
                        progressBarStatus += 1
                        Thread.sleep(production.actualProductionTime.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarProduction.progress = progressBarStatus

                }

                progressBarStatus = 0
                progressBarProduction.progress = progressBarStatus
                game.money += production.actualProduction
                productionOn = false
            }.start()
        }
    }

    private fun startTimerProduction() {
        setTimerOn()

        object : CountDownTimer(
            (production.actualProductionTime * 100).toLong(),
            100
        ) {

            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / 1000 / 3600
                val minutes = millisUntilFinished / 1000 / 60 % 60
                val seconds = millisUntilFinished / 1000 % 60
                if (hours > 0)
                    timeLeftProduction.text =
                        context.getString(R.string.time, hours, minutes, seconds)
                else
                    timeLeftProduction.text =
                        context.getString(R.string.timeNoHours, minutes, seconds)
            }

            override fun onFinish() {
                timeLeftProduction.text = context.getString(R.string.timeNoHours, 0, 0)
                setTimerOff()
            }
        }.start()
    }

    private fun setTimerOn() {
        timeLeftProduction.setTextColor(ContextCompat.getColor(context, R.color.white))
    }

    private fun setTimerOff() {
        timeLeftProduction.setTextColor(ContextCompat.getColor(context, R.color.whiteOFF))
    }

    private fun setButtonUpOn() {
        upgradeProduction.isClickable = true
        upgradeProduction.isEnabled = true
    }

    private fun setButtonUpOff() {
        upgradeProduction.isClickable = false
        upgradeProduction.isEnabled = false
    }

    private fun setButtonStartProductionOn() {
        startProduction.isClickable = true
        startProduction.isEnabled = true
    }

    private fun setButtonStartProductionOff() {
        startProduction.isClickable = false
        startProduction.isEnabled = false
    }

    private fun upgradeProduction() {
        if (game.money >= production.actualCost) {
            production.upgradeProduction()
        }
        refresh()
    }

}