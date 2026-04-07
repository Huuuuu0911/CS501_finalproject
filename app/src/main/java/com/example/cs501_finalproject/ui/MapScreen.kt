package com.example.cs501_finalproject.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

data class Facility(
    val name: String,
    val type: String,
    val address: String,
    val distance: String
)

@Composable
fun MapScreen(
    urgency: String,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val facilities = when (urgency) {
        "Emergency" -> listOf(
            Facility("Boston Medical Center", "Emergency Room", "1 Boston Medical Center Pl, Boston, MA", "1.2 miles away"),
            Facility("Mass General Hospital", "Emergency Room", "55 Fruit St, Boston, MA", "2.1 miles away"),
            Facility("City Hospital ER", "Emergency Room", "725 Albany St, Boston, MA", "2.8 miles away")
        )

        "Urgent Care" -> listOf(
            Facility("Downtown Urgent Care", "Urgent Care", "580 Washington St, Boston, MA", "0.8 miles away"),
            Facility("QuickCare Clinic", "Urgent Care", "295 Commonwealth Ave, Boston, MA", "1.4 miles away"),
            Facility("Neighborhood Walk-in Clinic", "Urgent Care", "133 Brookline Ave, Boston, MA", "1.7 miles away")
        )

        else -> listOf(
            Facility("Boston Primary Care", "Primary Care", "800 Boylston St, Boston, MA", "1.0 miles away"),
            Facility("Community Health Center", "Primary Care", "1601 Washington St, Boston, MA", "1.6 miles away"),
            Facility("Telehealth Access", "Virtual Care", "Online Appointment", "Available today")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Nearby Care Options",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Recommended care level: $urgency",
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Tap a location card to open directions in Google Maps.",
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Map Preview",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Map integration can be expanded in the next version. For now, tap a care option below to open navigation.",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(facilities) { facility ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            openInGoogleMaps(
                                context,
                                "${facility.name} ${facility.address}"
                            )
                        }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = facility.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = facility.type,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = facility.address,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = facility.distance,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}

fun openInGoogleMaps(context: Context, placeQuery: String) {
    val uri = Uri.parse("geo:0,0?q=" + Uri.encode(placeQuery))
    val mapIntent = Intent(Intent.ACTION_VIEW, uri)
    mapIntent.setPackage("com.google.android.apps.maps")

    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        val fallbackIntent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(fallbackIntent)
    }
}