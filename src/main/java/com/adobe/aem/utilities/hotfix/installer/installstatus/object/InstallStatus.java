package com.adobe.aem.utilities.hotfix.installer.installstatus.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstallStatus {

    @SerializedName("status")
    @Expose
    private Status status;

    /**
     *
     * @return
     * The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}