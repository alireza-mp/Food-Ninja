package com.digimoplus.foodninja.presentation.ui.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.useCase.login.CheckAuthenticationUseCase
import com.digimoplus.foodninja.domain.useCase.register.CheckCompleteRegisterUseCase
import com.digimoplus.foodninja.domain.useCase.register.CheckIntroductionUseCase
import com.digimoplus.foodninja.domain.util.OnlineChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val onlineChecker: OnlineChecker,
    private val checkCompleteRegisterUseCase: CheckCompleteRegisterUseCase,
    private val checkAuthenticationUseCase: CheckAuthenticationUseCase,
    private val checkIntroductionUseCase: CheckIntroductionUseCase,
) : ViewModel() {

    val retryVisibility = mutableStateOf(false)

    // ping google to check internet connection
    suspend fun isOnline(): Boolean {
        return withContext(Dispatchers.IO) {
            onlineChecker.isOnline
        }
    }

    // check user authentication
    suspend fun checkAuthentication(): String = withContext(Dispatchers.IO) {
        checkAuthenticationUseCase()
    }

    suspend fun checkCompleteRegister(): String = withContext(Dispatchers.IO) {
        checkCompleteRegisterUseCase()
    }

    suspend fun checkUserIntroduction(): String = withContext(Dispatchers.IO) {
        checkIntroductionUseCase()
    }

}