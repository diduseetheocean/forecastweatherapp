package io.github.diduseetheocean.forecastweatherapp.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel
import io.github.diduseetheocean.forecastweatherapp.domain.SearchState
import io.github.diduseetheocean.forecastweatherapp.domain.WeatherUsecase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PageViewModel(
    val weatherUsecase: WeatherUsecase = WeatherUsecase(),
) : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    private val _searchViewState = MutableLiveData<SearchState>(SearchState.Empty)
    val searchViewState: LiveData<SearchState> = Transformations.map(_searchViewState) {
        it
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    // [48.1374, 48.1374]
    fun onFetchClicked() {
        weatherUsecase(lat = 48.1374f, lon = 11.5755f)
            .onEach { _searchViewState.value = it }
            .launchIn(viewModelScope)
    }
}