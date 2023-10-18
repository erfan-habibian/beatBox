package com.example.beatbox

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beatbox.databinding.ActivityMainBinding
import com.example.beatbox.databinding.ButtonLayoutBinding


class MainActivity : ComponentActivity() {
    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        beatBox = BeatBox(assets)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }




    }

    private inner class SoundHolder(private val binding: ButtonLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
            init {
//                binding.viewModel = SoundViewModel(beatBox)
                binding.viewModel = SoundViewModel(beatBox)

            }

        fun bind(sound:Sound){
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }

    }

    private inner class SoundAdapter(private val sounds : List<Sound>): RecyclerView.Adapter<SoundHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val view = DataBindingUtil.inflate<ButtonLayoutBinding>(layoutInflater, R.layout.button_layout, parent
            , false)
            return SoundHolder(view)
        }

        override fun getItemCount(): Int {
            return sounds.size
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

    }

}
