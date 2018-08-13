package app.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tlatz on 7/31/2018.
 */
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;

    @ManyToMany
    private List<Type> type;
    private String price;
    private String date;
    private String startTime;
    private String endTime;


}
