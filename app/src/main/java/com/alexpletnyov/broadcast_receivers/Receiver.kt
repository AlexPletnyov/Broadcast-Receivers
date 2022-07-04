package com.alexpletnyov.broadcast_receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {

	override fun onReceive(context: Context?, intent: Intent?) {
		val turnedOn = intent?.getBooleanExtra("state", false)
		val count = intent?.getIntExtra(EXTRA_COUNT, 0)

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
			ACTION_CLICKED -> {
				Toast.makeText(context, "Clicked $count", Toast.LENGTH_SHORT).show()
			}
		}
	}

	companion object {

		const val ACTION_CLICKED = "clicked"
		const val EXTRA_COUNT = "count"
		const val EXTRA_PERCENT = "percent"
		const val ACTION_LOADING_PROGRESS = "progress"
	}
}