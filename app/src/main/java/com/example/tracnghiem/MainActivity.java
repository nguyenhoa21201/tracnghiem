package com.example.tracnghiem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Button BT1, BT2, BT3, BT4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BT1 = (Button) findViewById(R.id.BtnEnglishQuizText);
        BT2 = (Button) findViewById(R.id.BtnEnglishQuizimage);
        BT3 = (Button) findViewById(R.id.BtnGuessWord);
        BT4 = (Button) findViewById(R.id.BtnThoat);
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sử dụng Intent để mở activity banhcanh
                Intent intent = new Intent(MainActivity.this, activity_EQT.class);
                startActivity(intent);
            }
        });
        BT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_EQI.class);
                startActivity(intent);
            }
        });
        BT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_GuessWord.class);
                startActivity(intent);
            }
        });
        BT4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}