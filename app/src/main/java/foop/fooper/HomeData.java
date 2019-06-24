package foop.fooper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeData {
    @SerializedName("spent")
    @Expose
    String spent;
    @SerializedName("fodd_available")
    @Expose
    boolean foodAvailable;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("pnr_no")
    @Expose
    private String pnrNo;
    @SerializedName("dest_reached_time")
    @Expose
    private String destReachedTime;
    @SerializedName("alert_key")
    @Expose
    private String alertKey;
    @SerializedName("alert_deeplink")
    @Expose
    private String alertDeeplink;
    @SerializedName("journey_case")
    @Expose
    private int journeyCase;
    @SerializedName("dynamic_home_page")
    @Expose
    private List<HomeCardEntity> dynamicHomePage = null;
    @SerializedName("cards")
    @Expose
    private List<HomeCardEntity> dynamicFoodHomePage = null;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("station_name")
    @Expose
    private String stnName;

    public List<HomeCardEntity> getDynamicHomePage() {
        return dynamicHomePage;
    }

    public void setDynamicHomePage(List<HomeCardEntity> dynamicHomePage) {
        this.dynamicHomePage = dynamicHomePage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getDestReachedTime() {
        return destReachedTime;
    }

    public void setDestReachedTime(String destReachedTime) {
        this.destReachedTime = destReachedTime;
    }

    public String getAlertKey() {
        return alertKey;
    }

    public void setAlertKey(String alertKey) {
        this.alertKey = alertKey;
    }

    public int getJourneyCase() {
        return journeyCase;
    }

    public void setJourneyCase(int journeyCase) {
        this.journeyCase = journeyCase;
    }

    public String getAlertDeeplink() {
        return alertDeeplink;
    }

    public void setAlertDeeplink(String alertDeeplink) {
        this.alertDeeplink = alertDeeplink;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        this.spent = spent;
    }

    public List<HomeCardEntity> getDynamicFoodHomePage() {
        return dynamicFoodHomePage;
    }

    public void setDynamicFoodHomePage(List<HomeCardEntity> dynamicFoodHomePage) {
        this.dynamicFoodHomePage = dynamicFoodHomePage;
    }

    public String getStnName() {
        return stnName;
    }

    public void setStnName(String stnName) {
        this.stnName = stnName;
    }

    public boolean isFoodAvailable() {
        return foodAvailable;
    }

    public void setFoodAvailable(boolean foodAvailable) {
        this.foodAvailable = foodAvailable;
    }
}
