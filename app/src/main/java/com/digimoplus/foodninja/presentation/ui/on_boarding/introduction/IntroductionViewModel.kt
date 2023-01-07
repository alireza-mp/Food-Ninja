package com.digimoplus.foodninja.presentation.ui.on_boarding.introduction

import androidx.lifecycle.ViewModel
import com.digimoplus.foodninja.domain.useCase.register.SaveIntroductionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel
@Inject
constructor(
    private val saveIntroductionUseCase: SaveIntroductionUseCase,
) : ViewModel() {

    // save user looked introduction to datastore
    suspend fun saveIntroduction() {
        saveIntroductionUseCase(true)
    }

}