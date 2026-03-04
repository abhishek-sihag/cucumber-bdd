package pojoHelper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPayload {
    public int id;
    public String title;
    public double price;
    public String description;
    public String category;
    public String image;
}
