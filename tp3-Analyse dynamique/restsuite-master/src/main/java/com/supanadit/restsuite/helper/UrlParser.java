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
        com.supanadit.restsuite.logger.LogWriter.out("isHttp", "length");
        com.supanadit.restsuite.logger.LogWriter.out("isHttp", "length");
        boolean isValidHttp = false;
        if (this.url.length() >= this.http.length()) {
            com.supanadit.restsuite.logger.LogWriter.out("isHttp", "length");
            com.supanadit.restsuite.logger.LogWriter.out("isHttp", "substring");
            com.supanadit.restsuite.logger.LogWriter.out("isHttp", "substring");
            isValidHttp = this.url.substring(0, this.http.length()).equals(this.http);
        }
        return isValidHttp;
    }

    public boolean isHttps() {
        com.supanadit.restsuite.logger.LogWriter.out("isHttps", "length");
        com.supanadit.restsuite.logger.LogWriter.out("isHttps", "length");
        boolean isValidHttps = false;
        if (this.url.length() >= this.https.length()) {
            com.supanadit.restsuite.logger.LogWriter.out("isHttps", "length");
            com.supanadit.restsuite.logger.LogWriter.out("isHttps", "substring");
            com.supanadit.restsuite.logger.LogWriter.out("isHttps", "substring");
            isValidHttps = this.url.substring(0, this.https.length()).equals(this.https);
        }
        return isValidHttps;
    }

    public boolean isUseWWW() {
        com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "isHttp");
        boolean isUserUrl = false;
        int index = -1;
        if (this.isHttp()) {
            com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "length");
            index = this.http.length();
        } else {
            com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "isHttps");
            if (this.isHttps()) {
                com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "length");
            com.supanadit.restsuite.logger.LogWriter.out("isUseWWW", "length");
            index = index + (this.www.length() - 1);
            isUserUrl = this.url.length() > index;
        }
        return isUserUrl;
    }

    public boolean isHasCleanUrlTarget() {
        com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "isHttp");
        boolean isHasUrl = false;
        int index = -1;
        if (this.isHttp()) {
            com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "length");
            index = this.http.length();
        } else {
            com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "isHttps");
            if (this.isHttps()) {
                com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "length");
            com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "isUseWWW");
            if (this.isUseWWW()) {
                com.supanadit.restsuite.logger.LogWriter.out("isHasCleanUrlTarget", "length");
                index = index + (this.www.length() - 1);
            }
            isHasUrl = this.url.length() > index;
        }
        return isHasUrl;
    }

    public boolean isHasDomain() {
        com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "isHttp");
        boolean isHasDomain = false;
        int index = -1;
        if (this.isHttp()) {
            com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "length");
            index = this.http.length();
        } else {
            com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "isHttps");
            if (this.isHttps()) {
                com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "length");
            com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "isUseWWW");
            if (this.isUseWWW()) {
                com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "split");
                com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "length");
                com.supanadit.restsuite.logger.LogWriter.out("isHasDomain", "substring");
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
        com.supanadit.restsuite.logger.LogWriter.out("isValid", "isHttps");
        com.supanadit.restsuite.logger.LogWriter.out("isValid", "isHttp");
        boolean isValid = false;
        if (this.isHttp() || this.isHttps()) {
            com.supanadit.restsuite.logger.LogWriter.out("isValid", "isHasCleanUrlTarget");
            if (this.isHasCleanUrlTarget()) {
                isValid = true;
            }
        }
        return isValid;
    }

    public boolean hasQueryParams() {
        com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "isHttp");
        boolean isHasQueryParams = false;
        int index = -1;
        if (this.isHttp()) {
            com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "length");
            index = this.http.length();
        } else {
            com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "isHttps");
            if (this.isHttps()) {
                com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "length");
            com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "isUseWWW");
            if (this.isUseWWW()) {
                com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "split");
                com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "length");
                com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "substring");
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "split");
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        com.supanadit.restsuite.logger.LogWriter.out("hasQueryParams", "split");
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
        com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "isHttp");
        ArrayList<RequestModel> requestModel = new ArrayList<>();
        int index = -1;
        if (this.isHttp()) {
            com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "length");
            index = this.http.length();
        } else {
            com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "isHttps");
            if (this.isHttps()) {
                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "length");
                index = this.https.length();
            }
        }
        if (index != (-1)) {
            com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "length");
            com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "isUseWWW");
            if (this.isUseWWW()) {
                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "length");
                index = index + (this.www.length() - 1);
            }
            if (this.url.length() > index) {
                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "split");
                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "length");
                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "substring");
                String url = this.url.substring(index, this.url.length());
                String[] urlSplit = url.split("\\.");
                if (urlSplit.length >= 1) {
                    com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "split");
                    int lengthUrlPathDomain = urlSplit.length - 1;
                    String urlPathDomain = urlSplit[lengthUrlPathDomain];
                    String[] urlSplitPathDomain = urlPathDomain.split("/");
                    if (urlSplitPathDomain.length != 0) {
                        com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "split");
                        int lengthLastPath = urlSplitPathDomain.length - 1;
                        String[] queryParams = urlSplitPathDomain[lengthLastPath].split("\\?");
                        if (queryParams.length >= 2) {
                            com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "split");
                            String[] params = queryParams[1].split("&");
                            for (int i = 0; i < params.length; i++) {
                                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "add");
                                com.supanadit.restsuite.logger.LogWriter.out("getQueryParams", "split");
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