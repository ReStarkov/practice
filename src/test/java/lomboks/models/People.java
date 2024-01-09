package lomboks.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class People {
    private String name;
    private String surname;
    private Integer age;
    private String sex;
}
