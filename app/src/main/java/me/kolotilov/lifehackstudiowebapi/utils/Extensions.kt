package me.kolotilov.lifehackstudiowebapi.utils

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

private val compositeDisposable by lazy { CompositeDisposable() }

fun Disposable.autoDispose() {
    compositeDisposable.add(this)
}

fun Drawable?.notNull(): Drawable = requireNotNull(this)

fun safeStartActivity(context: Context, intent: Intent) {
    if (intent.resolveActivity(context.packageManager) != null)
        context.startActivity(intent)
    else
        Timber.d("Couldn't resolve activity for intent!")
}