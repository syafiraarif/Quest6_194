package com.example.quest6_194.view.iucontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quest6_194.model.DataJK.JenisK
import com.example.quest6_194.view.FormSiswa
import com.example.quest6_194.view.TampilSiswa
import com.example.quest6_194.viewmodel.SiswaViewModel

enum class Navigasi {
    FormSiswa,
    DetailSiswa
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    siswaViewModel: SiswaViewModel = viewModel()
) {
    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.FormSiswa.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // ⬅ Halaman Form
            composable(Navigasi.FormSiswa.name) {
                FormSiswa(
                    pilihanJK = listOf("Laki-laki", "Perempuan", "Lainnya"),
                    OnSubmitButtonClicked = { listData ->
                        siswaViewModel.setSiswa(listData)
                        navController.navigate(Navigasi.DetailSiswa.name)
                    }
                )
            }

            // ⬅ Halaman Detail
            composable(Navigasi.DetailSiswa.name) {

                val state = siswaViewModel.statusUI.collectAsState()

                TampilSiswa(
                    statusIUSiswa = state.value,
                    onBackButtonClicked = {
                        backToForm(navController)
                    }
                )
            }
        }
    }
}

private fun backToForm(navController: NavHostController) {
    navController.popBackStack(
        route = Navigasi.FormSiswa.name,
        inclusive = false
    )
}
