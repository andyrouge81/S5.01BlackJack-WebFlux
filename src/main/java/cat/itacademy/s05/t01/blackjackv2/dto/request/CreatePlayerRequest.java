package cat.itacademy.s05.t01.blackjackv2.dto;

import cat.itacademy.s05.t01.blackjackv2.model.enums.Role;



public record CreatePlayerRequest(String playerName, Role role) {
}
