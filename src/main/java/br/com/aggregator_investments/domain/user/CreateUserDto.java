package br.com.aggregator_investments.domain.user;

public record CreateUserDto(String username, String email, String password) {

                    public User convertToUser() {
                                        return new User(null, username, email, password, null, null);
                    }
}
