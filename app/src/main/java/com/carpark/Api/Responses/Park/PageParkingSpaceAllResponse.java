package com.carpark.Api.Responses.Park;

import com.carpark.Model.Park.ParkingSpace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 03/10/19
 */

public class PageParkingSpaceAllResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("spacesPerPage")
    @Expose
    private SpacesPerPage spacesPerPage;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SpacesPerPage getSpacesPerPage() {
        return spacesPerPage;
    }

    public void setSpacesPerPage(SpacesPerPage spacesPerPage) {
        this.spacesPerPage = spacesPerPage;
    }



    public class SpacesPerPage {

        @SerializedName("current_page")
        @Expose
        private int currentPage;
        @SerializedName("data")
        @Expose
        private List<ParkingSpace> parkingSpaces = null;
        @SerializedName("first_page_url")
        @Expose
        private String firstPageUrl;
        @SerializedName("from")
        @Expose
        private int from;
        @SerializedName("last_page")
        @Expose
        private int lastPage;
        @SerializedName("last_page_url")
        @Expose
        private String lastPageUrl;
        @SerializedName("next_page_url")
        @Expose
        private String nextPageUrl;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private int perPage;
        @SerializedName("prev_page_url")
        @Expose
        private String prevPageUrl;
        @SerializedName("to")
        @Expose
        private int to;
        @SerializedName("total")
        @Expose
        private int total;

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<ParkingSpace> getParkingSpaces() {
            return parkingSpaces;
        }

        public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
            this.parkingSpaces = parkingSpaces;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public Object getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(String nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(String prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

    }

}
