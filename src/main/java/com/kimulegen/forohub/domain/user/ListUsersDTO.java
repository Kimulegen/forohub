package com.kimulegen.forohub.domain.user;

public record ListUsersDTO(
        Long id,
        String name,
        String email
) {
    public ListUsersDTO (User user){
        this(user.getId(), user.getName(), user.getEmail());
    }
}
