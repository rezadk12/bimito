package question2;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    private String name;
    private String singer;
    private String genre;
    private byte tracks;
}
