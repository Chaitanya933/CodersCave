import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Abstract Question class representing a  question
abstract class Question {
    private String question_txt;
    //Question method for question initializations
    public Question(String question_txt) {
        this.question_txt = question_txt;
    }
    public String getQuestionText() {
        return question_txt;
    }
    //Method for checking answer
    public abstract boolean chck_ans(String ans);
}
//Defining MultipleChoiceQuestion class for representing the multiple choice questions
class MultipleChoiceQuestion extends Question {
    //Initializations
    private List<String> choice;
    private int correct_optindex;
    public MultipleChoiceQuestion(String question_txt, List<String> choice, int correct_optindex) {
        super(question_txt);
        this.choice = choice;
        this.correct_optindex = correct_optindex;
    }
    //Overriding the checkAnswer method for checking correct answer
    @Override
    public boolean chck_ans(String ans) {
        int selected_optindex;
        try {
            selected_optindex = Integer.parseInt(ans);
        } catch (NumberFormatException e) {
            return false; // Invalid input
        }
        return selected_optindex == correct_optindex;
    }
    //Getting options
    public List<String> getOptions() {
        return choice;
    }
}
//Defining TrueFalseQuestion class for representing the true/false questions
class TrueFalseQuestion extends Question {
    //Initializations
    private boolean correct_ans;
    public TrueFalseQuestion(String question_txt, boolean correct_ans) {
        super(question_txt);
        this.correct_ans = correct_ans;
    }
    //Overriding the checkAnswer method for checking correct answer
    @Override
    public boolean chck_ans(String ans) {
        boolean user_ans;
        try {
            user_ans = Boolean.parseBoolean(ans);
        } catch (IllegalArgumentException e) {
            return false; // Invalid input
        }
        return user_ans == correct_ans;
    }
}
//Defining the FillBlankQuestion class representing the fill in the blank questions
class FillBlankQuestion extends Question {
    //Initializations
    private String correct_ans;
    public FillBlankQuestion(String question_txt, String correct_ans) {
        super(question_txt);
        this.correct_ans = correct_ans;
    }
    //Overriding the checkAnswer method for checking correct answer
    @Override
    public boolean chck_ans(String ans) {
        return ans.equalsIgnoreCase(correct_ans);
    }
}
// Quiz class representing a collection of questions
class Quiz {
    private List<Question> questions;
    public Quiz() {
        this.questions = new ArrayList<>();
    }
    public void addQuestion(Question question) {
        questions.add(question);
    }
    public List<Question> getQuestions() {
        return questions;
    }
    //Calculating the marks percentage for our Quiz performance
    public double calculateMarks(List<String> answers) {
        int correctCount = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).chck_ans(answers.get(i))) {
                correctCount++;
            }
        }
        return ((double) correctCount / questions.size()) * 100;
    }
}
//Main class for our platform
public class OnlineQuizPlatform{
    public static void main(String[] args) {
        // Create a example sample quiz if you need we can add more and more questions
        Quiz quiz = new Quiz();
        quiz.addQuestion(new MultipleChoiceQuestion("What is the National Game of India?",List.of("1. Hockey", "2. Cricket", "3. Ice Hockey"), 1));
        quiz.addQuestion(new FillBlankQuestion("The father of the C Programming Language is ________.", "Dennis Ritchie"));
        quiz.addQuestion(new FillBlankQuestion("The Easiest Programming Language is ________.", "Python"));
        quiz.addQuestion(new MultipleChoiceQuestion("Who is the author of One Piece?",List.of("1. Kentaro Mura", "2. Jhonny Depp", "3. Oda Eichiiro"), 3));
        quiz.addQuestion(new MultipleChoiceQuestion("Which is not one of the Seven Wonders?",List.of("1. Taj Mahal", "2. Charminar", "3. Great Wall of China"), 2));
        quiz.addQuestion(new TrueFalseQuestion("Is C a programming language?", true));
        quiz.addQuestion(new TrueFalseQuestion("HTML stands for HyperText Markup Language?", true));
        quiz.addQuestion(new TrueFalseQuestion("In C Language,Spaces and commas are allowed in a variable name.?", false));
        // Taking answers from the user as input
        Scanner sc = new Scanner(System.in);
        List<String> userAnswers = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestionText());
            //Checking if the question is MCQ's it provides all options 
            if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
                for (String opt : mcq.getOptions()) {
                    System.out.println(opt);
                }
            }
            //User input for answer
            System.out.print("Your answer: ");
            String user_ans = sc.nextLine();
            userAnswers.add(user_ans);
            //Checks the user answer is correct or incorrect
            if (question.chck_ans(user_ans)) {
                System.out.println("Correct Answer..!\n");
            } else {
                System.out.println("Incorrect Answer..!\n");
            }
        }
        sc.close();
        // Calculate performance score in percentage for our answers
        double percentage = quiz.calculateMarks(userAnswers);
        System.out.println("Your marks: " + percentage + "%");
    }
}
