package mx.com.exploremars

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Executors(private val backThread: ExecutorService, private val mainThread: Executor) {
    constructor() : this(Executors.newSingleThreadExecutor(), MainThreadExecutor())

    fun back(): ExecutorService = backThread

    fun main(): Executor = mainThread

    private class MainThreadExecutor: Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            command?.let { mainThreadHandler.post(it) }
        }
    }
}