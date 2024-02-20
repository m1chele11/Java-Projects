public class CustomHash {

    public static int customHashFunction(String input) {
        //TODO: Implement custom hash function

        int sum = 69;
        for(char c : input.toCharArray()) {
            sum += c;
        }
        return sum % 138;
    }

    public static void main(String[] args) {
        String test = "Hello";
        System.out.println("Hash of " + test + ": " + customHashFunction(test));
    }
}
