package design_patterns.behavioral.state;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class Account {

    private final String id;
    private AccountState state;

    static Account create(String id) {
        return new Account(id, AccountState.INITIALIZED);
    }

    Optional<AccountState> updateState(String stateToUpdate) {
        var desiredState = AccountState.valueOf(stateToUpdate);
        if (state == AccountState.ACTIVE) {
            if (desiredState == AccountState.INITIALIZED) {
                return Optional.empty();
            } else {
                return Optional.of(desiredState);
            }
        } else if (state == AccountState.INITIALIZED) {
            if (desiredState != AccountState.ACTIVE) {
                return Optional.empty();
            } else {
                return Optional.of(AccountState.ACTIVE);
            }
        } else if (state == AccountState.BLOCKED) {
            if (desiredState == AccountState.ACTIVE) {
                return Optional.of(AccountState.DISABLED);
            }
            if (desiredState == AccountState.DISABLED) {
                return Optional.of(AccountState.REMOVED);
            }
            return Optional.empty();
        } else if (state == AccountState.DISABLED) {
            if (List.of(AccountState.REMOVED, AccountState.ACTIVE).contains(desiredState)) {
                return Optional.of(desiredState);
            } else {
                return Optional.empty();
            }
        } else {
            //status is removed
            return Optional.empty();
        }
    }

}
