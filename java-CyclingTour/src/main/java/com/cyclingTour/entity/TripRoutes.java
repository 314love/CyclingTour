package com.cyclingTour.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ren
 * @since 2023-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TripRoutes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String routeName;

    private String routeImageUrl;

    private String routeDetails;

    private Integer riderCount;

    private Integer routeRating;

    private String routeComments;


}
