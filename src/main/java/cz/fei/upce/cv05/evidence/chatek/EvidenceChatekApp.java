package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    public static void main(String[] args) {
        final int KONEC_PROGRAMU = 0;
        final int VYPIS_CHATEK = 1;
        final int VYPIS_KONKRETNI_CHATKU = 2;
        final int PRIDANI_NAVSTEVNIKU = 3;
        final int ODEBRANI_NAVSTEVNIKU = 4;
        final int CELKOVA_OBSAZENOST = 5;
        final int VYPIS_PRAZDNE_CHATKY = 6;

        final int VELIKOST_KEMPU = 5;
        final int MAX_VELIKOST_CHATKY = 10;

        Scanner scanner = new Scanner(System.in);

        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> {
                    for (int i = 0; i < chatky.length; i++) {
                        System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
                    }
                }

                case VYPIS_KONKRETNI_CHATKU -> {
                    System.out.print("Zadej cislo chatky: ");
                    int cisloChatky = scanner.nextInt() - 1;
                    if (cisloChatky < 0 || cisloChatky >= chatky.length) {
                        System.err.println("Tato chatka neexistuje");
                        continue; // Zacni novou iteraci cyklu
                    }
                    System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
                }

                case PRIDANI_NAVSTEVNIKU -> {
                    System.out.print("Zadej cislo chatky: ");
                    int cisloChatky = scanner.nextInt() - 1;
                    if (cisloChatky < 0 || cisloChatky >= chatky.length) {
                        System.err.println("Tato chatka neexistuje");
                        continue;
                    }
                    System.out.print("Zadej pocet navstevniku: ");
                    int pocetNavstevniku = scanner.nextInt();
                    if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
                        System.err.println("Neplatna hodnota pro pocet navstevniku");
                        continue;
                    }
                    if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
                        System.err.println("Prekrocen maximalni pocet navstevniku chatky");
                        continue; // Zacni novou iteraci cyklu
                    }
                    chatky[cisloChatky] = pocetNavstevniku + chatky[cisloChatky];
                }

                case ODEBRANI_NAVSTEVNIKU -> {
                    System.out.print("Zadejte cislo chatky, pro ktere chctete vymazat ucastnika.\n: ");
                    int cisloChatky = scanner.nextInt() - 1;
                    if (cisloChatky < 0 || cisloChatky >= chatky.length) {
                        System.err.println("Tato chatka neexistuje");
                        continue;
                    }
                    System.out.printf("Zadejte cislo kolik chete nastevniku odebrat z moznych %d .\n: ", chatky[cisloChatky]);
                    int cisloOdebraniNastevniku = scanner.nextInt();
                    if (cisloOdebraniNastevniku <= 0 || cisloOdebraniNastevniku > chatky[cisloChatky]) {
                        System.out.println("Nelze odebrat vice nastevniku, nez kolik se nachazi");
                        continue;
                    }
                    chatky[cisloChatky] = chatky[cisloChatky] - cisloOdebraniNastevniku;
                    System.out.printf("Chatka %d ma nyni %d nastevniku. ", cisloChatky+1, chatky[cisloChatky]);
                }

                case CELKOVA_OBSAZENOST -> {
                    System.out.print("Zadejte cislo chatky, pro ktere chctete zjistit dostupnost.\n: ");
                    int cisloChatky = scanner.nextInt() - 1;
                    if (cisloChatky < 0 || cisloChatky >= chatky.length) {
                        System.err.println("Tato chatka neexistuje");
                        continue;
                    }
                    int pocetZbyvajicichNastevniku =  MAX_VELIKOST_CHATKY - chatky[cisloChatky];
                    System.out.printf("Lze maximalne pridat moznych nastevniku %d.\n", pocetZbyvajicichNastevniku);
                    System.out.println("Ostatni chatky maji pocet obsazeni: ");
                    for (int chatka = 0 ; chatka < VELIKOST_KEMPU; chatka++) {
                        if (chatka != cisloChatky){
                            if (chatky[chatka] == 0) {
                                System.out.printf("Chatka %d je prazdna\n", chatka+1);
                            } else {
                                System.out.printf("Chatka %d je zabrana\n", chatka+1);
                            }
                        }
                    }
                }

                case VYPIS_PRAZDNE_CHATKY -> {
                    for (int chatka = 0 ; chatka < VELIKOST_KEMPU; chatka++) {
                        if (chatky[chatka] == 0) {
                            System.out.printf("Chatka %d je prazdna.\n", chatka + 1);
                        }
                    }
                }

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                    System.exit(1);
                }

                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }
}
