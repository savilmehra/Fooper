package foop.fooper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeCardEntity implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("class_name")
    @Expose
    private String className;
    @SerializedName("card_action")
    @Expose
    private String cardAction;
    @SerializedName("main_image")
    @Expose
    private String mainImage;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("template_id")
    @Expose
    private String templateId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_color")
    @Expose
    private String titleColor;
    @SerializedName("title_image")
    @Expose
    private String titleImage;
    @SerializedName("sub_title")
    @Expose
    private String subTitle;
    @SerializedName("subtitle_color")
    @Expose
    private String subtitleColor;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_color")
    @Expose
    private String descriptionColor;
    @SerializedName("action1_text")
    @Expose
    private String action1Text;
    @SerializedName("action2_text")
    @Expose
    private String action2Text;
    @SerializedName("action1_color")
    @Expose
    private String action1Color;
    @SerializedName("action2_color")
    @Expose
    private String action2Color;
    @SerializedName("action1_dplink")
    @Expose
    private String action1Dplink;
    @SerializedName("action2_dplink")
    @Expose
    private String action2Dplink;
    @SerializedName("subtitle_image")
    @Expose
    private String subtitleImage;
    @SerializedName("action3_text")
    @Expose
    private String action3Text;
    @SerializedName("action4_text")
    @Expose
    private String action4Text;
    @SerializedName("action3_color")
    @Expose
    private String action3Color;
    @SerializedName("action4_color")
    @Expose
    private String action4Color;
    @SerializedName("action3_dplink")
    @Expose
    private String action3Dplink;
    @SerializedName("action4_dplink")
    @Expose
    private String action4Dplink;
    @SerializedName("action_image")
    @Expose
    private String actionImage;
    @SerializedName("main_url")
    @Expose
    private String mainUrl;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("subtitle_two")
    @Expose
    private String subtitleTwo;
    @SerializedName("description_two")
    @Expose
    private String descriptionTwo;
    @SerializedName("image_one")
    @Expose
    private String imageOne;
    @SerializedName("title_one")
    @Expose
    private String titleOne;
    @SerializedName("image_two")
    @Expose
    private String imageTwo;
    @SerializedName("subtitle_one")
    @Expose
    private String subtitleOne;
    @SerializedName("description_one")
    @Expose
    private String descriptionOne;
    @SerializedName("deeplink_1")
    @Expose
    private String deeplink1;
    @SerializedName("deeplink_2")
    @Expose
    private String deeplink2;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("ad_unit_id")
    @Expose
    private String adUnitId;
    @SerializedName("is_footer")
    @Expose
    private String isFooter;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("alert_id")
    @Expose
    private int alertId;
    @SerializedName("alert_msg")
    @Expose
    private String alertMsg;
    @SerializedName("is_local")
    @Expose
    private boolean isLocal = true;
    @SerializedName("package_detail_data")
    @Expose
    private String packageDetailData;
    @SerializedName("description_three")
    @Expose
    private String descriptionThree;
    @SerializedName("pnr_no")
    @Expose
    private String pnrNo;
    @SerializedName("description1_color")
    @Expose
    private String description1Color;
    @SerializedName("description2_color")
    @Expose
    private String description2Color;
    @SerializedName("description3_color")
    @Expose
    private String description3Color;
    @SerializedName("image_three")
    @Expose
    private String imageThree;

    @SerializedName("image_only")
    @Expose
    private String imageOnly;
    @SerializedName("image_length")
    @Expose
    private String imageLength;
    @SerializedName("image_width")
    @Expose
    private String imageWidth;
    @SerializedName("description_four")
    @Expose
    private String descriptionFour;
    @SerializedName("description4_color")
    @Expose
    private String description4Color;
    @SerializedName("title_two")
    @Expose
    private String titleTwo;
    @SerializedName("is_elevation")
    @Expose
    private String isElevation;
    @SerializedName("title_one_color")
    @Expose
    private String titleOneColor;
    @SerializedName("title_two_color")
    @Expose
    private String titleTwoColor;
    @SerializedName("subtitle_one_color")
    @Expose
    private String subtitleOneColor;
    @SerializedName("subtitle_two_color")
    @Expose
    private String subtitleTwoColor;
    @SerializedName("is_open")
    @Expose
    private String isOpen;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("vendor_id")
    @Expose
    private String vendorId;
    @SerializedName("station_code")
    @Expose
    private String stnCode;
    @SerializedName("eta_time")
    @Expose
    private String etaTime;
    @SerializedName("sta_time")
    @Expose
    private String staTime;

    @SerializedName("delay_min")
    @Expose
    private String delayMin;

    @SerializedName("perc_ontime")
    @Expose
    private int perctOnTime;

    @SerializedName("perc_delay")
    @Expose
    private int perctDelay;


    @SerializedName("perc_ontime_text")
    @Expose
    private String perctOnTimeText;


    @SerializedName("perc_delay_text")
    @Expose
    private String perctDelayText;


    @SerializedName("perc_ontime_color")
    @Expose
    private String perctOnTimeColor;


    @SerializedName("perc_delay_color")
    @Expose
    private String perctDelayColor;


    @SerializedName("progress_color")
    @Expose
    private String progressColor;


    @SerializedName("progress1_color")
    @Expose
    private String progressColor1;


    @SerializedName("sub_title2")
    @Expose
    private String subTitle2;


    @SerializedName("sub_title_text")
    @Expose
    private String subTitleText;

    @SerializedName("sub_title2_text")
    @Expose
    private String subTitleText2;

    @SerializedName("timer_count")
    @Expose
    private Integer timerCount;




    public String getPackageDetailData() {
        return packageDetailData;
    }

    public void setPackageDetailData(String packageDetailData) {
        this.packageDetailData = packageDetailData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCardAction() {
        return cardAction;
    }

    public void setCardAction(String cardAction) {
        this.cardAction = cardAction;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubtitleColor() {
        return subtitleColor;
    }

    public void setSubtitleColor(String subtitleColor) {
        this.subtitleColor = subtitleColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionColor() {
        return descriptionColor;
    }

    public void setDescriptionColor(String descriptionColor) {
        this.descriptionColor = descriptionColor;
    }

    public String getAction1Text() {
        return action1Text;
    }

    public void setAction1Text(String action1Text) {
        this.action1Text = action1Text;
    }

    public String getAction2Text() {
        return action2Text;
    }

    public void setAction2Text(String action2Text) {
        this.action2Text = action2Text;
    }

    public String getAction1Color() {
        return action1Color;
    }

    public void setAction1Color(String action1Color) {
        this.action1Color = action1Color;
    }

    public String getAction2Color() {
        return action2Color;
    }

    public void setAction2Color(String action2Color) {
        this.action2Color = action2Color;
    }

    public String getAction1Dplink() {
        return action1Dplink;
    }

    public void setAction1Dplink(String action1Dplink) {
        this.action1Dplink = action1Dplink;
    }

    public String getAction2Dplink() {
        return action2Dplink;
    }

    public void setAction2Dplink(String action2Dplink) {
        this.action2Dplink = action2Dplink;
    }

    public String getSubtitleImage() {
        return subtitleImage;
    }

    public void setSubtitleImage(String subtitleImage) {
        this.subtitleImage = subtitleImage;
    }

    public String getAction3Text() {
        return action3Text;
    }

    public void setAction3Text(String action3Text) {
        this.action3Text = action3Text;
    }

    public String getAction4Text() {
        return action4Text;
    }

    public void setAction4Text(String action4Text) {
        this.action4Text = action4Text;
    }

    public String getAction3Color() {
        return action3Color;
    }

    public void setAction3Color(String action3Color) {
        this.action3Color = action3Color;
    }

    public String getAction4Color() {
        return action4Color;
    }

    public void setAction4Color(String action4Color) {
        this.action4Color = action4Color;
    }

    public String getAction3Dplink() {
        return action3Dplink;
    }

    public void setAction3Dplink(String action3Dplink) {
        this.action3Dplink = action3Dplink;
    }

    public String getAction4Dplink() {
        return action4Dplink;
    }

    public void setAction4Dplink(String action4Dplink) {
        this.action4Dplink = action4Dplink;
    }

    public String getActionImage() {
        return actionImage;
    }

    public void setActionImage(String actionImage) {
        this.actionImage = actionImage;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getSubtitleTwo() {
        return subtitleTwo;
    }

    public void setSubtitleTwo(String subtitleTwo) {
        this.subtitleTwo = subtitleTwo;
    }

    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }

    public String getImageOne() {
        return imageOne;
    }

    public void setImageOne(String imageOne) {
        this.imageOne = imageOne;
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getImageTwo() {
        return imageTwo;
    }

    public void setImageTwo(String imageTwo) {
        this.imageTwo = imageTwo;
    }

    public String getSubtitleOne() {
        return subtitleOne;
    }

    public void setSubtitleOne(String subtitleOne) {
        this.subtitleOne = subtitleOne;
    }

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }

    public String getDeeplink1() {
        return deeplink1;
    }

    public void setDeeplink1(String deeplink1) {
        this.deeplink1 = deeplink1;
    }

    public String getDeeplink2() {
        return deeplink2;
    }

    public void setDeeplink2(String deeplink2) {
        this.deeplink2 = deeplink2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdUnitId() {
        return adUnitId;
    }

    public void setAdUnitId(String adUnitId) {
        this.adUnitId = adUnitId;
    }

    public String isFooter() {
        return isFooter;
    }

    public void setFooter(String footer) {
        isFooter = footer;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }

    public String getDescriptionThree() {
        return descriptionThree;
    }

    public void setDescriptionThree(String descriptionThree) {
        this.descriptionThree = descriptionThree;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getDescription1Color() {
        return description1Color;
    }

    public void setDescription1Color(String description1Color) {
        this.description1Color = description1Color;
    }

    public String getDescription2Color() {
        return description2Color;
    }

    public void setDescription2Color(String description2Color) {
        this.description2Color = description2Color;
    }

    public String getDescription3Color() {
        return description3Color;
    }

    public void setDescription3Color(String description3Color) {
        this.description3Color = description3Color;
    }

    public String getImageThree() {
        return imageThree;
    }

    public void setImageThree(String imageThree) {
        this.imageThree = imageThree;
    }



    public String getImageOnly() {
        return imageOnly;
    }

    public void setImageOnly(String imageOnly) {
        this.imageOnly = imageOnly;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getDescriptionFour() {
        return descriptionFour;
    }

    public void setDescriptionFour(String descriptionFour) {
        this.descriptionFour = descriptionFour;
    }

    public String getDescription4Color() {
        return description4Color;
    }

    public void setDescription4Color(String description4Color) {
        this.description4Color = description4Color;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getIsElevation() {
        return isElevation;
    }

    public void setIsElevation(String isElevation) {
        this.isElevation = isElevation;
    }

    public String getTitleOneColor() {
        return titleOneColor;
    }

    public void setTitleOneColor(String titleOneColor) {
        this.titleOneColor = titleOneColor;
    }

    public String getTitleTwoColor() {
        return titleTwoColor;
    }

    public void setTitleTwoColor(String titleTwoColor) {
        this.titleTwoColor = titleTwoColor;
    }

    public String getSubtitleOneColor() {
        return subtitleOneColor;
    }

    public void setSubtitleOneColor(String subtitleOneColor) {
        this.subtitleOneColor = subtitleOneColor;
    }

    public String getSubtitleTwoColor() {
        return subtitleTwoColor;
    }

    public void setSubtitleTwoColor(String subtitleTwoColor) {
        this.subtitleTwoColor = subtitleTwoColor;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getStnCode() {
        return stnCode;
    }

    public void setStnCode(String stnCode) {
        this.stnCode = stnCode;
    }

    public String getEtaTime() {
        return etaTime;
    }

    public void setEtaTime(String etaTime) {
        this.etaTime = etaTime;
    }

    public String getStaTime() {
        return staTime;
    }

    public void setStaTime(String staTime) {
        this.staTime = staTime;
    }

    public String getDelayMin() {
        return delayMin;
    }

    public void setDelayMin(String delayMin) {
        this.delayMin = delayMin;
    }

    public int getPerctOnTime() {
        return perctOnTime;
    }

    public void setPerctOnTime(int perctOnTime) {
        this.perctOnTime = perctOnTime;
    }

    public int getPerctDelay() {
        return perctDelay;
    }

    public void setPerctDelay(int perctDelay) {
        this.perctDelay = perctDelay;
    }

    public String getPerctOnTimeText() {
        return perctOnTimeText;
    }

    public void setPerctOnTimeText(String perctOnTimeText) {
        this.perctOnTimeText = perctOnTimeText;
    }

    public String getPerctDelayText() {
        return perctDelayText;
    }

    public void setPerctDelayText(String perctDelayText) {
        this.perctDelayText = perctDelayText;
    }

    public String getPerctOnTimeColor() {
        return perctOnTimeColor;
    }

    public void setPerctOnTimeColor(String perctOnTimeColor) {
        this.perctOnTimeColor = perctOnTimeColor;
    }

    public String getPerctDelayColor() {
        return perctDelayColor;
    }

    public void setPerctDelayColor(String perctDelayColor) {
        this.perctDelayColor = perctDelayColor;
    }

    public String getProgressColor() {
        return progressColor;
    }

    public void setProgressColor(String progressColor) {
        this.progressColor = progressColor;
    }

    public String getProgressColor1() {
        return progressColor1;
    }

    public void setProgressColor1(String progressColor1) {
        this.progressColor1 = progressColor1;
    }

    public String getSubTitle2() {
        return subTitle2;
    }

    public void setSubTitle2(String subTitle2) {
        this.subTitle2 = subTitle2;
    }

    public String getSubTitleText() {
        return subTitleText;
    }

    public void setSubTitleText(String subTitleText) {
        this.subTitleText = subTitleText;
    }

    public String getSubTitleText2() {
        return subTitleText2;
    }

    public void setSubTitleText2(String subTitleText2) {
        this.subTitleText2 = subTitleText2;
    }

    public Integer getTimerCount() {
        return timerCount;
    }

    public void setTimerCount(Integer timerCount) {
        this.timerCount = timerCount;
    }

}