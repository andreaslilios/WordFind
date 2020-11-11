
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Game {

    static ArrayList<Word> words = new ArrayList();

    private Random rand;

    public static void gameon() {
        Scanner player = new Scanner(System.in);
        System.out.println("WELCOME TO THE GAME <<FIND THE WORDS>>");
        System.out.println("Enter Name:");
        String name = player.next();

        int newgame = 1;        // ME AYTHN ELEGXW AN THELEI NA KSANAPAIKSEI O XRHSTHS
        wordList();             //  FTIAXNEI TIS LEKSEIS-ANTIKEIMENA KAI TIS TOPOTHETEI SE LISTA
        while (newgame == 1) {

            System.out.println("CHOOSE DIFFICULTY\nFOR EASY press 1\tFOR DIFFICULT press 2");
            int choose = player.nextInt();

            Random rand = new Random();
            
            ArrayList<Word> guess = new ArrayList();
            guess.add(words.get(rand.nextInt(words.size())));          //EISAGW TIS 3 TYXAIES LEKSEIS TOY PRWTOY
            words.remove(guess.get(0));                               // GYROY SE NEA LISTA KAI TIS VGAZW APTHN PALIA
            guess.add(words.get(rand.nextInt(words.size())));        //GIA THN MONADIKOTHTA KAI GIA PITHANO NEO GYRO
            words.remove(guess.get(1));
            guess.add(words.get(rand.nextInt(words.size())));
             words.remove(guess.get(2));
            

            StringBuilder sb = new StringBuilder(guess.get(0).getLeksi().length() + guess.get(1).getLeksi().length() + guess.get(2).getLeksi().length());
            sb.append(guess.get(0).getLeksi());
            sb.append(guess.get(1).getLeksi());     //STRINGBUILDER ME TIS 3 LEKSEIS SYNOLIKA
            sb.append(guess.get(2).getLeksi());    //GIA TO LEKSILOGIO POY THA EMFANIZETAI STON PAIKTH

            int efforts = 0;            // GIA NA ELEGXW TIS LATHOS APANTHSEIS
            boolean correct = true;    // GIA NA ELEGXW TIS SWSTES APANTHSEIS
            int epilogh = 0;          //  EPILOGH LEKSIS APTON PAIKTH
            while (efforts != 2 && guess.size() != 0) { //TERMATIZEI STIS 3 PROSPATHEIES,3 SWSTES LEKSEIS
                if (correct) {
                    printGame(choose, guess, sb);   // EMFANISH TOY PAIXNIDIOY

                    System.out.println();
                    System.out.println("PARAKALW EPILEKSTE LEKSH!!!");

                    for (int i = 1; i < guess.size() + 1; i++) {
                        System.out.println("Pieste <<" + i + ">> gia thn " + i + "h leksh\t");
                    }
                    epilogh = player.nextInt();   //EPILOGH LEKSHS
                }
                System.out.println("PLHKTROLOGHSTE TON PITHANO SYNDYASMO");
                String combination = player.next(); // H APANTHSH TOY PAIKTH
                if (combination.contentEquals(guess.get(epilogh - 1).getLeksi())) { //SYGKRISH THS APANTHSHS ME THN ANTISTOIXH EPILEGMENH LEKSH
                    for (int i = 0; i < guess.get(epilogh - 1).getLeksi().length(); i++) {
                        sb.deleteCharAt(sb.indexOf(Character.toString(guess.get(epilogh - 1).getLeksi().charAt(i))));
                        // DIAGRAFH XARAKTHRWN THS LEKSHS APTO LEKSILOGIO DHLADH APTO STRINGBUILDER
                    }                                                                                                   
                    guess.remove(epilogh - 1);  // SYNEPWS KAI APTHN LISTA TWN PITHANWN LEKSEWN
                    System.out.println("CONGRATULATIONS!");
                    correct = true;
                } else {
                    correct = false;//MANTEYEI combination MEXRI NA TO VREI   
                    System.out.println("WRONG COMBINATION!\t TRY AGAIN!");
                    efforts++; // MANTEYEI TO POLY 3 FORES
                }
            }
            System.out.println("New game?press 1");//AFOY TERMATISEI H WHILE GINETAI ELEGXOS GIA
            newgame = player.nextInt();           //DEYTERO GYRO
        }
    }

    public static void printGame(int choose, ArrayList<Word> g, StringBuilder sb) {
        if (choose == 1) {  //EYKOLO EPIPEDO
            StringBuilder sb2 = new StringBuilder(shuffle(sb.toString()));//FTIAXNW ENA DEYTERO GIA NA ANAKATEYTOYN TA GRAMMATA KAI NA MPORW NA PEIRAZW THN PRWTH
            for (int j = 0; j < g.size(); j++) {
                sb2.deleteCharAt(sb2.indexOf(Character.toString(g.get(j).getLeksi().charAt(0))));//DIAGRAFH PRWTOY XARAKTHRA TWN LEKSEWN APTO LEKSILOGIO POY VLEPEI O PAIKTHS
                System.out.print(g.get(j).getDescription() + "\t" + g.get(j).getLeksi().charAt(0));
                for (int i = 1; i < g.get(j).getLeksi().length() - 1; i++) {    //EMFANISH LEKSEWN TYPOY: S_ _ _A
                    System.out.print(" _ ");
                }
                System.out.println(g.get(j).getLeksi().charAt(g.get(j).getLeksi().length() - 1));
                sb2.deleteCharAt(sb2.indexOf(Character.toString(g.get(j).getLeksi().charAt(g.get(j).getLeksi().length() - 1))));
                //DIAGRAFH TELEYTAIOY XARAKTHRA TWN LEKSEWN APTO LEKSILOGIO POY VLEPEI O PAIKTHS
            }

            System.out.println(sb2.toString());
        } else if (choose == 2) {   // DYSKOLO EPIPEDO
            for (int j = 0; j < g.size(); j++) {
                System.out.print(g.get(j).getDescription() + "\t");
                for (int i = 0; i < g.get(j).getLeksi().length(); i++) {
                    System.out.print(" _ ");    //IDOY TYPOY EMFANISH ME APO PANW
                }
                System.out.println();
            }

            StringBuilder sb2 = new StringBuilder(shuffle(sb.toString()));  //TO TELIKO LEKSILOGIO POY VLEPEI O XRHSTHS
            System.out.println(sb2.toString());//EMFANISH LEKSILOGIOY
        }
    }

    public static String shuffle(String input) {        //ANAKATEYEI TA STOIXEIA THS STRINGBUILDER MOY
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public static void wordList() {
        Word leksi1 = new Word("meso texnhths fwtias", "LIGHTER");
        Word leksi2 = new Word("meso thlepikoinwnias", "PHONE");            //TA ANTIKEIMENA THS KLASHS WORD
        Word leksi3 = new Word("meso mazikis metaforas", "BUS");            //PITHANES LEKSEIS TOY PAIXNIDIOY
        Word leksi4 = new Word("amerikanikh tainia tromou", "SAW");
        Word leksi5 = new Word("onoma planitarxi--kartoun ths disney ", "DONALD");
        Word leksi6 = new Word("aggliko mousiko sykrothma", "OASIS");
        Word leksi7 = new Word("dhmofiles paixnidi trapoulas", "TICHU");
        Word leksi8 = new Word("rofhma: zestos,pagwmenos ", "COFFEE");
        Word leksi9 = new Word("egxordo mousiko organo", "GUITAR");

        words.add(leksi1);  //EISAGWGH TWN ANTIKEIMENWN SE LISTA 
        words.add(leksi2);
        words.add(leksi3);
        words.add(leksi4);
        words.add(leksi5);
        words.add(leksi6);
        words.add(leksi7);
        words.add(leksi8);
        words.add(leksi9);

    }
}
