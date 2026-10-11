package zags.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AdminProcessRequest {
    private Integer applId;
    private Integer staffId;
    private String action;
}
