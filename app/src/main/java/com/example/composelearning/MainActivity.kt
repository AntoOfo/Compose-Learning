package com.example.composelearning

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.ui.theme.ComposeLearningTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLearningTheme {
            }
        }
    }
}

// search bar composable
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {     // for the small icon inside the searchbar
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("Search")   // hint inside search bar
        },

        modifier = modifier
            .fillMaxWidth()  // fills width of screen
            .heightIn(min = 56.dp)  // height of search bar
    )
}

// ui for alignyourbody element composable (circle image with text below)
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,  // align columns contents to centre
        modifier = modifier) {
        Image(      // image
            painter = painterResource(drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,    // crops image to fit in circle shape
            modifier = Modifier
                .size(88.dp)  // scale image down
                .clip(CircleShape)  // shape
        )
        Text(text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp))

    }
}

// lazyrow for alignyourbody elements with data fetched from Data.kt
@Composable
fun AlignYourBodyRow(modifier: Modifier = Modifier) {
    // scrollable row
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),  // space between elements
        contentPadding = PaddingValues(horizontal = 16.dp),  // elements wont clip into padding
        modifier = modifier) {
        items(alignYourBodyData) { item ->  // for each item in the data, invoke the method for the element
            AlignYourBodyElement(item.drawable, item.text)  // in data.kt
        }
    }
}

// ui for favoritecollectioncard element composable
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier) {

    Surface(
        shape = MaterialTheme.shapes.medium,        // curved border
        color = MaterialTheme.colorScheme.surfaceVariant,    // colour of surface
        modifier = modifier) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(255.dp)
                .height(80.dp)) {       // length of row
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
            Text(
                text = stringResource(text),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// lazyhorizontalgrid for collection card elements with data fetched from Data.kt
@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),   // fixed amount of rows for the grid
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text)
        }
    }
}

// composable for the home section (no top search bar or bottom nav bar)
@Composable
fun HomeSection(
    @StringRes title: Int,      // title text
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit) {   // composable to pass in

    Column(modifier) {
        Text(stringResource(title),     // passed in title
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(bottom = 16.dp, top = 40.dp)
                .padding(horizontal = 16.dp))
        content()                      // passed in composable
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    // adds scrolling for devices that arent tall enough
    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))   // spacer literally just makes space
        SearchBar(Modifier.padding(horizontal = 16.dp))
        // home section for align body part
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        // home section for collections part
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    HomeSection(R.string.align_your_body) {
        AlignYourBodyRowPreview()
    }
}

// preview for FavoriteCollectionsGrid
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview(modifier: Modifier = Modifier) {
    FavoriteCollectionsGrid()
}

// preview for alignbody row
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview(modifier: Modifier = Modifier) {
    AlignYourBodyRow()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        text = R.string.NatureMeditations,
        drawable = R.drawable.leavespng,
        modifier = Modifier.padding(8.dp))
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    AlignYourBodyElement(
        text = R.string.Inversions,
        drawable = R.drawable.ab1_inversion,
        modifier = Modifier.padding(8.dp)
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}

