package jetpack

import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(private var countReserved:Int): ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}