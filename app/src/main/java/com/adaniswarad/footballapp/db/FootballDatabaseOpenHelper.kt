package com.adaniswarad.footballapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FootballDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: FootballDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballDatabaseOpenHelper {
            if (instance == null) {
                instance = FootballDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as FootballDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.MATCH_ID to TEXT + UNIQUE,
            Favorite.MATCH_DATE to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: FootballDatabaseOpenHelper
    get() = FootballDatabaseOpenHelper.getInstance(applicationContext)