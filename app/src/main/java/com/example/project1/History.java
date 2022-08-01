package com.example.project1;

public class History {
    String tvN, tvE, tvM, tvT, tvP, tvD;

    public History(){}

    public History(String tvN, String tvE, String tvM, String tvT, String tvP, String tvD) {
        this.tvN = tvN;
        this.tvE = tvE;
        this.tvM = tvM;
        this.tvT = tvT;
        this.tvP = tvP;
        this.tvD = tvD;
    }

    public String getTvN() {
        return tvN;
    }

    public void setTvN(String tvN) {
        this.tvN = tvN;
    }

    public String getTvE() {
        return tvE;
    }

    public void setTvE(String tvE) {
        this.tvE = tvE;
    }

    public String getTvM() {
        return tvM;
    }

    public void setTvM(String tvM) {
        this.tvM = tvM;
    }

    public String getTvT() {
        return tvT;
    }

    public void setTvT(String tvT) {
        this.tvT = tvT;
    }

    public String getTvP() {
        return tvP;
    }

    public void setTvP(String tvP) {
        this.tvP = tvP;
    }

    public String getTvD() {
        return tvD;
    }

    public void setTvD(String tvD) {
        this.tvD = tvD;
    }
}
