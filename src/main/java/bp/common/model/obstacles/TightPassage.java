package bp.common.model.obstacles;

import bp.common.model.IObstacle;
import bp.common.model.ObstacleTypes;
import bp.common.model.annotations.EditableAttribute;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Bi Banh Bao on 18.05.2017.
 * this class represents tight passgage through which disable people with wheelchair
 * are not able to pass.
 */
@Entity
@DiscriminatorValue(value = "tightpassage")

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")

public class TightPassage extends Obstacle implements IObstacle {

    public ObstacleTypes typeCode = ObstacleTypes.TIGHT_PASSAGE;
    /**
     * the mWidth of the passage
     */
    @EditableAttribute("Passage mWidth")
    private double mWidth;

    public TightPassage() {
    }

    public TightPassage(String name, double longitude, double latitude, double width, boolean smallerThan90cm) {
        super(name, longitude, latitude);
        this.mWidth = width;
    }

    @Override
    public ObstacleTypes getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return "Tight Passage";
    }

    public double getWidth() {
        return mWidth;
    }

    public void setWidth(double mWidth) {
        this.mWidth = mWidth;
    }

}
