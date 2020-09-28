package com.supanadit.restsuite.helper;
import com.supanadit.restsuite.model.RequestModel;
import java.util.ArrayList;
public class UrlParser {
    String url;

    protected final String http = "http://";

    protected final String https = "https://";

    protected final String www = "wwww.";

    public UrlParser(String url) {
        this.url = url;
    }

    public boolean isHttp() {
        System.out.println("isHttp -> length");
        System.out.println("isHttp -> length");
        boolean isValidHttp = false;
        if (this.url.length() >= this.http.length()) {
            System.out.println("isHttp -> length");
            System.out.println("isHttp -> substring");
            System.out.println("isHttp -> substring");
            isValidHttp = this.url.substring(0, this.http.length()).equals(this.http);
        }
        return isValidHttp;
    }

    public boolean isHttps() {
        System.out.println("isHttps -> length");
        System.out.println("isHttps -> length");
        boolean isValidHttps = false;
        if (this.url.length() >= this.https.length()) {
            System.out.println("isHttps -> length");
            System.out.println("isHttps -> substring");
            System.out.println("isHttps -> substring");
            isValidHttps = this.url.substring(0, this.https.length()).equals(this.https);
        }
        return isValidHttps;
    }

    public boolean isUseWWW() {
        System.out.println("isUseWWW -> isHttp");
        boolean isUserUrl = false;
        int index = -1;
        if (this.isHttp()) {
            System.out.println("isUseWWW -> length");
            index = this.http.length();
        } else {
            System.out.println("isUseWWW -> isHttps");
            if (this.isHttps()) {
                System.out.println("isUseWWW -> length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            System.out.println("isUseWWW -> length");
            System.out.println("isUseWWW -> length");
            index = index + (this.www.length() - 1);
            isUserUrl = this.url.length() > index;
        }
        return isUserUrl;
    }

    public boolean isHasCleanUrlTarget() {
        System.out.println("isHasCleanUrlTarget -> isHttp");
        boolean isHasUrl = false;
        int index = -1;
        if (this.isHttp()) {
            System.out.println("isHasCleanUrlTarget -> length");
            index = this.http.length();
        } else {
            System.out.println("isHasCleanUrlTarget -> isHttps");
            if (this.isHttps()) {
                System.out.println("isHasCleanUrlTarget -> length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            System.out.println("isHasCleanUrlTarget -> length");
            System.out.println("isHasCleanUrlTarget -> isUseWWW");
            if (this.isUseWWW()) {
                System.out.println("isHasCleanUrlTarget -> length");
                index = index + (this.www.length() - 1);
            }
            isHasUrl = this.url.length() > index;
        }
        return isHasUrl;
    }

    public boolean isHasDomain() {
        System.out.println("isHasDomain -> isHttp");
        boolean isHasDomain = false;
        int index = -1;
        if (this.isHttp()) {
            System.out.println("isHasDomain -> length");
            index = this.http.length();
        } else {
            System.out.println("isHasDomain -> isHttps");
            if (this.isHttps()) {
                System.out.println("isHasDomain -> length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            System.out.println("isHasDomain -> length");
            System.out.println("isHasDomain -> isUseWWW");
            if (this.isUseWWW()) {
                System.out.println("isHasDomain -> length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                System.out.println("isHasDomain -> split");
                System.out.println("isHasDomain -> length");
                System.out.println("isHasDomain -> substring");
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 2) {
                    isHasDomain = true;
                }
            }
        }
        return isHasDomain;
    }

    public boolean isValid() {
        System.out.println("isValid -> isHttps");
        System.out.println("isValid -> isHttp");
        boolean isValid = false;
        if (this.isHttp() || this.isHttps()) {
            System.out.println("isValid -> isHasCleanUrlTarget");
            if (this.isHasCleanUrlTarget()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public boolean hasQueryParams() {
        System.out.println("hasQueryParams -> isHttp");
        boolean isHasQueryParams = false;
        int index = -1;
        if (this.isHttp()) {
            System.out.println("hasQueryParams -> length");
            index = this.http.length();
        } else {
            System.out.println("hasQueryParams -> isHttps");
            if (this.isHttps()) {
                System.out.println("hasQueryParams -> length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            System.out.println("hasQueryParams -> length");
            System.out.println("hasQueryParams -> isUseWWW");
            if (this.isUseWWW()) {
                System.out.println("hasQueryParams -> length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                System.out.println("hasQueryParams -> split");
                System.out.println("hasQueryParams -> length");
                System.out.println("hasQueryParams -> substring");
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    System.out.println("hasQueryParams -> split");
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        System.out.println("hasQueryParams -> split");
                        int lengthLastPath = urlSplitPathDomain.length - 1;
                        String[] queryParams = urlSplitPathDomain[lengthLastPath].split("\\?");
                        if (queryParams.length >= 2) {
                            isHasQueryParams = true;
                        }
                    }
                }
            }
        }
        return isHasQueryParams;
    }

    public ArrayList<RequestModel> getQueryParams() {
        System.out.println("getQueryParams -> isHttp");
        ArrayList<RequestModel> requestModel = new ArrayList<>();
        int index = -1;
        if (this.isHttp()) {
            System.out.println("getQueryParams -> length");
            index = this.http.length();
        } else {
            System.out.println("getQueryParams -> isHttps");
            if (this.isHttps()) {
                System.out.println("getQueryParams -> length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            System.out.println("getQueryParams -> length");
            System.out.println("getQueryParams -> isUseWWW");
            if (this.isUseWWW()) {
                System.out.println("getQueryParams -> length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                System.out.println("getQueryParams -> split");
                System.out.println("getQueryParams -> length");
                System.out.println("getQueryParams -> substring");
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    System.out.println("getQueryParams -> split");
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        System.out.println("getQueryParams -> split");
                        int lengthLastPath = urlSplitPathDomain.length - 1;
                        String[] queryParams = urlSplitPathDomain[lengthLastPath].split("\\?");
                        if (queryParams.length >= 2) {
                            System.out.println("getQueryParams -> split");
                            String[] params = queryParams[1].split("&");
                            for (int i = 0; i < params.length; i++) {
                                System.out.println("getQueryParams -> add");
                                System.out.println("getQueryParams -> split");
                                String[] keyValue = params[i].split("=");
                                RequestModel requestModelData;
                                if (keyValue.length > 1) {
                                    requestModelData = new RequestModel(keyValue[0], keyValue[1]);
                                } else {
                                    requestModelData = new RequestModel(keyValue[0], "");
                                }
                                requestModel.add(requestModelData);
                            }
                        }
                    }
                }
            }
        }
        return requestModel;
    }
}