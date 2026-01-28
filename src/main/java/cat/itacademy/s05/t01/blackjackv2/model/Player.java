package cat.itacademy.s05.t01.blackjackv2.model;

import cat.itacademy.s05.t01.blackjackv2.model.enums.GameStatus;
import cat.itacademy.s05.t01.blackjackv2.model.enums.PlayerStatus;
import cat.itacademy.s05.t01.blackjackv2.model.enums.Role;
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

    private  Role role;

    private PlayerStatus status;


    private Integer gamesPlayed = 0;
    private Integer gamesWon = 0;

    public Player(String playerName, Role role) {
        this.playerName = playerName;
        this.role = role;
        this.status = PlayerStatus.PLAYING;
    }

    public void stand() {
        this.status = PlayerStatus.STAND;
    }
}
