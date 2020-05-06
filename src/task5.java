import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
public class task5 {
    public static void main(String[] args) {
        int [] array = (encrypt("Hello"));
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i]  + " ");
        System.out.println();
        int[] array1 = { 72, 33, -73, 84, -12, -3, 13, -13, -68};
        System.out.println(decrypt(array1));
        System.out.println(canMove("Конь","A1","B3"));
        System.out.println(canComplete("butl", "beautiful" ));
        int [] array2 = {1, 2, 3, 4, 5, 6};
        System.out.println(sumDigProd(array2));
        String [] array3 = {"toe", "ocelot", "maniac"};
        String [] result = (sameVowelGroup(array3));
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]  + " ");
        System.out.println();
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(validateCard(1234567890123456L));
        System.out.println(numToEng(120));
        System.out.println(numToRu(120));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(hexLattice(61));
    }
    public static int[] encrypt (String A){
        int [] array = new int [A.length()];
        for (int i = 0; i < A.length(); i++) {
            if (i == 0)
                array[i] = A.charAt(i);
            else
                array[i] = A.charAt(i) - A.charAt(i-1);
        }
        return array;
    }
    public static String decrypt(int [] array) {
        String res ="";
        for (int i = 0; i < array.length; i++){
            if (i == 0)
                res += (char) array[i];
            else {
                res += (char) (array[i] + array[i - 1]);
                array[i] += array[i-1];
            }
        }
        return res;
    }
    public static boolean canMove(String a, String b, String c) {
        int a1 = c.charAt(1) - b.charAt(1), a2 = c.charAt(0) - b.charAt(0);
        boolean b1 = (b.charAt(0) == c.charAt(0)) || (b.charAt(1) == c.charAt(1));
        boolean b2 = (Math.abs(a2)) == (Math.abs(a1));
        switch (a) {
            case "Пешка":
                if (b.charAt(0) == c.charAt(0)) {
                    if (a1 == 1)
                        return true;
                    else if ((a1 == 2) && (b.charAt(1) == '2'))
                        return true;
                    else
                        return false;
                }
                else
                    return false;
            case "Конь":
                if ((Math.abs(a2) == 1) && (Math.abs(a1) == 2))
                    return true;
                else if ((Math.abs(a1) == 1) && (Math.abs(a2) == 2))
                    return true;
                else
                    return false;
            case "Слон":
                if (b1)
                    return true;
                else
                    return false;
            case "Ладья":
                if (b2)
                    return true;
                else
                    return false;
            case "Ферзь":
                if (b1 || b2)
                    return true;
                else
                    return false;
            case "Король":
                if (Math.abs(a1) <= 1 && Math.abs(a2) <= 1)
                    return true;
                else
                    return false;
        }
        return false;
    }
    public static boolean canComplete (String F, String S){
        for (int i = 0; i < S.length(); i++){
            if (F.charAt(0) == S.charAt(i)) {
                F = F.substring(1, F.length() - 1);
                S = S.substring(i+1, S.length()-1);
            }
        }
        if (F.length() == 0)
            return true;
        else
            return false;
    }
    public static int sumDigProd(int [] array){
        int sum = 0;
        for (int i = 0; i < array.length; i++ )
            sum += array[i];
        int p = 1;
        while (sum > 9) {
            for (int i = 0; i < sum; i++) {
                p *= sum % 10;
                sum = sum / 10;
            }
            sum = p;
            p = 1;
        }
        return sum;
    }
    public static String[] sameVowelGroup (String [] array) {
        String[] res = new String[array.length];
        String ch = "";
        int s = 0;
        for (int i = 1; i < array.length; i++)
            res[i] = "";
        res[0] = array[0];
        int l = 0;
        for (int i = 0; i < res[0].length(); i++) {
            if ((res[0].charAt(i) == 'A') || (res[0].charAt(i) == 'a') || (res[0].charAt(i) == 'E') || (res[0].charAt(i) == 'e') || (res[0].charAt(i) == 'i') || (res[0].charAt(i) == 'I') || (res[0].charAt(i) == 'o') || (res[0].charAt(i) == 'O') || (res[0].charAt(i) == 'u') || (res[0].charAt(i) == 'U') || (res[0].charAt(i) == 'y') || (res[0].charAt(i) == 'Y')) {
                for (int j = 0; j < ch.length(); j++) {
                    if (res[0].charAt(i) == ch.charAt(j))
                        s ++;
                }
                if (s == 0)
                    ch += res[0].charAt(i);
            }
        }
        s = 0;
        for (int i = 1; i < array.length; i++) {
            for (int k = 0; k < ch.length(); k++) {
                for (int j = 0; j < array[i].length(); j++)
                    if (array[i].charAt(j) == ch.charAt(k)) {
                        j = array[i].length() - 1;
                        s ++;
                    }
            }
            if (s == ch.length()) {
                l += 1;
                res[l] += array[i];
            }
            s = 0;
        }
        return res;
    }
    public static boolean validateCard (long A){
        String number = String.valueOf(A);
        String invers = "";
        String s;
        int sum = 0;
        String res = "";
        char Num = number.charAt(number.length()-1);
        int N = Character.getNumericValue(Num);
        if ((number.length() >= 14) && (number.length() <= 19)) {
            number = number.substring(0, number.length() - 1);
            for (int i = number.length() - 1; i >= 0; i--)
                invers += number.charAt(i);
            for (int k = 0; k < invers.length(); k++) {
                char ch = invers.charAt(k);
                int p = 0;
                if (k % 2 == 0) {
                    p = Character.getNumericValue(invers.charAt(k));
                    p = 2 * p;
                    s = String.valueOf(p);
                    if (s.length() > 1) {
                        while (p > 9) {
                            p = (p / 10) + (p % 10);
                            s = String.valueOf(p);
                        }
                    }
                    if (s.length() == 1)
                        ch = s.charAt(0);
                }
                res += ch;
                }
            for (int i = 0; i < res.length(); i++)
                sum += Character.getNumericValue(res.charAt(i));
        }
        else
            return false;
        sum = sum % 10;
        if ((10 - sum) == N)
            return true;
        else
            return false;
        }
    public static String getSha256Hash(String a) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashInBytes = md.digest(a.getBytes(StandardCharsets.UTF_8));

//bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }
        catch (Exception ex) {}
        return "";
    }
    public static String numToEng(int a) {
        String out = "";
        switch (a / 100) {
            case 1:
                out += "one hundred ";
                break;
            case 2:
                out += "two hundred ";
                break;
            case 3:
                out += "three hundred ";
                break;
            case 4:
                out += "four hundred ";
                break;
            case 5:
                out += "five hundred ";
                break;
            case 6:
                out += "six hundred ";
                break;
            case 7:
                out += "seven hundred ";
                break;
            case 8:
                out += "eight hundred ";
                break;
            case 9:
                out += "nine hundred ";
                break;
            case 0:
                out += "";
                break;
        }
        switch (a / 10 - (a/100 * 10)) {
            case 1:
                switch (a % 100) {
                    case 10:
                        out += "ten";
                        return out;
                    case 11:
                        out += "eleven";
                        return out;
                    case 12:
                        out += "twelve";
                        return out;
                    case 13:
                        out += "thirteen";
                        return out;
                    case 14:
                        out += "fourteen";
                        return out;
                    case 15:
                        out += "fifteen";
                        return out;
                    case 16:
                        out += "sixteen";
                        return out;
                    case 17:
                        out += "seventeen";
                        return out;
                    case 18:
                        out += "eighteen";
                        return out;
                    case 19:
                        out += "nineteen";
                        return out;
                }
                break;
            case 2:
                out += "twenty ";
                break;
            case 3:
                out += "thirty ";
                break;
            case 4:
                out += "forty ";
                break;
            case 5:
                out += "fifty ";
                break;
            case 6:
                out += "sixty ";
                break;
            case 7:
                out += "seventy ";
                break;
            case 8:
                out += "eighty ";
                break;
            case 9:
                out += "ninety ";
                break;
            case 0:
                out += "";
                if (a % 10 != 0)
                    out += "and ";
                break;
        }
        switch (a % 10) {
            case 1:
                out += "one";
                break;
            case 2:
                out += "two";
                break;
            case 3:
                out += "three";
                break;
            case 4:
                out += "four";
                break;
            case 5:
                out += "five";
                break;
            case 6:
                out += "six";
                break;
            case 7:
                out += "seven";
                break;
            case 8:
                out += "eight";
                break;
            case 9:
                out += "nine";
                break;
            case 0:
                out += "";
                if (a == 0)
                    return "zero";
                break;
        }
        return out;
    }
    public static String numToRu(int a) {
        String out = "";
        switch (a / 100) {
            case 1:
                out += "сто ";
                break;
            case 2:
                out += "двести ";
                break;
            case 3:
                out += "триста ";
                break;
            case 4:
                out += "четыреста ";
                break;
            case 5:
                out += "пятьсот ";
                break;
            case 6:
                out += "шестьсот ";
                break;
            case 7:
                out += "семьсот ";
                break;
            case 8:
                out += "восемьсот ";
                break;
            case 9:
                out += "девятьсот ";
                break;
            case 0:
                out += "";
                break;
        }
        switch (a / 10 - (a/100 * 10)) {
            case 1:
                switch (a % 100) {
                    case 10:
                        out += "десять";
                        return out;
                    case 11:
                        out += "одиннадцать";
                        return out;
                    case 12:
                        out += "двенадцать";
                        return out;
                    case 13:
                        out += "тринадцать";
                        return out;
                    case 14:
                        out += "четырнадцать";
                        return out;
                    case 15:
                        out += "пятнадцать";
                        return out;
                    case 16:
                        out += "шестнадцать";
                        return out;
                    case 17:
                        out += "семнадцать";
                        return out;
                    case 18:
                        out += "восемнадцать";
                        return out;
                    case 19:
                        out += "девятнадцать";
                        return out;
                }
                break;
            case 2:
                out += "двадцать ";
                break;
            case 3:
                out += "тридцать ";
                break;
            case 4:
                out += "сорок ";
                break;
            case 5:
                out += "пятьдесят ";
                break;
            case 6:
                out += "шестьдесят ";
                break;
            case 7:
                out += "семьдесят ";
                break;
            case 8:
                out += "восемьдесят ";
                break;
            case 9:
                out += "девяносто ";
                break;
            case 0:
                out += "";
                break;
        }
        switch (a % 10) {
            case 1:
                out += "один";
                break;
            case 2:
                out += "два";
                break;
            case 3:
                out += "три";
                break;
            case 4:
                out += "четыре";
                break;
            case 5:
                out += "пять";
                break;
            case 6:
                out += "шесть";
                break;
            case 7:
                out += "семь";
                break;
            case 8:
                out += "восемь";
                break;
            case 9:
                out += "девять";
                break;
            case 0:
                out += "";
                if (a == 0)
                    return "ноль";
                break;
        }
        return out;
    }
    public static String correctTitle(String A){
        String B = " ";
        String res = "";
        A = A.toLowerCase();
        String[] N = A.split(B);
        for (int i = 0; i < N.length; i++) {
            if ((N[i].equals("and")) || (N[i].equals("of")) || (N[i].equals("the")) ||(N[i].equals("in"))) {
               res += N[i] + " ";
            }
            else {
                for (int j = 0; j < N[i].length(); j++) {
                    if (j == 0)
                        res += Character.toUpperCase(N[i].charAt(j));
                    else
                        res += N[i].charAt(j);
                    if (j == N[i].length()-1)
                        res += " ";
                }
            }
        }
        return res;
    }
    public static String hexLattice(int K){
        int k = 1, i = 1;
        String res = "";
        while (K > k) {
            k += i * 6;
            i++;
        }
        int l = i, h = i * 2 - 1;

        if (k == K) {
            while (l < h) {
                for (int g = 0; g < h - l; g++)
                    res += " ";
                for (int j = 0; j < l; j++)
                    res += "o ";
                res += "\n";
                l++;
            }
            while (l >= i) {
                for (int g = 0; g < h - l; g++)
                    res += " ";
                for (int j = l; j > 0; j--)
                    res += "o ";
                res += "\n";
                l--;
            }
        }
        else
            return "Invalid";
        return res;
    }
}


