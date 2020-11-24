package plane;

import model.ClassificationLevel;
import model.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends Plane {
    private final ExperimentalTypes type;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity,
                             ExperimentalTypes type, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalTypes getExperimentalPlaneType() { return type; }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    @Override public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override public int hashCode() {
        return Objects.hash(type, classificationLevel);
    }

    @Override public String toString() { return String.format("experimentalPlane{ model=\'%s\' }", model); }
}
