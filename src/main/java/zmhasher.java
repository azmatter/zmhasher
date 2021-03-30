import org.springframework.security.crypto.bcrypt.BCrypt;

public class zmhasher {
    private static String showUsageText(){
        return
                "==============================\n" +
                        "zmHasher - local password generator / hashing tool\n\n" +
                        "Use secure passwords for your internet accounts, but don't trust websites that generate them for you.\n" +
                        "These websites can store generated passwords on their local webserver, and if breached, " +
                        "generated passwords can be added to hacker password dictionaries.\n" +
                        "Use zmHasher to generate a password for a site or account, then store that password in a password manager tool" +
                        "\n\nUSAGE: \"java zmhasher <passwd> <salt - optional>\",  where \n" +
                        "\t<password> is your desired base password\n" +
                        "\t<salt> is your desired salt (can be iteration of a password, such as \'2\')\n";
    }

    private String hashedResult;

    public static void main(String [] args){
        int argc = args.length;
        zmhasher z;
        switch (argc) {
            case 1 -> z = new zmhasher(args[0]);
            case 2 -> z = new zmhasher(args[0], args[1]);
            default -> {
                System.out.println(showUsageText());
                return;
            }
        }

        System.out.println(z.toString());
    }

    public zmhasher(String input){
        hashedResult = BCrypt.hashpw(input,BCrypt.gensalt());
    }

    public zmhasher(String input, String salt){
        hashedResult = BCrypt.hashpw(input,salt);
    }

    @Override
    public String toString(){
        return this.hashedResult;
    }


}
