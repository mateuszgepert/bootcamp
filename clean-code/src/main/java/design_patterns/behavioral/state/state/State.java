package design_patterns.behavioral.state.state;

public interface State {

    State block();

    State activate();

    State disable();

    State unblock();

    String name();
}
