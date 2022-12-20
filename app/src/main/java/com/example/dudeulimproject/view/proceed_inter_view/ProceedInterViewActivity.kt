package com.example.dudeulimproject.view.proceed_inter_view

import android.Manifest
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityProceedInterViewBinding
import com.example.dudeulimproject.view.chat.ChatActivity
import com.example.dudeulimproject.view.map.MapActivity
import com.example.dudeulimproject.view.proceed_inter_view.adapter.RecordInterViewAdapter
import com.example.dudeulimproject.view.proceed_inter_view.viewmodel.ProceedInterViewViewModel
import com.example.dudeulimproject.view.voice_chat.VoiceChatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ProceedInterViewActivity : BaseActivity<ActivityProceedInterViewBinding>(R.layout.activity_proceed_inter_view) {
    private val viewModel: ProceedInterViewViewModel by viewModels()
    private var downloadManager : DownloadManager? = null
    private var downloadId : Long? = null
    private var mediaRecorder : MediaRecorder? = null
    private var mediaPlayer : MediaPlayer? = null
    private var audioFileName : String = ""
    private var isPlaying = false
    private var positionOfPlayingAudio = 0;
    private var isRecording = false
    private val playRecord = { position: Int, uri: String, imageView: ImageView ->
        if(!isPlaying){
            imageView.setImageResource(R.drawable.stop_button)
            positionOfPlayingAudio = position
            mediaPlayer = MediaPlayer()
            try {
                mediaPlayer!!.setDataSource(File(uri).absolutePath)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            isPlaying = true
            mediaPlayer!!.setOnCompletionListener {
                stopAudio()
                imageView.setImageResource(R.drawable.play_button)
            }
            true
        }else {
            if(positionOfPlayingAudio == position){
                stopAudio()
                imageView.setImageResource(R.drawable.play_button)
                true
            }else {
                Toast.makeText(this, "오디오를 재생 중 입니다.", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }


    private val activityResult = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (!isGranted) {
            Toast.makeText(this, "권한을 얻어오지 못했습니다", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
        binding.recyclerRequestInterViewRecord.adapter = RecordInterViewAdapter(playRecord)
        viewModel.checkRequester(intent.getBooleanExtra("isRequester", false))
        viewModel.getRequestInterView(intent.getStringExtra("proceedInterViewId")!!)
        bindUserName()
    }
    private fun bindUserName() {
        binding.textProceedInterViewName.text = if(viewModel.isRequester.value!!){
            viewModel.interView.value!!.publisher.name
        }else {
            viewModel.interView.value!!.requester.name
        }
    }

    fun clickBackButton(view: View){
        finish()
    }

    fun clickChatButton(view: View) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("roomId", viewModel.interView.value!!.id)
        if(viewModel.isRequester.value!!){
            intent.putExtra("userId", viewModel.interView.value!!.requester.id)
        }else {
            intent.putExtra("userId", viewModel.interView.value!!.publisher.id)
        }
        startActivity(intent)
    }
    fun clickPlaceButton(view: View){
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("place", viewModel.interView.value!!.place)
        startActivity(intent)
    }

    fun clickVoiceChatButton(view: View) {
        val intent = Intent(this, VoiceChatActivity::class.java)
        intent.putExtra("roomId", "8gpw-cm18-pt25")
        if(viewModel.isRequester.value!!){
            intent.putExtra("userName", viewModel.interView.value!!.requester.name)
        }else {
            intent.putExtra("userName", viewModel.interView.value!!.publisher.name)
        }
        startActivity(intent)
    }


    fun clickRecordButton(view: View) {
        checkPermission()
        if(!isRecording){
            startRecording()
        }else {
            stopRecording()
        }
    }
    private fun startRecording() {
        isRecording = true
        binding.imageRequestInterviewRecordIcon.setImageResource(R.drawable.record_stop)
        binding.cardRequestInterViewRecordButton.setCardBackgroundColor(ContextCompat.getColor(this,R.color.blue_700))

        val recordPath = getExternalFilesDir("/")!!.absolutePath
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        audioFileName = "$recordPath/DDL${viewModel.interView.value!!.publisher.name}_${timeStamp}.mp3"

        mediaRecorder = MediaRecorder()
        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder!!.setOutputFile(audioFileName)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

        try {
            mediaRecorder!!.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaRecorder!!.start()
    }

    private fun stopRecording() {
        binding.imageRequestInterviewRecordIcon.setImageResource(R.drawable.inter_view_blue)
        binding.cardRequestInterViewRecordButton.setCardBackgroundColor(ContextCompat.getColor(this,R.color.white))

        mediaRecorder!!.stop()
        mediaRecorder!!.release()
        mediaRecorder = null
        viewModel.addAudio(audioFileName)
        isRecording = false
        Log.d(ContentValues.TAG, "stopRecording: ${viewModel.audioList[0]}")
    }

    private fun stopAudio() {
        isPlaying = false
        mediaPlayer!!.stop()
    }

    private fun checkPermission(){
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        if(permission == PackageManager.PERMISSION_DENIED){
            activityResult.launch(Manifest.permission.RECORD_AUDIO)
        }
    }
}