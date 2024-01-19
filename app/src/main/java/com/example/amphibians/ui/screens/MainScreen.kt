package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.amphibians.R
import com.example.amphibians.network.model.AmphibianItem

private const val TAG = "MainScreen"

@Composable
fun MainScreen(
    uiState: AmphibianUiState,
    modifier: Modifier = Modifier
) {
    Log.d(TAG, uiState.toString())
    when(uiState){
        is AmphibianUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is AmphibianUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
        is AmphibianUiState.Success -> ResultScreen(uiState.amphibianList, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.app_loading)
    )
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )

        Text(
            text = stringResource(id = R.string.app_loading_failed)
        )
    }
}

@Composable
fun ResultScreen(
    itemList: List<AmphibianItem>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = itemList, key = { item -> item} ) {
            item -> AmphibianCard(item = item)
        }
    }
}

@Composable
fun AmphibianCard(
    item: AmphibianItem,
    modifier: Modifier = Modifier
) {
    Text(
        text = item.name
    )
    Text(
        text = item.description
    )
}