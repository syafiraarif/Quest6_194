package com.example.quest6_194.viewmodel

class SiswaViewModel : ViewModel() {
    private val _statusUI = MutableStateFlow( value = Siswa())
    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()


}