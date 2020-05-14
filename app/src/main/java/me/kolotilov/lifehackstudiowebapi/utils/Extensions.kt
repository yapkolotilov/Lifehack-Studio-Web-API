package me.kolotilov.lifehackstudiowebapi.utils

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions

private val compositeDisposable by lazy { CompositeDisposable() }

fun Disposable.autoDispose() {
    compositeDisposable.add(this)
}