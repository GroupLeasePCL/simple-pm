package th.co.grouplease.simple.pm.ui.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRelease extends BaseModel<Long> {
    private String version;
    private LocalDate releaseDate;
}
