package ren.shuaipeng.android.cms.db

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.util.concurrent.TimeUnit

abstract class DbTest {
    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()

    private lateinit var _db: CmsClientDb
    val db: CmsClientDb
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
            getInstrumentation().context,
            CmsClientDb::class.java
        ).build()
    }

    @After
    fun closeDb() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        _db.close()
    }
}
