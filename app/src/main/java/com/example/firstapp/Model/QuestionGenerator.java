package com.example.firstapp.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionGenerator {

    // Questions with answers and 3 wrongs answers by difficulty
    private static final String[][][] QuestionsAnswerWrong = {
            { // Easy
                    {"Quelle est la couleur du ciel?", "Bleu", "Vert", "Jaune", "Rouge"},
                    {"Quel est le contraire de 'triste'?", "Heureux", "Ennuyé", "Étonné", "Énergique"},
                    {"Combien de jours y a-t-il dans une semaine?", "7", "6", "8", "9"},
                    {"Quelle est la capitale de la France?", "Paris", "Berlin", "Londres", "Madrid"},
                    {"Quel animal donne du lait?", "Vache", "Poule", "Chèvre", "Cheval"},
                    {"Quelle est la première lettre de l'alphabet?", "A", "B", "C", "D"},
                    {"Combien de minutes y a-t-il dans une heure?", "60", "45", "75", "90"},
                    {"Quelle est la couleur de la neige?", "Blanc", "Gris", "Bleu", "Rose"},
                    {"Quel est le fruit du pommier?", "Pomme", "Poire", "Pêche", "Banane"},
                    {"Quel animal est connu pour son miaulement?", "Chat", "Chien", "Loup", "Tigre"},
                    {"Quelle planète est connue comme la Planète Rouge?", "Mars", "Vénus", "Jupiter", "Mercure"},
                    {"Quel oiseau est connu pour son hululement?", "Hibou", "Aigle", "Corbeau", "Colombe"},
                    {"Quel est le contraire de 'haut'?", "Bas", "Lourd", "Court", "Petit"},
                    {"Quel numéro vient après 2?", "3", "4", "1", "0"},
                    {"Quelle est la couleur d'une banane mûre?", "Jaune", "Vert", "Rouge", "Orange"},
                    {"Dans quel mois de l'année est Noël?", "Décembre", "Janvier", "Novembre", "Octobre"},
                    {"Combien de doigts a une main?", "5", "4", "6", "3"},
                    {"Quel est le contraire de 'sec'?", "Mouillé", "Humide", "Doux", "Lourd"},
                    {"Quelle est la forme de la Terre?", "Sphérique", "Plate", "Cubique", "Ovale"},
                    {"Quelle est la couleur du soleil?", "Jaune", "Blanc", "Orange", "Rouge"},
                    {"Quelle lettre vient après 'B' dans l'alphabet?", "C", "A", "D", "E"},
                    {"Quel est le contraire de 'rapide'?", "Lent", "Vite", "Courant", "Rapide"},
                    {"Quel animal est le roi de la jungle?", "Lion", "Tigre", "Éléphant", "Léopard"},
                    {"Quelle est la boisson obtenue en pressant des oranges?", "Jus d'orange", "Jus de pomme", "Eau", "Thé"},
                    {"Quel est le principal gaz dans l'air que nous respirons?", "Azote", "Oxygène", "Carbone", "Hélium"}
            },
            { // Normal
                    {"Qui est l'auteur de 'Le Petit Prince'?", "Antoine de Saint-Exupéry", "Victor Hugo", "Jules Verne", "George Orwell"},
                    {"Quel élément a le symbole chimique 'Au'?", "Or", "Argent", "Aluminium", "Azote"},
                    {"Dans quelle année a eu lieu la Révolution française?", "1789", "1776", "1800", "1812"},
                    {"Qui a formulé la théorie de la relativité?", "Albert Einstein", "Isaac Newton", "Galilée", "Niels Bohr"},
                    {"Quelle est la monnaie du Japon?", "Yen", "Dollar", "Euro", "Roupie"},
                    {"Quel est le plus long fleuve du monde?", "Nil", "Amazone", "Mississippi", "Danube"},
                    {"Quelle est la capitale de l'Australie?", "Canberra", "Sydney", "Melbourne", "Brisbane"},
                    {"Qui est le dieu de la guerre dans la mythologie grecque?", "Arès", "Zeus", "Apollon", "Hermès"},
                    {"Dans quel pays se trouve le Taj Mahal?", "Inde", "Pakistan", "Bangladesh", "Népal"},
                    {"Quel est l'instrument de mesure de la pression atmosphérique?", "Baromètre", "Thermomètre", "Hygromètre", "Anémomètre"},
                    {"Quel est le plus petit os du corps humain?", "Étrier", "Cubitus", "Fémur", "Coccyx"},
                    {"Combien d'États composent les États-Unis?", "50", "48", "52", "46"},
                    {"Quel est le plus grand mammifère terrestre?", "Éléphant d'Afrique", "Baleine bleue", "Hippopotame", "Rhino"},
                    {"Quel est le plus grand océan?", "Pacifique", "Atlantique", "Indien", "Arctique"},
                    {"Dans quelle ville se trouve la Tour Eiffel?", "Paris", "Londres", "Berlin", "New York"},
                    {"Dans quel pays se trouve le mont Everest?", "Népal", "Chine", "Inde", "Pakistan"},
                    {"Quelle est la valeur de Pi à deux chiffres après la virgule?", "3,14", "3,15", "2,71", "1,61"},
                    {"Quel est le plus grand planète du système solaire?", "Jupiter", "Saturne", "Mars", "Vénus"},
                    {"Dans quel pays a été inventé le Sudoku?", "Japon", "Chine", "Inde", "États-Unis"},
                    {"Quel est le livre sacré de l'Islam?", "Coran", "Bible", "Torah", "Vedas"},
                    {"Quelle est la capitale de la Russie?", "Moscou", "Saint-Pétersbourg", "Kiev", "Varsovie"},
                    {"Qui est le créateur de la statue 'Le Penseur'?", "Auguste Rodin", "Michel-Ange", "Léonard de Vinci", "Pablo Picasso"},
                    {"Quelle est la plus grande planète naine?", "Éris", "Pluton", "Haumea", "Makémaké"},
                    {"Quelle est la couleur du saphir?", "Bleu", "Vert", "Rouge", "Jaune"},
                    {"Quel est le symbole chimique pour l'eau?", "H2O", "CO2", "O2", "N2"}
            },
            { // Hard
                    {"Quelle est la constante cosmologique?", "Lambda", "Pi", "Epsilon", "Sigma"},
                    {"Qui a écrit 'À la recherche du temps perdu'?", "Marcel Proust", "Ernest Hemingway", "James Joyce", "Franz Kafka"},
                    {"Quelle est la somme des angles intérieurs d'un hexagone?", "720", "540", "360", "900"},
                    {"Quel philosophe a écrit 'L'être et le néant'?", "Jean-Paul Sartre", "Albert Camus", "Friedrich Nietzsche", "René Descartes"},
                    {"Quelle est la capitale de la Nouvelle-Zélande?", "Wellington", "Auckland", "Christchurch", "Dunedin"},
                    {"Qui a inventé le premier avion motorisé?", "Frères Wright", "Leonardo da Vinci", "Thomas Edison", "Henry Ford"},
                    {"Quelle est la plus petite planète du système solaire?", "Mercure", "Vénus", "Mars", "Uranus"},
                    {"Quel est le deuxième élément le plus abondant dans la croûte terrestre?", "Silicium", "Oxygène", "Aluminium", "Fer"},
                    {"Quel compositeur a créé l'œuvre 'Le Sacre du Printemps'?", "Igor Stravinsky", "Wolfgang Amadeus Mozart", "Johann Sebastian Bach", "Ludwig van Beethoven"},
                    {"Qui a écrit la 'Divine Comédie'?", "Dante Alighieri", "William Shakespeare", "Homer", "Virgile"},
                    {"Quel est le point le plus bas sur la surface terrestre?", "Mer Morte", "Fosse des Mariannes", "Lac Baïkal", "Vallée de la Mort"},
                    {"Quelle est la troisième loi de Newton?", "Action et réaction", "F=ma", "Loi de la gravitation universelle", "Loi des aires"},
                    {"Qui est l'auteur de 'Crime et Châtiment'?", "Fiodor Dostoïevski", "Leo Tolstoï", "Anton Tchekhov", "Alexandre Pouchkine"},
                    {"Quel est le plus petit nombre premier à deux chiffres?", "11", "13", "17", "19"},
                    {"Quelle est la couleur de la lumière émise par une étoile la plus chaude?", "Bleu", "Rouge", "Jaune", "Blanc"},
                    {"Dans quelle ville est située la Banque centrale européenne?", "Francfort", "Bruxelles", "Londres", "Paris"},
                    {"Quel est l'animal le plus rapide sur terre?", "Guépard", "Faucon pèlerin", "Sailfish", "Antilope"},
                    {"Quelle molécule est également appelée 'l'hormone du bonheur'?", "Sérotonine", "Dopamine", "Endorphine", "Ocytocine"},
                    {"Quelle est la langue la plus parlée dans le monde?", "Mandarin", "Anglais", "Espagnol", "Hindi"},
                    {"Quel écrivain a utilisé le pseudonyme de George Orwell?", "Eric Arthur Blair", "Samuel Clemens", "Charles Lutwidge Dodgson", "William Sydney Porter"},
                    {"Quelle est la capitale de la Mongolie?", "Oulan-Bator", "Astana", "Kiev", "Bichkek"},
                    {"Qui est connu pour la théorie de l'évolution par la sélection naturelle?", "Charles Darwin", "Alfred Russel Wallace", "Gregor Mendel", "Jean-Baptiste Lamarck"},
                    {"Quelle est la plus haute montagne d'Europe?", "Mont Elbrouz", "Mont Blanc", "Matterhorn", "Monte Rosa"},
                    {"Quel est le principal composant du gaz naturel?", "Méthane", "Azote", "Oxygène", "Dioxyde de carbone"},
                    {"Quel philosophe a écrit 'Ainsi parlait Zarathoustra'?", "Friedrich Nietzsche", "Platon", "Immanuel Kant", "Søren Kierkegaard"}
            }
    };

    // Generate question by difficulty
    public static List<Question> generateQuestion(Difficulty difficulty) {
        int indexDifficulty = difficulty.ordinal();
        List<Question> listQuestion = new ArrayList<>();
        Set<Integer> indexUsed = new HashSet<>();
        Question tmp;
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            int questionIndex;

            do {
                questionIndex = rand.nextInt(QuestionsAnswerWrong[indexDifficulty].length);
            } while (indexUsed.contains(questionIndex));
            indexUsed.add(questionIndex);

            String questionText = QuestionsAnswerWrong[indexDifficulty][questionIndex][0];
            String questionAnswer = QuestionsAnswerWrong[indexDifficulty][questionIndex][1];

            List<String> wrongAnswer = new ArrayList<>();
            wrongAnswer.add(QuestionsAnswerWrong[indexDifficulty][questionIndex][2]);
            wrongAnswer.add(QuestionsAnswerWrong[indexDifficulty][questionIndex][3]);
            wrongAnswer.add(QuestionsAnswerWrong[indexDifficulty][questionIndex][4]);

            tmp = new Question(i, questionText, questionAnswer, wrongAnswer, difficulty);
            listQuestion.add(tmp);
        }

        return listQuestion;
    }

}
