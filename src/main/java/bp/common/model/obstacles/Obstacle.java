package bp.common.model.obstacles;


import bp.common.model.AttributeTypes;
import bp.common.model.ObstacleTypes;
import bp.common.model.annotations.EditableAttribute;
import bp.common.model.ways.Node;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bi on 18.05.2017.
 */
@Entity
@Table(name = "OBSTACLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OBSTACLE_TYPE", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dummy")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Construction.class, name = "construction"),
        @JsonSubTypes.Type(value = Elevator.class, name = "elevator"),
        @JsonSubTypes.Type(value = FastTrafficLight.class, name = "fasttrafficlight"),
        @JsonSubTypes.Type(value = Stairs.class, name = "stairs"),
        @JsonSubTypes.Type(value = TightPassage.class, name = "tightpassage"),
        @JsonSubTypes.Type(value = Unevenness.class, name = "unevenness")})
public abstract class Obstacle {
    boolean alreadyExported = false;

    @EditableAttribute(name = "Name", type = AttributeTypes.TEXT)
    public String mName;

    // Coordinates of the starting point of the obstacle
    public double longitude_start = 49.874978;
    public double latitude_start = 8.655971;

    // Coordinates of the endpoint of the obstacle 
    public double longitude_end;
    public double latitude_end;
        
    /**
     * osm id of the way this obstacle is on
     */
    public long id_way;
    /**
     * osm id of the starting point from the stair
     */
    public long osm_id_start;
    public long osm_id_end;
    /**
     * 2 ids of 2 nodes which this Obstacle lies between
     */
    public long id_firstnode;
    public long id_lastnode;

    /**
     * List of all nodes that lies between the 2 Obstacles Nodes
     * the 2 Obstacles Nodes included
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "obstacle",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("nodeToObstacle")
    private List<Node> nodes;

    @TableGenerator(name = "OBSTACLE_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OBSTACLE_GEN")
    private long id;


    public Obstacle() {

    }

    public Obstacle(String mName, double longitude_start, double latitude_start, double longitude_end, double latitude_end) {
        this.mName = mName;
        this.longitude_start = longitude_start;
        this.latitude_start = latitude_start;
        this.longitude_end = longitude_end;
        this.latitude_end = latitude_end;
    }

    /**
     *
     * @return the hibernate DB ID of this Obstacle
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id the hibernate DB ID of this Obstacle
     */
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public abstract ObstacleTypes getTypeCode();


    public double getLatitudeStart() {
        return latitude_start;
    }

    public void setLatitudeStart(double latitude) {
        this.latitude_start = latitude;
    }

    public double getLongitudeStart() {
        return longitude_start;
    }

    public void setLongitudeStart(double longitude) {
        this.longitude_start = longitude;
    }
        
    public double getLatitudeEnd() {
        return latitude_end;
    }

    public void setLatitudeEnd(double latitude) {
        this.latitude_end = latitude;
    }

    public double getLongitudeEnd() {
        return longitude_end;
    }

    public void setLongitudeEnd(double longitude) {
        this.longitude_end = longitude;
    }

    /**
     *
     * @return osm ID of the way the Obstacle lies on
     */
    public long getId_way() {
        return id_way;
    }

    /**
     *
     * @param id_way osm ID of the way the Obstacle lies on
     */
    public void setId_way(long id_way) {
        this.id_way = id_way;
    }

    /**
     * @return id 1 of the 2 nodes which this Obstacle lies between
     */
    public long getId_firstnode() {
        return id_firstnode;
    }

    /**
     *
     * @param id_firstnode set id 1 of the 2 nodes which this Obstacle lies between
     */
    public void setId_firstnode(long id_firstnode) {
        this.id_firstnode = id_firstnode;
    }

    /**
     * @return id 1 of the 2 nodes which this Obstacle lies between
     */
    public long getId_lastnode() {
        return id_lastnode;
    }

    /**
     * set id 1 of the 2 nodes which this Obstacle lies between
     * @param id_lastnode
     */
    public void setId_lastnode(long id_lastnode) {
        this.id_lastnode = id_lastnode;
    }

    /**
     *
     * @return osm id of the starting point from the stair
     */
    public long getOsm_id_start() {
        return osm_id_start;
    }

    /**
     *
     * @param osm_id_start set osm id of the starting point from the stair
     */
    public void setOsm_id_start(long osm_id_start) {
        this.osm_id_start = osm_id_start;
    }

    /**
     *
     * @return osm id of the ending point from the stair
     */
    public long getOsm_id_end() {
        return osm_id_end;
    }

    /**
     *
     * @param osm_id_end set osm id of the ending point from the stair
     */
    public void setOsm_id_end(long osm_id_end) {
        this.osm_id_end = osm_id_end;
    }

    /**
     * is this Obstacle already prepared for ExportTool ?
     * @return
     */
    public boolean isAlreadyExported() {
        return alreadyExported;
    }

    public void setAlreadyExported(boolean alreadyExported) {
        this.alreadyExported = alreadyExported;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
