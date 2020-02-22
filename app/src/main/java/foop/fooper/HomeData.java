package foop.fooper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeData {

    @SerializedName("dynamic_home_page")
    @Expose
    private List<HomeCardEntity> dynamicHomePage = null;

    public List<HomeCardEntity> getDynamicHomePage() {
        return dynamicHomePage;
    }

    public void setDynamicHomePage(List<HomeCardEntity> dynamicHomePage) {
        this.dynamicHomePage = dynamicHomePage;
    }


}
