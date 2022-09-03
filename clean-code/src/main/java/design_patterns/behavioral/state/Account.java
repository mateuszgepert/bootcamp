package design_patterns.behavioral.state;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Account {

    private final String id;
    private AccountState state;

    static Account create(String id) {
        return new Account(id, AccountState.INITIALIZED);
    }

    String getState() {
        return state.name();
    }

    void block() {
        if (state == AccountState.ACTIVE) {
            state = AccountState.BLOCKED;
        }
    }

    void unblock() {
        if (state == AccountState.BLOCKED) {
            state = AccountState.ACTIVE;
        }
    }

    void activate() {
        if (state == AccountState.ACTIVE) {
            //do nothing
            return;
        }
        if (state == AccountState.INITIALIZED) {
            this.state = AccountState.ACTIVE;
        }

    }

    void disable() {
        if(state == AccountState.BLOCKED) {
            System.out.println("Someone tries to disable blocked account");
            return;
        }
        state = AccountState.DISABLED;
    }

}
