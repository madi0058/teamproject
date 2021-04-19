package com.cst2335.teamproject;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Score extends AppCompatActivity {
    int sco;
    ArrayList<Users> contactsList = new ArrayList<>();
    private static int ACTIVITY_VIEW_CONTACT = 33;
    int positionClicked = 0;
    MyOwnAdapter myAdapter;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        //Get the fields from the screen:
        TextView nameEdit = findViewById(R.id.nameInput);
        TextView emailEdit =  findViewById(R.id.emailInput);
        TextView highscore= findViewById(R.id.highscore);
        EditText scoreEdit = findViewById(R.id.score);
        Button insertButton = findViewById(R.id.insert);
        ListView theList = findViewById(R.id.the_list);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if (sharedPreferences.contains("name")) {
            scoreEdit.setText(sharedPreferences.getString("name", ""));

        }
        loadDataFromDatabase(); //get any previously saved Users objects
        sco = getIntent().getExtras().getInt("score");
        nameEdit.setText(Trivia.diff);
        emailEdit.setText(String.valueOf(sco));
        //create an adapter object and send it to the listVIew
        myAdapter = new MyOwnAdapter();
        theList.setAdapter(myAdapter);


        //This listens for items being clicked in the list view
        theList.setOnItemClickListener(( parent,  view,  position,  id) -> {
            showContact( position );
        });


        //Listen for an insert button click event:
        insertButton.setOnClickListener( click ->
        {
            //get the email and name that were typed
            String name = nameEdit.getText().toString();
            String email = emailEdit.getText().toString();
            String score = scoreEdit.getText().toString();


            //add to the database and get the new ID
            ContentValues newRowValues = new ContentValues();

            //Now provide a value for every database column defined in MyOpener.java:
            //put string name in the NAME column:
            newRowValues.put(MyOpener.COL_NAME, name);
            //put string email in the EMAIL column:
            newRowValues.put(MyOpener.COL_EMAIL, email);
            newRowValues.put(MyOpener.COL_SCORE, score);
            //Now insert in the database:
            long newId = db.insert(MyOpener.TABLE_NAME, null, newRowValues);

            //now you have the newId, you can create the Users object
            Users newUsers = new Users(name, email,score, newId);

            //add the new contact to the list:
            contactsList.add(newUsers);
            //update the listView:
            myAdapter.notifyDataSetChanged();

            //clear the EditText fields:
//            nameEdit.setText("");
//            emailEdit.setText("");
//            scoreEdit.setText("");
        });
    }


    private void loadDataFromDatabase()
    {
        //get a database connection:
        MyOpener dbOpener = new MyOpener(this);
        db = dbOpener.getWritableDatabase(); //This calls onCreate() if you've never built the table before, or onUpgrade if the version here is newer


        // We want to get all of the columns. Look at MyOpener.java for the definitions:
        String [] columns = {MyOpener.COL_ID, MyOpener.COL_EMAIL, MyOpener.COL_NAME, MyOpener.COL_SCORE};
        //query all the results from the database:
        Cursor results = db.query(false, MyOpener.TABLE_NAME, columns, null, null, null, null, null, null);

        //Now the results object has rows of results that match the query.
        //find the column indices:
        int emailColumnIndex = results.getColumnIndex(MyOpener.COL_EMAIL);
        int nameColIndex = results.getColumnIndex(MyOpener.COL_NAME);
        int scoreColIndex = results.getColumnIndex(MyOpener.COL_SCORE);
        int idColIndex = results.getColumnIndex(MyOpener.COL_ID);

        //iterate over the results, return true if there is a next item:
        while(results.moveToNext())
        {
            String name = results.getString(nameColIndex);
            String email = results.getString(emailColumnIndex);
            String score= results.getString(scoreColIndex);
            long id = results.getLong(idColIndex);

            //add the new Users to the array list:
            contactsList.add(new Users(name, email,score, id));
        }

        //At this point, the contactsList array has loaded every row from the cursor.
    }


    protected void showContact(int position)
    {
        Users selectedUsers = contactsList.get(position);

        View contact_view = getLayoutInflater().inflate(R.layout.contact_edit, null);
        //get the TextViews
        EditText rowName = contact_view.findViewById(R.id.row_name);
        EditText rowEmail = contact_view.findViewById(R.id.row_email);
        EditText rowScore = contact_view.findViewById(R.id.row_score);
        TextView rowId = contact_view.findViewById(R.id.row_id);

        //set the fields for the alert dialog
        rowName.setText(selectedUsers.getName());
        rowEmail.setText(selectedUsers.getEmail());
        rowScore.setText(selectedUsers.getScore());
        rowId.setText("id:" + selectedUsers.getId());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You clicked on item #" + position)
                .setMessage("You can update the fields and then click update to save in the database")
                .setView(contact_view) //add the 3 edit texts showing the contact information
                .setPositiveButton("Update", (click, b) -> {
                    selectedUsers.update(rowName.getText().toString(), rowEmail.getText().toString(),rowScore.getText().toString());
                    updateContact(selectedUsers);
                    myAdapter.notifyDataSetChanged(); //the email and name have changed so rebuild the list
                })
                .setNegativeButton("Delete", (click, b) -> {
                    deleteContact(selectedUsers); //remove the contact from database
                    contactsList.remove(position); //remove the contact from contact list
                    myAdapter.notifyDataSetChanged(); //there is one less item so update the list
                })
                .setNeutralButton("dismiss", (click, b) -> { })
                .create().show();
    }

    protected void updateContact(Users c)
    {
        //Create a ContentValues object to represent a database row:
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(MyOpener.COL_NAME, c.getName());
        updatedValues.put(MyOpener.COL_EMAIL, c.getEmail());
        updatedValues.put(MyOpener.COL_SCORE, c.getScore());

        //now call the update function:
        db.update(MyOpener.TABLE_NAME, updatedValues, MyOpener.COL_ID + "= ?", new String[] {Long.toString(c.getId())});
    }

    protected void deleteContact(Users c)
    {
        db.delete(MyOpener.TABLE_NAME, MyOpener.COL_ID + "= ?", new String[] {Long.toString(c.getId())});
    }
    //This class needs 4 functions to work properly:
    protected class MyOwnAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return contactsList.size();
        }

        public Users getItem(int position){
            return contactsList.get(position);
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            View newView = getLayoutInflater().inflate(R.layout.contact_row, parent, false );

        Users thisRow = getItem(position);

            //get the TextViews
            TextView rowName = newView.findViewById(R.id.row_name);
            TextView rowEmail = newView.findViewById(R.id.row_email);
            TextView rowScore = newView.findViewById(R.id.row_score);
            TextView rowId = newView.findViewById(R.id.row_id);

            //update the text fields:
            rowName.setText(  thisRow.getName());
            rowEmail.setText( thisRow.getEmail());
            rowScore.setText(thisRow.getScore());
            rowId.setText("id:" + thisRow.getId());

            //return the row:
            return newView;
        }

        //last week we returned (long) position. Now we return the object's database id that we get from line 71
        public long getItemId(int position)
        {
            return getItem(position).getId();
        }
    }

//    protected void highscore(){
//        SharedPreferences sp = getSharedPreferences("your_prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("your_int_key", sco);
//        editor.commit();
//    }
    @Override
    protected void onPause() {
        super.onPause();
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        TextView scoreText = findViewById(R.id.score);
        TextView emailEdit =  findViewById(R.id.emailInput);
        myEdit.putString("name", scoreText.getText().toString());

        myEdit.commit();
    }
}
