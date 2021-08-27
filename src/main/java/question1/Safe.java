package question1;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Safe {

    private String password;
    private List<String> safeGears;

    public int calculateMinimumOfChange() {
        int totalChanges = 0;
        for (int i = 0; i < this.password.length(); i++) {
            if (this.safeGears.get(i).indexOf(this.password.charAt(i)) <= 4) {
                totalChanges += this.safeGears.get(i).indexOf(this.password.charAt(i));
            } else {
                totalChanges += (9 - this.safeGears.get(i).indexOf(this.password.charAt(i)));
            }
        }
        return totalChanges;
    }
}
