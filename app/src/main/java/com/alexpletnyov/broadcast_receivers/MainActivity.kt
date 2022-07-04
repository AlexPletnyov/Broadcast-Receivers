package com.alexpletnyov.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import com.alexpletnyov.broadcast_receivers.Receiver.Companion.EXTRA_COUNT

class MainActivity : AppCompatActivity() {

	private val receiver = Receiver()

	private val receiver2 = object : BroadcastReceiver() {
		override fun onReceive(context: Context?, intent: Intent?) {
			if (intent?.action == Receiver.ACTION_LOADING_PROGRESS) {
				findViewById<ProgressBar>(R.id.pb_progress_bar).apply {
					progress = intent.getIntExtra(Receiver.EXTRA_PERCENT, 0)
				}
			}
		}
	}
	private var count = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		findViewById<Button>(R.id.button_receive).setOnClickListener {
			Intent(Receiver.ACTION_CLICKED).apply {
				putExtra(EXTRA_COUNT, count++)
				sendBroadcast(this)
			}
		}

		val intentFilter = IntentFilter().apply {
			addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
			addAction(Intent.ACTION_BATTERY_LOW)
			addAction(Receiver.ACTION_CLICKED)
			addAction(Receiver.ACTION_LOADING_PROGRESS)
		}
		registerReceiver(receiver, intentFilter)
		registerReceiver(receiver2, intentFilter)
		Intent(this, MyService::class.java).apply {
			startService(this)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		unregisterReceiver(receiver)
		unregisterReceiver(receiver2)
	}
}