package foop.fooper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.NonNull

class ViewModelHomePage(@NonNull application: Application) : AndroidViewModel(application) {


    var listLiveData: MutableLiveData<List<HomeCardEntity>>

    init {
        listLiveData = MutableLiveData()
    }


}