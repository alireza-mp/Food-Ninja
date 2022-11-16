package com.digimoplus.foodninja.domain.util

import java.io.IOException
import java.util.concurrent.TimeUnit

class ExperimentalOnlineChecker(private val runtime: Runtime) : OnlineChecker {

    override val isOnline: Boolean
        get() {
            try {
               // val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
                val ipProcess = runtime.exec("/system/bin/ping -c 1 0.0.0.0")
                return ipProcess.waitFor() ==0
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return false
        }


}