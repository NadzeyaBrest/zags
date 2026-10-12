package zags.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseForAdmin {
    private Integer applicantid;
    private Integer applicationid;
    private Integer citizenid;
    private String dateofapplication;
    private String kindofapplication;
    private String statusofapplication;
    private Integer staffid;
    private String channel;
    private String image;
}
