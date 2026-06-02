package com.example.paroleinspiree.live

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LiveInfo(
    val videoId: String = "",
    val title: String = "",
    val status: String = "soon"
)

class LiveViewModel(private val repository: LiveRepository = LiveRepository()) : ViewModel() {

    private val _liveInfo = MutableStateFlow(LiveInfo())
    val liveInfo: StateFlow<LiveInfo> = _liveInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadLive() {
        viewModelScope.launch {
            _isLoading.value = true
            _liveInfo.value = repository.fetchLiveInfo()
            _isLoading.value = false
        }
    }

    fun setLive(videoId: String, title: String) {
        _liveInfo.value = LiveInfo(videoId = videoId, title = title, status = "live")
    }
}

class LiveViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiveViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}