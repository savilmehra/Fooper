package foop.fooper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.annotation.NonNull

class ViewModelHomePage(@NonNull application: Application) : AndroidViewModel(application) {


    var listLiveData: MutableLiveData<List<HomeCardEntity>>

    init {
        listLiveData = MutableLiveData()
    }


}