package com.ry.manage.direct.model;

import com.sibecommon.ota.gds.Segment;
import com.sibecommon.ota.site.SibeSegment;
import lombok.Data;

import java.util.List;

@Data
public class SegmentInfo {

    private String validatingCarrier = "CZ";

    private List<SibeSegment> fromSegments;

    private List<SibeSegment> retSegments;

    /**
     * 直飞、中转、混合、共享、非共享
     */
    private List<String> flightType;

}
