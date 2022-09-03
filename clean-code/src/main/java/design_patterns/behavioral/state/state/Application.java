package design_patterns.behavioral.state.state;


class Application {

        public static void main(String[] args) {
            var account = Account.create("4c9b6714-48e6-45b6-b34e-e24dd27ee3f1");

            account.block();
            System.out.println(account.getState());

            account.activate();
            System.out.println(account.getState());

            account.block();
            System.out.println(account.getState());

            account.activate();
            System.out.println(account.getState());

            account.unblock();
            System.out.println(account.getState());

            account.block();
            System.out.println(account.getState());

            account.disable();
            System.out.println(account.getState());

    }
}
