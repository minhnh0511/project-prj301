/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Question {
    private String QuestionID, Content, Choice1, Choice2, Choice3, Choice4;
    private int QuestionKey;
    private String TestID;

    public Question() {
    }

    public Question(String QuestionID, String Content, String Choice1, String Choice2, String Choice3, String Choice4, int QuestionKey, String TestID) {
        this.QuestionID = QuestionID;
        this.Content = Content;
        this.Choice1 = Choice1;
        this.Choice2 = Choice2;
        this.Choice3 = Choice3;
        this.Choice4 = Choice4;
        this.QuestionKey = QuestionKey;
        this.TestID = TestID;
    }

    public String getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(String QuestionID) {
        this.QuestionID = QuestionID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getChoice1() {
        return Choice1;
    }

    public void setChoice1(String Choice1) {
        this.Choice1 = Choice1;
    }

    public String getChoice2() {
        return Choice2;
    }

    public void setChoice2(String Choice2) {
        this.Choice2 = Choice2;
    }

    public String getChoice3() {
        return Choice3;
    }

    public void setChoice3(String Choice3) {
        this.Choice3 = Choice3;
    }

    public String getChoice4() {
        return Choice4;
    }

    public void setChoice4(String Choice4) {
        this.Choice4 = Choice4;
    }

    public int getQuestionKey() {
        return QuestionKey;
    }

    public void setQuestionKey(int QuestionKey) {
        this.QuestionKey = QuestionKey;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String TestID) {
        this.TestID = TestID;
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionID=" + QuestionID + ", Content=" + Content + ", Choice1=" + Choice1 + ", Choice2=" + Choice2 + ", Choice3=" + Choice3 + ", Choice4=" + Choice4 + ", QuestionKey=" + QuestionKey + ", TestID=" + TestID + '}';
    }
    
    
}
