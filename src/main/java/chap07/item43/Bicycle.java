package chap07.item43;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bicycle {

  private String brand;
  private Integer frameSize;

  public String getYaho(Bicycle bicycle) {
    return bicycle.getBrand() + " yaho " + bicycle.getFrameSize();
  }
}
