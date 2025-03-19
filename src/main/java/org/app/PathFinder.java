package org.app;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.*;
import com.graphhopper.util.shapes.GHPoint;

import java.util.Locale;

public class PathFinder{

    private final GraphHopper graphHopper;

    public PathFinder(){
        graphHopper = new GraphHopper()
                .setOSMFile("italy-latest.osm.pbf")
                .setGraphHopperLocation("graph-cache")
                .setEncodedValuesString("car_access, car_average_speed, road_access")
                .setProfiles(new Profile("car").setVehicle("car").setWeighting("fastest"));

        graphHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));

        graphHopper.importOrLoad();
    }

    public PathDto findPath(GHPoint startPoint, GHPoint endPoint){
        GHRequest request = new GHRequest(startPoint, endPoint)
                .setProfile("car")
                .setLocale(Locale.ITALY);

        GHResponse response = graphHopper.route(request);

        if(response.hasErrors()){
            throw new RuntimeException("Error finding path: " + response.getErrors());
        }

        ResponsePath path = response.getBest();

        Translation tr = graphHopper.getTranslationMap().getWithFallBack(Locale.ITALY);
        InstructionList instructionList = path.getInstructions();

        for(Instruction ins : instructionList){
            System.out.println("Tra " + Math.floor(ins.getDistance()) + " metri  " + ins.getTurnDescription(tr));
        }

        assert instructionList.size() == 6;
        assert Helper.round(path.getDistance(), -2) == 600;

        return new PathDto(path.getDistance(), path.getTime(), path.getPoints(), tr, path.getInstructions());
    }

    public void close(){
        graphHopper.close();
    }
}
