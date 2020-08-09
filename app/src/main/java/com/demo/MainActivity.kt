package com.demo

import android.app.ProgressDialog
import android.graphics.PixelFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val pd by lazy { ProgressDialog(this) }
   // val url="https://aajtaklive-amd.akamaized.net/hls/live/2014416/aajtak/aajtaklive/aajtak_2/chunklist.m3u8"
   // val url="https://zmcl-live.akamaized.net/zeenewsclean_mbr/tracks-v3a1/mono.m3u8"
  //  val url="https://nicls1-lh.akamaihd.net/i/ddnews_1@409133/index_2_av-p.m3u8?sd=10&rebase=on"
    //val url="https://abp-i.akamaihd.net/hls/live/765529/abphindi/master_234.m3u8"
    val url="https://ndtv24x7elemarchana.akamaized.net/hls/live/2003678/ndtv24x7/masterp_480p@3.m3u8"
  //  val url="https://news18bihar-lh.akamaihd.net/i/n18biharjh_1@175736/index_1_av-p.m3u8?sd=10&rebase=on&hdntl=exp=1596621500~acl=%2f*~data=hdntl~hmac=c523a691c17f52b4ae4203faa42cd60f51fa2dd12aa7907d019bbfbd972856f9"
   // val url="https://5b44cf20b0388.streamlock.net:8443/live/ngrp:live_all/playlist.m3u8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)

        pd.setMessage("Buffering...")
        pd.setCancelable(true)
        playVideo()
    }

    private fun playVideo() {
        try {

            window.setFormat(PixelFormat.TRANSLUCENT)
            val mediaController=MediaController(this)
            mediaController.setAnchorView(videoView)

            videoView.apply {
                setMediaController(mediaController)
                setVideoURI(Uri.parse(url))
                requestFocus()
            }.setOnPreparedListener {
                pd.dismiss()
                videoView.start()
            }


        }catch (e:Exception){
            pd.dismiss()
        }

    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }


    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}