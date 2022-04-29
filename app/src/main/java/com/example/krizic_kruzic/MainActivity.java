package com.example.krizic_kruzic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean drugiIgrac;

    int[] stanje = {0,0,0,0,0,0,0,0,0};

    Button button;
    GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drugiIgrac = false;

        button = findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);

        grid = findViewById(R.id.glayout);

    }

    public void drop(View view) {

        ImageView counter = (ImageView) view;

        if(stanje[Integer.parseInt(counter.getTag().toString())] == 0){
            counter.setTranslationY(-2000);

            if (drugiIgrac == false){
                counter.setImageResource(R.drawable.yellow);
                stanje[Integer.parseInt(counter.getTag().toString())] = 1;

                if(checkWin() == true){

                    finish("žuti");
                }
            }

            else{
                counter.setImageResource(R.drawable.red);
                stanje[Integer.parseInt(counter.getTag().toString())] = 2;

                if(checkWin() == true){

                    finish("crveni");
                }
            }

            counter.animate().translationYBy(2000).setDuration(400);

            drugiIgrac = !drugiIgrac;
        }
    }

    public Boolean checkWin(){
        if(     (stanje[0] == stanje[1] && stanje[0] == stanje[2] && stanje[0] != 0) ||
                (stanje[3] == stanje[4] && stanje[3] == stanje[5] && stanje[3] != 0) ||
                (stanje[6] == stanje[7] && stanje[6] == stanje[8] && stanje[6] != 0) ||
                (stanje[0] == stanje[3] && stanje[0] == stanje[6] && stanje[0] != 0) ||
                (stanje[1] == stanje[4] && stanje[1] == stanje[7] && stanje[1] != 0) ||
                (stanje[2] == stanje[5] && stanje[2] == stanje[8] && stanje[2] != 0) ||
                (stanje[0] == stanje[4] && stanje[0] == stanje[8] && stanje[0] != 0) ||
                (stanje[2] == stanje[4] && stanje[2] == stanje[6] && stanje[2] != 0)){
            return true;
        }else{
            return false;
        }
    }

    public void finish(String pobjednik){

        String ispis = pobjednik + " je pobjednik";
        Toast.makeText(MainActivity.this,ispis,Toast.LENGTH_SHORT).show();
        button.setVisibility(View.VISIBLE);

    }

    public void reset(View view){

        for(int i = 0; i<9; i++){
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
            stanje[i] = 0;

            button.setVisibility(View.INVISIBLE);
        }
    }
}