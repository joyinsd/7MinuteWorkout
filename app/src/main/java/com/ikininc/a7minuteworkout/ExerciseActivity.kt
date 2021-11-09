package com.ikininc.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener{

    private var restTimer:CountDownTimer?=null
    private var restProgress=0

    private var exeTimer:CountDownTimer?=null
    private var exeProgress=0
    private var exerciseTimeDuration:Long=1
    private var restTimeDuration:Long=1

    private var exeArrayList:ArrayList<ExerciseModel>?=null
    private var currentExePosition=-1

    private var tts:TextToSpeech?=null

    private var player:MediaPlayer?=null

    private var exerciseAdapter:ExerciseStatusAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
            customerDialogForBackButton()
        }
        exeArrayList = Constants.defaultExerciseList();
        tts= TextToSpeech(this, this)

        setupRestView()
        setupExerciseStatusRecyclerView()
    }

    private fun setupRestView(){

        player= MediaPlayer.create(applicationContext, R.raw.press_start)
        player!!.isLooping=false
        player!!.start()

        llRestView.visibility=View.VISIBLE
        llExerciseView.visibility=View.GONE

        tvNextExeName.text=exeArrayList!![currentExePosition+1].getName()

        if(restTimer !=null){
            restTimer!!.cancel()
            restProgress=0
        }
        setRestProgressBar()
    }

    private fun setRestProgressBar(){

        progressBar.progress = restProgress

        restTimer = object:CountDownTimer(restTimeDuration*1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                var countDown = 10-restProgress
                progressBar.progress=countDown
                tvTimer.text =countDown.toString()
            }

            override fun onFinish() {
                currentExePosition++

                exeArrayList!![currentExePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExeView()
            }
        }.start()

    }

    private fun setupExeView(){

        llRestView.visibility = View.GONE
        llExerciseView.visibility=View.VISIBLE

        if(exeTimer !=null){
            exeTimer!!.cancel()
            exeProgress=0
        }

        speakOut(exeArrayList!![currentExePosition].getName())
        ivImage.setImageResource(exeArrayList!![currentExePosition].getImage())
        tvExeName.text=exeArrayList!![currentExePosition].getName()
        setExeProgressBar()
    }

    private fun setExeProgressBar(){

        progressBarExe.progress = restProgress

        exeTimer = object:CountDownTimer(exerciseTimeDuration*1000, 1000){
            override fun onTick(millisUntilFinished:Long) {
                exeProgress++
                var countDown = 30-exeProgress
                progressBarExe.progress=countDown
                tvTimerExe.text =countDown.toString()
            }

            override fun onFinish() {
                if(currentExePosition < exeArrayList!!.size-1){
                    exeArrayList!![currentExePosition].setIsSelected(false)
                    exeArrayList!![currentExePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged() //onBindViewHolder update when it is happening
                    setupRestView()
                }else {
                    finish()
                    val intent =Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer!!.cancel()
            restProgress=0;
        }
        if(exeTimer!=null){
            exeTimer!!.cancel()
            exeProgress=0;
        }
        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        if(player!=null){
            player!!.stop()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            var result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization Failed")
        }
    }

    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun setupExerciseStatusRecyclerView(){
//to setup recyclerView with the RecyclerAdapter that I made
        //rvExeStatus is the id of recyclerView declaration
        rvExeStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exeArrayList!!, this)
        rvExeStatus.adapter=exerciseAdapter
    }

    private fun customerDialogForBackButton(){
        val customDialog= Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        customDialog.tvYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.show()

    }
}