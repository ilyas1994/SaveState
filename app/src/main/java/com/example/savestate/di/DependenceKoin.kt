package com.example.savestate.di

import com.example.savestate.Presenter.Presenter
import org.koin.dsl.module

val appModul = module {
    factory { Presenter()}
}