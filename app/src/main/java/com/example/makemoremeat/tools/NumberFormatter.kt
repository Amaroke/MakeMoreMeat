package com.example.makemoremeat.tools

import kotlin.math.ln
import kotlin.math.pow

class NumberFormatter {

    fun getFormattedNumber(count: Double): String {
        if (count < 10) return String.format("%.2f", count)
        else if (count < 1000) return String.format("%.1f", count)
        val exp = (ln(count) / ln(1000.0)).toInt()
        return String.format(
            "%.2f %c",
            count / 1000.0.pow(exp.toDouble()),
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"[exp - 1]
        )
    }
}