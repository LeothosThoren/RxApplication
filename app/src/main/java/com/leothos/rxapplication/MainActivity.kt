package com.leothos.rxapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener { startCombineStream() }

    }

    private fun startCombineStream() {
        val num = Observable.range(1, 6)
        val str = Observable.just("one", "two", "three", "four", "five")

        val zipped = Observables.zip(num, str) { n, s -> "$n $s" }
        zipped.subscribe(::println)
    }

    private fun startStream() {

        val list = listOf("1", "2", "3", "4", "5")

        // Transform the list into observable
        list.toObservable()
            .subscribeBy(
                onNext = { println("onNext $it") },
                onError = { it.message },
                onComplete = { println("onComplete") })


//        // Create observable
//        val observable = getObservable()
//
//        // Create Observer
//        val observer = getObserver()
//
//        // Subscribe observer to observable
//        observable.subscribe(observer)

    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG, "onNext $t")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError ${e.message}")
            }

        }
    }
}
