package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    restaurant: Restaurant,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(
                vertical = 4.dp, horizontal = 8.dp
            )
            .semantics {
                contentDescription = "${restaurant.name} item"
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp),
        ) {
            AsyncImage(
                model = restaurant.getPicture(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        CircleShape
                    )
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = restaurant.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = restaurant.description,
                    softWrap = true,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantItemPreview() {
    RestaurantItem(
        restaurant = Restaurant(
            id = "123",
            name = "Kunokuni",
            city = "Surabaya", rating = 4.5,
            pictureId = "14",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Eget nullam non nisi est sit. Amet porttitor eget dolor morbi non arcu risus quis varius. Diam in arcu cursus euismod quis viverra nibh cras. Sit amet nulla facilisi morbi tempus iaculis urna id volutpat.",
        )
    )
}