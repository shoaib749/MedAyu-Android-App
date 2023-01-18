package com.unicon.mini;

public class DataHolder {
    String result,download;
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public DataHolder(String result, String download) {
        this.result = result;
        this.download = download;
    }
}
