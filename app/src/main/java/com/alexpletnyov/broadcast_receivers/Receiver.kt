package com.alexpletnyov.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {

	override fun onReceive(context: Context?, intent: Intent?) {
		val turnedOn = intent?.getBooleanExtra("state", false)
		when (intent?.action) {
			Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
				Toast.makeText(
					context,
					"Airplane mode changed. Turned od: $turnedOn",
					Toast.LENGTH_SHORT
				).show()
			}
			Intent.ACTION_BATTERY_LOW -> {
				Toast.makeText(context, "Battery is low", Toast.LENGTH_SHORT).show()
			}
		}
	}
}