package com.cekroda.ui.screen.inspection

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cekroda.model.Inspection
import com.cekroda.model.InspectionStatus
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInspectionScreen(onSave: (Inspection) -> Unit) {
    var carName by remember { mutableStateOf("") }
    var carType by remember { mutableStateOf("") }
    var licensePlate by remember { mutableStateOf("") }
    var inspectorName by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(InspectionStatus.INCOMING) } // Default to INCOMING
    val photoUri by remember { mutableStateOf<Uri?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .padding(16.dp)
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            TextField(
                value = carName,
                onValueChange = { carName = it },
                label = { Text("Car Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = carType,
                onValueChange = { carType = it },
                label = { Text("Car Type") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = licensePlate,
                onValueChange = { licensePlate = it },
                label = { Text("License Plate") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = inspectorName,
                onValueChange = { inspectorName = it },
                label = { Text("Inspector Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = status.name,onValueChange = {},
                    readOnly = true,
                    label = { Text("Status") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    InspectionStatus.values().forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.name) },
                            onClick = {
                                status = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val inspection = Inspection(
                        carName = carName,
                        carType = carType,
                        licensePlate = licensePlate,
                        inspectionDate = Date().time, // Current timestamp
                        inspectorName = inspectorName,
                        notes = notes,
                        status = status,
                        photoUri = photoUri?.toString()
                    )
                    onSave(inspection)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Inspection")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddInspectionScreenPreview() {
    AddInspectionScreen(onSave = {})
}