
/*======= Entity Compétition================
 CREATE TABLE competition (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 description TEXT,
 annee DATE NOT NULL,
 admin_id BIGINT NOT NULL,
 FOREIGN KEY (admin_id) REFERENCES admin(id)
 );
 */

Package com.careerexpo.careerexpo_API.entity;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "competitions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT",nullable = false)
    private String description;

    @Column(columnDefinition = "DATE",nullable = false)
    private LocalDate annee;

    @ManyToOne
    @JoinColumn(name = "admin_id",nullable = false, insertable = true, updatable = true)
    private Admin admin;




}
