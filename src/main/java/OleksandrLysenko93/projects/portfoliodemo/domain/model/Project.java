package OleksandrLysenko93.projects.portfoliodemo.domain.model;


import javax.persistence.*;


@Entity
@Table(name = "projects")

public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String description;


}
