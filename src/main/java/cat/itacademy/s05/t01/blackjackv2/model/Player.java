package cat.itacademy.s05.t01.blackjackv2.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("players")

public class Player {

    @Id
    private Long id;

    private String playerName;

    private Integer gamesPlayed = 0;
    private Integer gamesWon = 0;

    public Player(String playerName) {
        this.playerName = playerName;


    }

}
