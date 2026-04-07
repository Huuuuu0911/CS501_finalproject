package com.example.cs501_finalproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    urgency: String,
    recommendation: String,
    symptom: String,
    painLevel: Int,
    duration: String,
    onFindCareClick: () -> Unit
) {
    val safeDuration = if (duration.isBlank()) "Not provided" else duration
    val buttonText = if (urgency == "Emergency") "Find Emergency Care" else "Find Nearby Care"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Recommendation",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        UrgencyBadge(urgency = urgency)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Urgency: $urgency",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Symptom: $symptom",
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Pain Level: $painLevel",
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "Duration: $safeDuration",
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }

        Text(
            text = recommendation,
            modifier = Modifier.padding(top = 20.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = if (urgency == "Emergency") {
                "This app does not provide a medical diagnosis. If symptoms are severe or worsening, seek emergency care immediately."
            } else {
                "This app does not provide a medical diagnosis. If symptoms get worse, please seek professional care immediately."
            },
            modifier = Modifier.padding(top = 16.dp)
        )

        Button(
            onClick = onFindCareClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text(buttonText)
        }
    }
}

@Composable
fun UrgencyBadge(urgency: String) {
    val bgColor = when (urgency) {
        "Emergency" -> Color(0xFFFFDAD6)
        "Urgent Care" -> Color(0xFFFFE0B2)
        else -> Color(0xFFDFF5E1)
    }

    val textColor = when (urgency) {
        "Emergency" -> Color(0xFFB3261E)
        "Urgent Care" -> Color(0xFF8A5200)
        else -> Color(0xFF1B5E20)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = urgency,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}