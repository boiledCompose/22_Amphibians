package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianApplication
import com.example.amphibians.data.AmphibianRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "AmphibianViewModel"

class AmphibianViewModel(private val amphibianRepository: AmphibianRepository): ViewModel(
) {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibianItems()
    }

    private fun getAmphibianItems() {
        viewModelScope.launch {
            Log.d(TAG, amphibianUiState.toString())
            amphibianUiState = try{
                val listResult = amphibianRepository.getAmphibianItems()
                AmphibianUiState.Success(listResult)
            } catch(e:IOException){
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
            Log.d(TAG, amphibianUiState.toString())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository = application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}