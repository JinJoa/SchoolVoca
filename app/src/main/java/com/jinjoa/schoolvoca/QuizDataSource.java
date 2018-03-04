package com.jinjoa.schoolvoca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jinjoa on 2018. 3. 1..
 */

public class QuizDataSource {

    private List<QuizItem> listQuizItems;

    public void downloadQUiz() {

        listQuizItems = new ArrayList<>();

//        QuizItem item = new QuizItem();
//        item.strEnglish = "egg";
//        item.strKorean = "계란";
        listQuizItems.add(new QuizItem("egg", "계란"));
        listQuizItems.add(new QuizItem("apple", "사과"));
        listQuizItems.add(new QuizItem("may", "5월"));
        listQuizItems.add(new QuizItem("original", "원래의"));
        listQuizItems.add(new QuizItem("note", "메모하다"));
        listQuizItems.add(new QuizItem("add", "더하다"));
        listQuizItems.add(new QuizItem("immoral", "불멸의"));
    }

    public List<QuizItem> getQuiz(int numItems) {
        List<QuizItem> listReturn = new ArrayList<>();
        Collections.shuffle(listQuizItems);

        if(numItems > listQuizItems.size())
            numItems = listQuizItems.size();

        for(int i=0; i<numItems; i++) {
            listReturn.add(listQuizItems.get(i));
        }

        return listReturn;
    }
}
