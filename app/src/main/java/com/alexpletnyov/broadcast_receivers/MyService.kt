package com.alexpletnyov.broadcast_receivers

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.alexpletnyov.broadcast_receivers.Receiver.Companion.EXTRA_PERCENT
import kotlin.concurrent.thread

class MyService : Service() {

	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		thread {
			for (i in 1..10) {
				Thread.sleep(1000)

				Intent(Receiver.ACTION_LOADING_PROGRESS).apply {
					putExtra(EXTRA_PERCENT, i * 10)
					sendBroadcast(this)
				}
			}
		}
		return super.onStartCommand(intent, flags, startId)
	}

	override fun onBind(intent: Intent?): IBinder? {
		TODO("Not yet implemented")
	}
}