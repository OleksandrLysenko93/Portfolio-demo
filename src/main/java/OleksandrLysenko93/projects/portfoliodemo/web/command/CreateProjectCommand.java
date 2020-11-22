package OleksandrLysenko93.projects.portfoliodemo.web.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class CreateProjectCommand {

    @NotBlank
    @Size(min=3, max = 26)
    private String name;
    @URL
    private String url;
    @NotBlank
    @Size(min=6, max = 160)
    private String description;
}
