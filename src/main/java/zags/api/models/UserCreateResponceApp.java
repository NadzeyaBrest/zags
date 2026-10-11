package zags.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponceApp {
    private Integer applicantid;
    private Integer applicationid;
    private Integer citizenid;
    private Integer birthcertificateid;
    private Integer  merrigecertificateid;
    private Integer deathcertificateid;
}
