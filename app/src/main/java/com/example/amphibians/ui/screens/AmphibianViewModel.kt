package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "AmphibianViewModel"

class AmphibianViewModel: ViewModel() {
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibianItem()
    }

    private fun getAmphibianItem() {
        viewModelScope.launch {
            val result = AmphibianApi.retrofitService.getAmphibianList()
            Log.d(TAG, amphibianUiState.toString())
            amphibianUiState = try{
                 AmphibianUiState.Success(result)
            } catch(e:IOException){
                AmphibianUiState.Error
            } catch (e: HttpException) {
                AmphibianUiState.Error
            }
        }
    }
}