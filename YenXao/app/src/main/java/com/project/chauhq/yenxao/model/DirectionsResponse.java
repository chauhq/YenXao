package com.project.chauhq.yenxao.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author ChauHQ
 */
@Data
public class DirectionsResponse {
    private String status;
    @SerializedName("routes")
    private List<Route> routes = new ArrayList<>();

    @Data
    public class Route {
        private List<Leg> legs = new ArrayList<>();
        @SerializedName("overview_polyline")
        private OverviewPolyline overviewpolyline;

        @Data
        public class OverviewPolyline {
            @SerializedName("points")
            private String point;
        }

        @Data
        public class Leg {
            @SerializedName("steps")
            private List<Step> steps = new ArrayList<>();

            @Data
            public class Step {
                @SerializedName("start_location")
                private StartLocation startLocation;
                @SerializedName("end_location")
                private EndLocation endLocation;
                private Polyline polyline;

                @Data
                public class StartLocation {
                    private String lat;
                    private String lng;
                }

                @Data
                public class EndLocation {
                    private String lat;
                    private String lng;
                }

                public class Polyline {
                    @SerializedName("points")
                    private String point;
                }
            }
        }

    }
}
