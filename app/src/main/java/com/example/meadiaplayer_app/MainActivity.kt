package com.example.meadiaplayer_app

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    lateinit var mediaplayer : MediaPlayer
    var totalTime : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val play = findViewById<ImageView>(R.id.imageView2)
        val pause = findViewById<ImageView>(R.id.imageView3)
        val stop = findViewById<ImageView>(R.id.imageView4)
        val seekbarMusic = findViewById<SeekBar>(R.id.seekBar)

        mediaplayer = MediaPlayer.create(this,R.raw.music)
        mediaplayer.setVolume(1f,1f)
        totalTime = mediaplayer.duration



        play.setOnClickListener{
            mediaplayer.start()
        }
        pause.setOnClickListener{
            mediaplayer.pause()
        }

        stop.setOnClickListener{
            mediaplayer?.stop()
            mediaplayer?.reset()
            mediaplayer?.release()

        }
        seekbarMusic.max = totalTime
        seekbarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
               if (fromUser){
                   mediaplayer.seekTo(progress)
               }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        // change seekbar position based on music
        val handler = Handler()
        handler.postDelayed( object : Runnable{
            override fun run(){
                seekbarMusic.progress = mediaplayer.currentPosition
                handler.postDelayed(this,1000)
            }
        },0)

    }


    }
