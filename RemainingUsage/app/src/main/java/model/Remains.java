package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by UFUK on 22.6.2017.
 */

public class Remains {

    @SerializedName("LAST_READ")
    @Expose
    private String  lAST_READ;

    @SerializedName("REMAIN_DATA")
    @Expose
    private String  rEMAIN_DATA;

    @SerializedName("REMAIN_VOICE")
    @Expose
    private String  rEMAIN_VOICE;

    @SerializedName("REMAIN_SMS")
    @Expose
    private String  rEMAIN_SMS;

    @SerializedName("USABLE_DATA")
    @Expose
    private String  uSABLE_DATA;

    @SerializedName("USABLE_VOICE")
    @Expose
    private String  uSABLE_VOICE;

    @SerializedName("USABLE_SMS")
    @Expose
    private String  uSABLE_SMS;

    public String getlAST_READ() {return lAST_READ; }

    public void setlAST_READ(String lAST_READ) {this.lAST_READ = lAST_READ;}

    public String getrEMAIN_DATA() {
        return rEMAIN_DATA;
    }

    public void setrEMAIN_DATA(String rEMAIN_DATA) {
        this.rEMAIN_DATA = rEMAIN_DATA;
    }

    public String getrEMAIN_VOICE() {
        return rEMAIN_VOICE;
    }

    public void setrEMAIN_VOICE(String rEMAIN_VOICE) {
        this.rEMAIN_VOICE = rEMAIN_VOICE;
    }

    public String getrEMAIN_SMS() {
        return rEMAIN_SMS;
    }

    public void setrEMAIN_SMS(String rEMAIN_SMS) {
        this.rEMAIN_SMS = rEMAIN_SMS;
    }

    public String getuSABLE_DATA() {
        return uSABLE_DATA;
    }

    public void setuSABLE_DATA(String uSABLE_DATA) {
        this.uSABLE_DATA = uSABLE_DATA;
    }

    public String getuSABLE_VOICE() {
        return uSABLE_VOICE;
    }

    public void setuSABLE_VOICE(String uSABLE_VOICE) {
        this.uSABLE_VOICE = uSABLE_VOICE;
    }

    public String getuSABLE_SMS() {
        return uSABLE_SMS;
    }

    public void setuSABLE_SMS(String uSABLE_SMS) {
        this.uSABLE_SMS = uSABLE_SMS;
    }
}
