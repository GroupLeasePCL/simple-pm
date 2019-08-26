package th.co.grouplease.simple.pm.ui.main;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRelease {
    private String version;
    private LocalDate releaseDate;
}
