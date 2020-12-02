package com.jphernandez.melichallenge.application

import android.app.Application

class MeliChallengeApplication: Application() {

    companion object{
        val appComponent: AppComponent =
            DaggerAppComponent.create()
    }
}