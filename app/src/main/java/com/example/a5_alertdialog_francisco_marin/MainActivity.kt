package com.example.a5_alertdialog_francisco_marin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a5_alertdialog_francisco_marin.ui.theme._5_alertdialog_Francisco_MarinTheme

class MainActivity : ComponentActivity() {

    private val dialogos = listOf(
        DatosDialogo("Confirmación de Acción", "¿Estás seguro de que deseas continuar?", "Sí", "No"),
        DatosDialogo("Eliminar Elemento", "Esta acción es irreversible. ¿Eliminar?", "Eliminar", "Cancelar"),
        DatosDialogo("Aviso Importante", "Los cambios realizados no se pueden deshacer.", "Entendido", ""),
        DatosDialogo("Requiere Autenticación", "Necesitas autenticarte de nuevo.", "Autenticar", "Cancelar"),
        DatosDialogo("Error Crítico", "Error crítico. ¿Intentar de nuevo?", "Reintentar", "Cancelar")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _5_alertdialog_Francisco_MarinTheme {
                MainScreen(dialogos)
            }
        }
    }
}

@Composable
fun MainScreen(dialogos: List<DatosDialogo>) {
    var dialogo by remember { mutableStateOf(-1) }
    var texto by remember { mutableStateOf("HOLA") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = texto, style = MaterialTheme.typography.bodyLarge)

        for (i in dialogos.indices) {
            val datos = dialogos[i]
            Button(onClick = { dialogo = i }, modifier = Modifier.fillMaxWidth()) {
                Text(datos.title)
            }
        }

        if (dialogo >= 0) {
            val datos = dialogos[dialogo]
            AlertDialog(
                onDismissRequest = { dialogo = -1 },
                title = { Text(datos.title) },
                text = { Text(datos.message) },
                confirmButton = {
                    TextButton(onClick = {
                        when (dialogo) {
                            0 -> texto = "Acción Confirmada"
                            1 -> texto = "Eliminación exitosa"
                            3 -> texto = "Usuario Autenticado"
                            4 -> texto = "Intento de Reintento"
                        }
                        dialogo = -1
                    }) {
                        Text(datos.confirmButtonText)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { dialogo = -1 }) {
                        Text(datos.dismissButtonText)
                    }
                }
            )
        }
    }
}
