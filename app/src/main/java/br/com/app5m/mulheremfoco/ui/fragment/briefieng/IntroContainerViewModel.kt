package br.com.app5m.mulheremfoco.ui.fragment.briefieng
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntroContainerViewModel() : ViewModel() {


    private val dataFristOpen: MutableLiveData<Boolean> = MutableLiveData(false)
    val fristOpen: LiveData<Boolean> = dataFristOpen


    fun isfristOpen() {
        dataFristOpen.value = false

    }
    fun notIsfristOpen() {
        dataFristOpen.value = true

    }




}
