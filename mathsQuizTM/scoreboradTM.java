package mathsQuizTM;

public class scoreboradTM {

    private int id;
    private String name;
    private int score;

    public scoreboradTM(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public scoreboradTM() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "scoreboradTM{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
