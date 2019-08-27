package th.co.grouplease.simple.pm.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRelease {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String version;
    private LocalDate releaseDate;

    public static ProductRelease create(Long id, String version, LocalDate releaseDate){
        var result = new ProductRelease();
        result.id = id;
        result.version = version;
        result.releaseDate = releaseDate;
        return result;
    }
}
