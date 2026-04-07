package com.example.cs501_finalproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(onFindCareClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Recommendation",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Urgency: Urgent Care",
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text("You should visit an urgent care clinic.")

        Text(
            text = "This app does not provide a diagnosis.",
            modifier = Modifier.padding(top = 12.dp)
        )

        Button(
            onClick = onFindCareClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            Text("Find Nearby Care")
        }
    }
}