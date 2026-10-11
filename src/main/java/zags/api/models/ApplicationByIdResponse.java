package zags.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApplicationByIdResponse {
    private String dateofapplication;
    private String kindofapplication;
    private String statusofapplication;
}
