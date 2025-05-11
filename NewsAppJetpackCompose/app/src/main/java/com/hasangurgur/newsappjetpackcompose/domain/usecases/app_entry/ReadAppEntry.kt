package com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry

import com.hasangurgur.newsappjetpackcompose.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager: LocalUserManager
) {
     operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}