package com.example.amphibians.ui.screens

import com.example.amphibians.model.AmphibianItem

sealed interface AmphibianUiState {
    data class Success(val amphibianList:List<AmphibianItem>): AmphibianUiState
    object Error:AmphibianUiState
    object Loading:AmphibianUiState
}