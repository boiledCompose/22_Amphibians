package com.example.amphibians.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibianItem

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
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = itemList ) {
            item -> AmphibianCard(item = item)
        }
    }
}

@Composable
fun AmphibianCard(
    item: AmphibianItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_padding_amphibian_card))
    ) {
        Text(
            text = stringResource(id = R.string.amphibian_card_title, item.name, item.type),
            modifier = modifier.padding(dimensionResource(R.dimen.dimen_amphibian_card_text)),
            style = MaterialTheme.typography.titleMedium

        )
        
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(item.imgSrc)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.loading_img)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = modifier.fillMaxWidth())
        
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(dimensionResource(R.dimen.dimen_amphibian_card_text))
        )
    }
}