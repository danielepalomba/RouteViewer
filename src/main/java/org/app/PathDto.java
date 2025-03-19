package org.app;

import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;

public class PathDto {
    private double len;
    private double time;
    PointList points;
    Translation translation;
    InstructionList instructions;

    public PathDto(double len, double time, PointList points, Translation translation, InstructionList instructions) {
        this.len = len;
        this.time = time;
        this.points = points;
        this.translation = translation;
        this.instructions = instructions;
    }

    public double getLen() {
        return len;
    }

    public double getTime() {
        return time;
    }

    public PointList getPoints() {
        return points;
    }

    public Translation getTranslation() {
        return translation;
    }

    public InstructionList getInstructions() {
        return instructions;
    }

}
