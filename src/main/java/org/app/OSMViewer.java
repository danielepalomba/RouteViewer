package org.app;

import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;
import org.app.gui.DataPanel;
import org.app.gui.InfoPanel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OSMViewer {

    private final JMapViewer map;
    private final PathFinder pathFinder;
    private Window window;

    public OSMViewer() {
        map = new JMapViewer();
        map.setTileSource(new OsmTileSource.Mapnik());

        pathFinder = new PathFinder();

        SwingUtilities.invokeLater(() -> window = new org.app.gui.Window());
    }

    public void calculatePath(double lat1, double lon1, double lat2, double lon2) {
        PathDto pathData = pathFinder.findPath(new GHPoint(lat1, lon1), new GHPoint(lat2, lon2));

        populateMap(pathData.getPoints());
        addMapAndDataToWindow(pathData);
        setFocusOnStartPoint(lat1, lon1);
    }

    private void populateMap(PointList points) {
        List<Coordinate> pathCoordinates = new ArrayList<>();
        for(int i = 0; i < points.size(); i++) {
            GHPoint point = points.get(i);
            Coordinate coordinate = new Coordinate(point.getLat(), point.getLon());
            pathCoordinates.add(coordinate);

            map.addMapMarker(new MapMarkerDot(coordinate));
        }
    }

    private void setFocusOnStartPoint(double lat1, double lon1){
        map.setDisplayPosition(new Coordinate(lat1, lon1), 12);
    }

    public void closeOSMViewer(){
        pathFinder.close();
    }

    private void addMapAndDataToWindow(PathDto pathData){
        window.add(map, BorderLayout.CENTER);
        window.add(new DataPanel(pathData.getLen(), pathData.getTime()), BorderLayout.SOUTH);
        window.add(new InfoPanel(pathData.getInstructions(), pathData.getTranslation()), BorderLayout.EAST);
        window.setVisible(true);
    }

    public static void main(String[] args) {

        OSMViewer viewer = new OSMViewer();
        viewer.calculatePath(40.843063,14.286132,40.7494407,14.5058401); //test1
        //viewer.calculatePath(40.619062,14.3821672,40.7494407,14.5058401); //test2

        viewer.closeOSMViewer();
    }
}

