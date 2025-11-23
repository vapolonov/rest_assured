package otus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@SuppressFBWarnings("EI_EXPOSE_REP")
public class PetDTO {

  private long id;
  private CategoryDTO category;
  private String name;
  private ArrayList<String> photoUrls;
  private ArrayList<TagDTO> tags;
  private String status;
}
