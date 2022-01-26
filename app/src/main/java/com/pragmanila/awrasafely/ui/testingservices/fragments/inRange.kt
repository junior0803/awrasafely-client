package com.pragmanila.awrasafely.ui.testingservices.fragments

import android.text.InputFilter
import java.lang.Exception

class inRange {
    private fun inRange(min: Int, max: Int): InputFilter? {
        return InputFilter { source, start1, end, dest, dstart, dend ->
            try {
                val input = (dest.toString() + source.toString()).toInt()
                return@InputFilter if (input < min || input > max) {
                    ""
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@InputFilter null
            }
        }
    }
}