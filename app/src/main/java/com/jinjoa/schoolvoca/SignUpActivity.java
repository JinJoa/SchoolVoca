package com.jinjoa.schoolvoca;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button buttonRegion, buttonSchool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        buttonRegion = (Button)findViewById(R.id.buttonRegion);
        buttonSchool = (Button)findViewById(R.id.buttonSchool);


        buttonRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] items = { "서울", "경기도", "부산", "전라북도", "전라남도", "제주도" };

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("지역")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                CharSequence sequence = items[which];
                                buttonRegion.setText(sequence.toString());
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        buttonSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CharSequence[] items = getSchoolForRegion(buttonRegion.getText().toString()) ;

                if(items == null) {
                    Toast.makeText(getApplicationContext(), "지역을 먼저 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("학교")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                CharSequence sequence = items[which];
                                buttonRegion.setText(sequence.toString());
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }


    private CharSequence[] getSchoolForRegion(String region) {
        if(region.compareTo("서울") == 0) {
            final CharSequence[] items = { "서울고 ", "북서울고" };
            return items;
        }
        else if(region.compareTo("경기도") == 0) {
            final CharSequence[] items = { "중앙고 ", "분당고" };
            return items;
        }
        else if(region.compareTo("부산") == 0) {
            final CharSequence[] items = { "부산고 ", "남부산고" };
            return items;
        }
        else if(region.compareTo("전라북도") == 0) {
            final CharSequence[] items = { "완산고 ", "전주고" };
            return items;
        }
        else if(region.compareTo("전라남도") == 0) {
            final CharSequence[] items = { "남고 ", "전라남도" };
            return items;

        }
        else if(region.compareTo("제주도") == 0) {
            final CharSequence[] items = { "제주고 ", "우도고" };
            return items;

        }
        else {
            return null;
        }
    }
}
