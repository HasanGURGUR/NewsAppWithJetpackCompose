package com.hasangurgur.newsappjetpackcompose.domain.usecases.app_entry

import com.hasangurgur.newsappjetpackcompose.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}