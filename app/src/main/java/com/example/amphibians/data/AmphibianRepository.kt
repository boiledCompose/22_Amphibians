package com.example.amphibians.data

import com.example.amphibians.network.AmphibianApiService
import com.example.amphibians.model.AmphibianItem

interface AmphibianRepository{
    suspend fun getAmphibianItems(): List<AmphibianItem>
}

// 객체 선언은 되도록이면 피하자
class NetworkAmphibianRepository(
    private val amphibianApiService: AmphibianApiService
) : AmphibianRepository{
    override suspend fun getAmphibianItems(): List<AmphibianItem> =
        amphibianApiService.getAmphibianList()

}