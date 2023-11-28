package com.wafflestudio.assignment5.utils

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

class ToneGenerator {

    private val sampleRate = 44100
    private val buffer = ShortArray(sampleRate)

    fun play(frequency: Double) {
        for (i in 0 until sampleRate) {
            buffer[i] =
                (Short.MAX_VALUE * sin(2.0 * PI * i.toDouble() / (sampleRate / frequency))).toInt()
                    .toShort()
        }

        val audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            sampleRate * 2,
            AudioTrack.MODE_STATIC
        )

        audioTrack.write(buffer, 0, sampleRate)

        CoroutineScope(Dispatchers.Main).launch {
            while (audioTrack.playState != AudioTrack.STATE_INITIALIZED) {
                delay(100L)
            }
            audioTrack.play()
            while (audioTrack.playState == AudioTrack.PLAYSTATE_PLAYING) {
                delay(1000L)
            }
            audioTrack.stop()
            audioTrack.release()
        }
    }
}