package com.deshaware.shuttleservice.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shuttle {

    /**
     * A plate number or a vehicle registration number would suffice this requirement
     * 
     */
    @Id
    private String shuttle_id;  

    /**
     * Shuttle types are enum so that in future any type of vehcile 
     * can be added
     */
    @Enumerated(EnumType.ORDINAL)
    private Vehicle shuttle_type;

    // Shuttle Description
    /**
     * Optional field contains descriptory informatio
     */
    private String shuttle_desc;

    /**
     * Important as it decided capactiy
     */
    @Min(value = 1, message = "The value must be positive")
    private int capacity;
    private Instant modified;
    private boolean enabled;

    public String toJSONString(){
        return 
        "{\"shuttle_id\" : " + this.shuttle_id+ ", {\"shuttle_desc\" : " + 
        this.shuttle_desc+ ", {\"capacity\" : " + this.capacity 
        + ", {\"shuttle_type\" : " + this.shuttle_type+ "}";
    }

}
