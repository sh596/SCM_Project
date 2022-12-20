package com.example.dudeulimproject.view.voice_chat


import android.Manifest
import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityVoiceChatBinding
import com.example.dudeulimproject.view.voice_chat.viewmodel.VoiceChatViewModel
import live.videosdk.rtc.android.Meeting
import live.videosdk.rtc.android.Participant
import live.videosdk.rtc.android.Stream
import live.videosdk.rtc.android.VideoSDK
import live.videosdk.rtc.android.lib.PeerConnectionUtils
import live.videosdk.rtc.android.listeners.MeetingEventListener
import live.videosdk.rtc.android.listeners.ParticipantEventListener
import org.webrtc.SurfaceViewRenderer
import org.webrtc.VideoTrack

class VoiceChatActivity : BaseActivity<ActivityVoiceChatBinding>(R.layout.activity_voice_chat) {
    private val viewModel: VoiceChatViewModel by viewModels()
    private var meeting: Meeting? = null
    private val sampleToken =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcGlrZXkiOiI2MWJhMDU1NS03YWU5LTQyYzgtYjE0OS00ZmQ1ZWZhNzgxYjEiLCJwZXJtaXNzaW9ucyI6WyJhbGxvd19qb2luIl0sImlhdCI6MTY3MTQ1NjEyOSwiZXhwIjoxNjc0MDQ4MTI5fQ.20JmczDqqHdl6p1yKLvFNpITZPv4DSR3KZ6kV7HfTHw"
    private var roomId = ""
    private lateinit var user: String
    private val participants: MutableList<Participant> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID)
        checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID)
        checkSelfPermission(REQUESTED_PERMISSIONS[2], PERMISSION_REQ_ID)

//        createMeeting(sampleToken)
        roomId = intent.getStringExtra("roomId").toString()
        user = intent.getStringExtra("userName")!!

        initCall()
    }

    /*private fun createMeeting(token: String) {
        AndroidNetworking.post("https://api.videosdk.live/v2/rooms")
            .addHeaders("Authorization", token) //we will pass the token in the Headers
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val meetingId = response.getString("roomId")

                        intent.putExtra("token", sampleToken)
                        intent.putExtra("meetingId", meetingId)
                        Log.d(TAG, "onResponse: $meetingId")
                        startActivity(intent)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(anError: ANError) {
                    anError.printStackTrace()
                    Toast.makeText(
                        binding.root.context, anError.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }*/
    private fun initCall() {
        VideoSDK.config(sampleToken)

        meeting = VideoSDK.initMeeting(
            this, roomId, user,
            viewModel.micEnabled.value!!, viewModel.webcamEnabled.value!!, false, null, null
        )

        binding.rendererVoiceChatLocal.init(PeerConnectionUtils.getEglContext(), null)
        binding.rendererVoiceChatRemote.init(PeerConnectionUtils.getEglContext(), null)
        initRenderer(
            binding.rendererVoiceChatLocal, meeting!!.localParticipant
        )
        meeting!!.addEventListener(meetingEventListener)
    }

    fun clickMicButton(view: View) {
        if(!(viewModel.isConnect.value!!)){
            return
        }
        if (viewModel.micEnabled.value!!) {
            meeting!!.muteMic()
        } else {
            meeting!!.unmuteMic()
        }
        viewModel.changeMic()
    }

    fun clickCamButton(view: View) {
        if(!(viewModel.isConnect.value!!)){
            return
        }
        if (viewModel.webcamEnabled.value!!) {
            meeting!!.disableWebcam()
        } else {
            meeting!!.enableWebcam()
        }
        viewModel.changeCam()
    }

    fun clickVoiceChatButton(view: View) {
        if (viewModel.isConnect.value!!) {
            viewModel.disConnect()
            meeting!!.leave()
            finish()
        } else {
            meeting!!.join()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(this, permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode)
            return false
        }
        return true
    }

    fun clickBackButton(view: View) {
        finish()
    }


    private val meetingEventListener: MeetingEventListener = object : MeetingEventListener() {
        override fun onMeetingJoined() {

        }

        override fun onMeetingLeft() {
            binding.rendererVoiceChatLocal.clearImage()
        }

        override fun onParticipantJoined(participant: Participant) {
            initRenderer(binding.rendererVoiceChatRemote, participant)
        }

        override fun onParticipantLeft(participant: Participant) {
            binding.rendererVoiceChatRemote.clearImage()
        }
    }

    private fun initRenderer(renderer: SurfaceViewRenderer, participant: Participant) {
        for ((_, stream) in participant.streams) {
            if (stream.kind.equals("video", ignoreCase = true)) {
                renderer.visibility = View.VISIBLE
                val videoTrack = stream.track as VideoTrack
                videoTrack.addSink(renderer)
                break
            }
        }
        participant.addEventListener(object : ParticipantEventListener() {
            override fun onStreamEnabled(stream: Stream) {
                if(participant == meeting!!.localParticipant){
                    viewModel.connect()
                }
                if (stream.kind.equals("video", ignoreCase = true)) {
                    renderer.visibility = View.VISIBLE
                    val videoTrack = stream.track as VideoTrack
                    videoTrack.addSink(renderer)
                }
            }

            override fun onStreamDisabled(stream: Stream) {
                if (stream.kind.equals("video", ignoreCase = true)) {
                    val track = stream.track as VideoTrack
                    track.removeSink(binding.rendererVoiceChatRemote)
                    renderer.clearImage()
                    renderer.visibility = View.GONE
                }
            }
        })
    }

    companion object {
        private const val PERMISSION_REQ_ID = 22

        @RequiresApi(Build.VERSION_CODES.S)
        private val REQUESTED_PERMISSIONS = arrayOf(
            RECORD_AUDIO,
            Manifest.permission.CAMERA,
            BLUETOOTH_CONNECT
        )
    }

}
