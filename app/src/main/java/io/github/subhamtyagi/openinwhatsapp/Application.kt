package io.github.subhamtyagi.openinwhatsapp

import android.app.Application
import android.os.Build
import timber.log.Timber

const val MAX_TAG_LENGTH = 23

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(LongTagTree(packageName))
    }

    inner class LongTagTree(private val packageName: String) : Timber.DebugTree() {

        private fun getMessage(tag: String?, message: String): String {
            val longTagTreeMessage: String
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                // Tag length limitation (<23): Use truncated package name and add class name to message
                longTagTreeMessage = "$tag: $message"
            } else {
                // No tag length limit limitation: Use package name *and* class name
                longTagTreeMessage = message
            }
            return longTagTreeMessage
        }

        private fun getTag(tag: String?): String {
            var longTagTreeTag: String
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                // Tag length limitation (<23): Use truncated package name and add class name to message
                longTagTreeTag = packageName
                if (longTagTreeTag.length > MAX_TAG_LENGTH) {
                    val shortPackageName = packageName.substring(packageName.length - MAX_TAG_LENGTH + 3, packageName.length)
                    longTagTreeTag = "...$shortPackageName"
                }
            } else {
                // No tag length limit limitation: Use package name *and* class name
                longTagTreeTag = "$packageName $tag"
            }

            return longTagTreeTag
        }

        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            val longTagTreeMessage = getMessage(tag, message)
            val longTagTreeTag = getTag(tag)

            super.log(priority, longTagTreeTag, longTagTreeMessage, t)
        }
    }
}
