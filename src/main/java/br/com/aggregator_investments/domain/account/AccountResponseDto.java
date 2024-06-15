package br.com.aggregator_investments.domain.account;

public record AccountResponseDto(String accountId, String description) {

                    public static AccountResponseDto toAccountResponse(Account account) {
                                        return new AccountResponseDto(account.getAccountId().toString(), account.getDescription());
                    }
}
