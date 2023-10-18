package com.example.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(private val beatBox: BeatBox): BaseObservable(){

    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name


    fun onButtonClicked(){
        sound?.let{
            beatBox.play(it)
        }
    }

}