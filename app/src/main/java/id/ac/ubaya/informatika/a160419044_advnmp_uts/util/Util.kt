package id.ac.ubaya.informatika.a160419044_advnmp_uts.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.ac.ubaya.informatika.a160419044_advnmp_uts.R
import id.ac.ubaya.informatika.a160419044_advnmp_uts.model.TodoDatabase
import java.lang.Exception

val DB_NAME = "tododb"

fun buildDB(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context,
            TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()
    return db
}

fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

fun ImageView.loadImage(url:String, progressBar:ProgressBar) {
    Picasso.get()
            .load(url)
            .resize(400,400)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_24)
            .into(this, object:Callback {
                    override fun onSuccess() {
                            progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                    }
            })
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}

val MIGRATION_2_3 = object: Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}