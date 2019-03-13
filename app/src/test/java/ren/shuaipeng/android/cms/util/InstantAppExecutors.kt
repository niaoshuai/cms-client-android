package ren.shuaipeng.android.cms.util

import ren.shuaipeng.android.cms.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}