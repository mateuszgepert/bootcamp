package design_patterns.behavioral.state.state;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Account {

    private final String id;
    private State state;

    static Account create(String id) {
        return new Account(id, new InitializedState());
    }

    String getState() {
        return state.name();
    }
    void block() {
        this.state = state.block();
    }

    void unblock() {
        this.state = state.unblock();
    }

    void activate() {
        this.state = state.activate();
    }

    void disable() {
        this.state = state.disable();
    }

}
