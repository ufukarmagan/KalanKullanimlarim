package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by UFUK on 21.6.2017.
 */

public class Customer {

// web servisten gelen json formatındaki veri alanlarına ayrılıp bu değişkenlerle eşleştirildi.

    @SerializedName("FIRST_NAME")
    @Expose
    private String fIRSTNAME;
    @SerializedName("LAST_NAME")
    @Expose
    private String lASTNAME;
    @SerializedName("TARIFF_NAME")
    @Expose
    private String tARIFFNAME;

    public String getFIRSTNAME() {
        return fIRSTNAME;
    }

    public void setFIRSTNAME(String fIRSTNAME) {
        this.fIRSTNAME = fIRSTNAME;
    }

    public String getLASTNAME() {
        return lASTNAME;
    }

    public void setLASTNAME(String lASTNAME) {
        this.lASTNAME = lASTNAME;
    }

    public String getTARIFFNAME() {
        return tARIFFNAME;
    }

    public void setTARIFFNAME(String tARIFFNAME) {
        this.tARIFFNAME = tARIFFNAME;
    }

}