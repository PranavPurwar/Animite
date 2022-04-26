package com.imashnake.animite.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.imashnake.animite.TrendingNowQuery
import com.imashnake.animite.ui.state.HomeViewModel

/**
 * TODO: Kdoc.
 */
@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {
    viewModel.run {
        addAnimes(1, 5, 6, 7, 132405)
    }

    val animeList = viewModel.uiState.trendingAnimeList

    if (animeList != null) {
        AnimeList(animeList = animeList)
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun AnimeList(animeList: TrendingNowQuery.Page) {
    LazyColumn(
        modifier = Modifier
            // TODO: This is a hack, understand how layouts work and un-hardcode this.
            .padding(bottom = 80.dp)
            .fillMaxSize()
    ) {
        if(animeList.media != null)
            items(animeList.media) { anime ->
                Row {
                    AsyncImage(
                        model = anime?.coverImage?.large,
                        contentDescription = anime?.title?.native,
                        modifier = Modifier
                    )
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = (anime?.title?.english ?: "Null bro"),
                            modifier = Modifier
                                .padding(6.dp)
                                .absolutePadding(left = 30.dp),
                            fontSize = 16.sp
                        )
                        Text(
                            text = (anime?.title?.romaji ?: "Null bro"),
                            modifier = Modifier
                                .padding(6.dp)
                                .absolutePadding(left = 30.dp),
                            fontSize = 16.sp
                        )
                        Text(
                            text = (anime?.title?.native ?: "Null bro"),
                            modifier = Modifier
                                .padding(6.dp)
                                .absolutePadding(left = 30.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
}
