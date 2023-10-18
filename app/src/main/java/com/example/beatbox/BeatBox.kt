package com.example.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import java.io.IOException


private const val TAG = "BeatBox"
private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5
class BeatBox(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPools = SoundPool.Builder()
        .setMaxStreams(MAX_SOUNDS)
        .build()

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound>{
        val soundNames : Array<String>
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
        }catch (e: Exception){
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { fileName ->
            val assetPath = "$SOUNDS_FOLDER/$fileName"
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            }catch (e: IOException) {}
        }
        return sounds
    }

    private fun load( sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPools.load(afd,1)
        sound.soundId = soundId
    }

    fun play(sound: Sound){
        sound.soundId?.let {
            soundPools.play(it, 1.0f, 1.0f, 1, 1, 1.0f)
        }
    }

}