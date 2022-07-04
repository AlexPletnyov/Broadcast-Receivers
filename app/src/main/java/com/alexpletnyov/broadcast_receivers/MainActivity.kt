package com.alexpletnyov.broadcast_receivers

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alexpletnyov.broadcast_receivers.Receiver.Companion.EXTRA_COUNT

class MainActivity : AppCompatActivity() {

	private val receiver = Receiver()
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
		}
		registerReceiver(receiver, intentFilter)
	}

	override fun onDestroy() {
		super.onDestroy()
		unregisterReceiver(receiver)
	}
}