package com.mdadil2019.videoplayer

import android.Manifest
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mdadil2019.videoplayer.databinding.ActivityMainBinding
import com.mdadil2019.videoplayer.viewmodel.VideoPlayerViewModel
import com.mdadil2019.videoplayer.viewmodel.VideoPlayerViewModelFactory

class VideoPlayerActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: VideoPlayerViewModel

    private val EXTERNAL_STORAGE_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main)
        checkPermission()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == EXTERNAL_STORAGE_REQUEST_CODE &&
            grantResults.first() == PackageManager.PERMISSION_GRANTED) onReady()
    }

    private fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED){
            onReady()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),EXTERNAL_STORAGE_REQUEST_CODE)
        }
    }

    private fun onReady(){
        viewModel = ViewModelProviders.of(this,
            VideoPlayerViewModelFactory(
                application
            )
        ).get(
            VideoPlayerViewModel::class.java)
        binding.vm = viewModel
        viewModel.setAdapter(binding.rvVideoPlayer)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}
