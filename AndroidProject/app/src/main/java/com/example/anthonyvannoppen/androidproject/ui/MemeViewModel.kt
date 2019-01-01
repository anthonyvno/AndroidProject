package com.example.anthonyvannoppen.androidproject.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.anthonyvannoppen.androidproject.base.InjectedViewModel
import com.example.anthonyvannoppen.androidproject.domain.Meme
import com.example.anthonyvannoppen.androidproject.network.MemeApi
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MemeViewModel: InjectedViewModel(){
    private val memes = MutableLiveData<List<Meme>>()
    @SuppressLint("NewApi")

    @Inject
    lateinit var memeApi: MemeApi

    /**
     * Indicates whether the loading view should be displayed.
     */
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Represents a disposable resources
     */
    private var subscription: Disposable
    init {
        subscription = memeApi.getAllMemes()
            //we tell it to fetch the data on background by
            .subscribeOn(Schedulers.io())
            //we like the fetched data to be displayed on the MainTread (UI)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMemeStart() }
            .doOnTerminate { onRetrieveMemeFinish() }
            .subscribe(
                { result -> onRetrieveMemeSuccess(result) },
                { error -> onRetrieveMemeError(error) }
            )

    }

    private fun onRetrieveMemeError(error: Throwable) {
        //Currently requests fail silently, which isn't great for the user.
        //It would be better to show a Toast, or maybe make a TextView visible with the error message.
        Logger.e(error.message!!)
    }

    @SuppressLint("NewApi")
    private fun onRetrieveMemeSuccess(result: List<Meme>) {
        memes.value = result
    }

    private fun onRetrieveMemeFinish() {
        Logger.i("Finished retrieving meme info")
        loadingVisibility.value = false
    }

    private fun onRetrieveMemeStart() {
        Logger.i("Started retrieving meme info")
        loadingVisibility.value = true
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getMemes(): MutableLiveData<List<Meme>> {
        Log.d("robert", memes.toString())
        return memes
    }

    fun postMeme(meme:Meme){
        memeApi.addMeme(meme)
    }


}