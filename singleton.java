class Singleton {
    private static Singleton instance;
    private int random = 0;
    private Singleton() {
        // Generating a random value between 0 to 100
        this.random = (int) (Math.random() * 100);
        System.out.println("singleton constructor generated random number: " + this.random);
    }

    // Getter method for the randomly generated number
    public int getNumber() {
        return this.random;
    }

    // Instantiatin Singleton once
    // only if it is not instantiated before
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
class Main {
    public static void main(String args[]){
        // Getting two instances for Singleton
        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();

        // Both instances should show the same random value
        // because Singlton gives access to the same instance
        System.out.println(x.getNumber());
        System.out.println(y.getNumber());
    }
}