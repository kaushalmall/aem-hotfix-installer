package com.adobe.aem.utilities.hotfix.installer.installstatus.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("finished")
    @Expose
    private Boolean finished;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;

    /**
     *
     * @return
     * The finished
     */
    public Boolean getFinished() {
        return finished;
    }

    /**
     *
     * @param finished
     * The finished
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     *
     * @return
     * The itemCount
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     *
     * @param itemCount
     * The itemCount
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

}