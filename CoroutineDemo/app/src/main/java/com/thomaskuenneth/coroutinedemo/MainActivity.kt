package com.thomaskuenneth.coroutinedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// import androidx.lifecycle.lifecycleScope
import com.thomaskuenneth.coroutinedemo.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        lifecycleScope.launchWhenStarted {
//            var count = 0
//            while (isActive) {
//                val result = withContext(Dispatchers.IO) {
//                    delay(3000)
//                    count++
//                }
//                binding.textview.text = "$result"
//            }
//        }

        // don't do it like this :-)
//        val job = GlobalScope.launch {
//            var count = 0
//            while (isActive) {
//                delay(1000)
//                println(count++)
//            }
//        }
//        Handler(mainLooper).postDelayed({
//            job.cancel()
//        }, 10000)

        val t = GlobalScope.launch {
            val myFlow = flow {
                for (i in listOf(10, 3, 42, 7)) {
                    delay(5000)
                    emit(i)
                }
            }
            myFlow.collect {
                println("\n$it")
            }
        }
        GlobalScope.launch {
            while (t.isActive) {
                println("-")
                delay(1000)
            }
        }

    }
}