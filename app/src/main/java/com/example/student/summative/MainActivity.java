package com.example.student.summative;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
//https://www.youtube.com/watch?v=IgbGeOIPu8w
public class MainActivity extends AppCompatActivity
{

    private EditText edtxtTV;
    private Button btnAddItem;
    private Button btnShowItems;
    private TextView txtvwOutput;
    private EditText edtxtStudio;
    private EditText edtxtReview;
    private ImageButton uturnbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtxtTV = (EditText) findViewById(R.id.edtxtTV);
        btnAddItem = (Button) findViewById(R.id.btnAddItem);
        btnShowItems = (Button) findViewById(R.id.btnShowItems);
        txtvwOutput = (TextView) findViewById(R.id.txtvwOutput);
        edtxtStudio = (EditText) findViewById(R.id.edtxtStudio);
        edtxtReview = (EditText) findViewById(R.id.edtxtReview);
        uturnbtn = (ImageButton) findViewById(R.id.uturnbtn);



    }

    private void startActivity(MainActivity mainActivity, Class<mainMenu> mainMenuClass) {

        startActivity(MainActivity.this, mainMenu.class);

    }

    public void menuMain(View vw){

        Intent i= new Intent(MainActivity.this, mainMenu.class);
        startActivity(i);

    }



    public void addNewItem(View vw)
    {

        new ItemAdder().execute();
        //new ItemAdder().execute(edtxtStudio.getText().toString());
        //new ItemAdder().execute(Integer.parseInt(edtxtReview.getText().toString()));
/*
        ATDatabaseHelper atdatabasehelper = new ATDatabaseHelper(this, null, null, 0);
        SQLiteDatabase db;
        String tvTitle;
        String studio;
        String review;
        ContentValues contentValues;

        try {
            db = atdatabasehelper.getWritableDatabase();
            if (edtxtTV.getText().length()==0 || edtxtStudio.getText().length()==0 || edtxtReview.getText().length()==0){

                txtvwOutput.setText("Please enter in all values");
            } else {
                tvTitle = edtxtTV.getText().toString();
                studio = edtxtStudio.getText().toString();
                review = edtxtReview.getText().toString();

                contentValues = new ContentValues();
                contentValues.put("ANIME", tvTitle);
                contentValues.put("STUDIO", studio);
                contentValues.put("REVIEW", review);

                atdatabasehelper.insertElement(db, contentValues);

                txtvwOutput.setText("Added properly.");


            }
            db.close();


        } catch (SQLiteException e){
            txtvwOutput.setText("Not Found.");
            edtxtTV.setText("Not Found.");
            edtxtStudio.setText("Not Found.");
            edtxtReview.setText("Not Found.");
        }
*/
    }

    public void showAllItems(View vw)
    {
        new ShowItems().execute();
    }

    private class ItemAdder extends AsyncTask<String, String, Boolean>
    {
        private SQLiteDatabase db;
        private ATDatabaseHelper atDatabaseHelper;
        private ContentValues cntntVals;
        private String anime;
        private String studio;
        private Integer review;

            @Override
            protected void onPreExecute()
            {
                atDatabaseHelper = new ATDatabaseHelper(MainActivity.this, null, null, 0);
                anime = edtxtTV.getText().toString();
                studio = edtxtStudio.getText().toString();
                review = Integer.parseInt(edtxtReview.getText().toString());
        }

        @Override
        protected Boolean doInBackground(String... params)
        {

            cntntVals = new ContentValues();

            cntntVals.put("ANIME", anime);
            cntntVals.put("STUDIO",studio);
            cntntVals.put("REVIEW", review);

            try
            {
                db = atDatabaseHelper.getWritableDatabase();
            }
            catch(SQLiteException sqlEx)
            {
                return false;
            }

            if (anime.length() == 0 || studio.length() == 0 || review == 0)
            {
                publishProgress("You must enter a value to add a new item.");
                return false;
            }
            else
            {
                atDatabaseHelper.insertElement(db, cntntVals);
                publishProgress("Item has been added to the database.");
            }

            atDatabaseHelper.close();
            return true;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            txtvwOutput.setText(values[0]);
        }
    }

    private class ShowItems extends AsyncTask<Void, String, Boolean>
    {
        private SQLiteDatabase db;
        private ATDatabaseHelper atDatabaseHelper;
        private Cursor crsrDBReader;
        private ArrayList<String> arylstAllItems;
        private ArrayList<Integer> arylstAllItems2;
        private ArrayList<String> arylstAllItems3;



        @Override
        protected void onPreExecute()
        {
            atDatabaseHelper = new ATDatabaseHelper(MainActivity.this, null, null, 0);
            arylstAllItems = new ArrayList<String>();
            arylstAllItems3 = new ArrayList<String>();
            arylstAllItems2 = new ArrayList<Integer>();
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                db = atDatabaseHelper.getReadableDatabase();
            }
            catch(SQLiteException sqlEx)
            {
                return false;
            }


            crsrDBReader = db.rawQuery("SELECT * FROM LIST", null);

            if(crsrDBReader != null)
            {
                if(crsrDBReader.moveToFirst())
                {
                    while(crsrDBReader.isAfterLast() == false)
                    {
                        String strItem = crsrDBReader.getString(crsrDBReader.getColumnIndex("ANIME"));
                        String strItem2 = crsrDBReader.getString(crsrDBReader.getColumnIndex("STUDIO"));
                        Integer intItem2 = crsrDBReader.getInt(crsrDBReader.getColumnIndex("REVIEW"));

                        arylstAllItems.add(strItem);
                        arylstAllItems2.add(intItem2);
                        arylstAllItems3.add(strItem2);

                        crsrDBReader.moveToNext();
                    }
                }
                crsrDBReader.close();
            }

            atDatabaseHelper.close();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean value)
        {
            txtvwOutput.setText(arylstAllItems.toString() + "\n" + arylstAllItems3.toString() + "\n" + arylstAllItems2 );
        }

    }

}
