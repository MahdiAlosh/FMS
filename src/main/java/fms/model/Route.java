package fms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.awt.*;
import java.util.ArrayList;

@Entity
@Table(name = "Route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;
    @Column(name = "Route_start")
    private String startRoute;
    @Column(name = "Route_end")
    private String endRouter;

    @JsonBackReference
    @OneToOne(mappedBy = "route", cascade = CascadeType.ALL)
    private Mission mission;

//    private Point xy;
    // Arraylist
    ArrayList<Point> pointList = new ArrayList<>();

    //**************************
    protected Route(){}
    public Route(Integer routeId, String startRoute, String endRouter){
        this.routeId = routeId;
        this.startRoute = startRoute;
        this.endRouter = endRouter;
    }

    @Override
    public String toString(){
        return String.format("Route: %03d, Abfahrtort: %s, Zielort: %s", getRouteId(),getStartRoute(),getEndRouter());
//        return String.format("Route: %03d, Abfahrtort: %s, Zielort: %s, Standort: (%d,%d)", getRouteId(),getStartRoute(),getEndRouter(), getXy().x, getXy().y);
    }
    public Integer getRouteId() {
        return routeId;
    }
    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getStartRoute() {
        return startRoute;
    }
    public void setStartRoute(String startRoute) {
        this.startRoute = startRoute;
    }

    public String getEndRouter() {
        return endRouter;
    }
    public void setEndRouter(String endRouter) {
        this.endRouter = endRouter;
    }

    // für Mission
    public Mission getMission() {
        return mission;
    }
    public void setMission(Mission mission) {
        this.mission = mission;
    }

    // für PointList
    public ArrayList<Point> getPointList() {
        return pointList;
    }
    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    // Location in die Liste hinzufügen
    public void addPoint(Point point){
        pointList.add(point);
    }
    public void removePoint(Point point){
        pointList.remove(point);
    }
}
